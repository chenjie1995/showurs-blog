package cn.showurs.blog.user.service.impl;

import cn.showurs.blog.common.constant.RedisKey;
import cn.showurs.blog.common.constant.RoleInfo;
import cn.showurs.blog.common.constant.code.UserStatus;
import cn.showurs.blog.common.core.impl.EntityServiceImpl;
import cn.showurs.blog.common.exception.BusinessException;
import cn.showurs.blog.common.util.Captcha;
import cn.showurs.blog.common.vo.user.*;
import cn.showurs.blog.user.entity.RoleEntity;
import cn.showurs.blog.user.entity.UserEntity;
import cn.showurs.blog.user.entity.UserRoleEntity;
import cn.showurs.blog.user.repository.RoleRepository;
import cn.showurs.blog.user.repository.UserRepository;
import cn.showurs.blog.user.service.EncryptService;
import cn.showurs.blog.user.service.RoleService;
import cn.showurs.blog.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by CJ on 2018/11/18 21:58.
 */
@Service
public class UserServiceImpl extends EntityServiceImpl<UserEntity, User> implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private static final int CAPTCHA_LENGTH = 4;
    private static final int CAPTCHA_EXPIRED_SECOND = 60 * 30;

    @Resource
    private UserRepository userRepository;
    @Resource
    private RoleService roleService;
    @Resource
    private EncryptService encryptService;
    @Resource
    private RedisTemplate<String, Serializable> redisTemplate;
    @Resource
    private RoleRepository roleRepository;

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    @Cacheable(value = "blogUser:user", key = "#id", unless = "#result == null")
    @Override
    public User findById(Long id) {
        return userRepository.findById(id).flatMap(this::poToVo).orElse(new User());
    }

    @Transactional
    @Override
    public UserToken register(String key, UserRegister userRegister) {
        String captcha = (String) redisTemplate.opsForValue().get(RedisKey.CAPTCHA_KEY + key);

        logger.info("输入验证码:{}  key:{}  验证码{}", userRegister.getCaptcha(), RedisKey.CAPTCHA_KEY + key, captcha);
        validUserRegister(captcha, userRegister);
        redisTemplate.delete(RedisKey.CAPTCHA_KEY + key);

        UserEntity userEntity = initUserEntity(userRegister);

        RoleEntity roleEntity = roleRepository.findByName(RoleInfo.ROLE_DEFAULT_BLOGGER_NAME).orElseGet(RoleService::getInitBloggerRole);
        roleRepository.save(roleEntity);

        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setUser(userEntity);
        userRoleEntity.setRole(roleEntity);
        userEntity.getUserRoles().add(userRoleEntity);
        userRepository.save(userEntity);

        return login(userRegister.getUsername(), userRegister.getPassword());
    }

    @Transactional
    @Override
    public CaptchaImage getCaptchaImage(String key, Integer width, Integer height) {
        if (StringUtils.isEmpty(key)) {
            throw new BusinessException("获取验证码的请求不合法");
        }

        String captcha = UUID.randomUUID().toString().substring(0, CAPTCHA_LENGTH);

        CaptchaImage captchaImage = new CaptchaImage();
        captchaImage.setImage(Captcha.createCaptchaImage(captcha, width, height));
        captchaImage.setKey(key);

        redisTemplate.opsForValue().set(RedisKey.CAPTCHA_KEY + key, captcha, CAPTCHA_EXPIRED_SECOND, TimeUnit.SECONDS);

        logger.info("保存验证码:{}  key:{}", captcha, RedisKey.CAPTCHA_KEY + key);

        return captchaImage;
    }

    @Transactional
    @Override
    public UserToken login(String username, String password) {
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(() -> new BusinessException("用户不存在"));

        if (!userEntity.getPassword().equals(encryptService.encryptPassword(password, username))) {
            throw new BusinessException("密码错误");
        }

        UserJwtSubject userJwtSubject = new UserJwtSubject();
        userJwtSubject.setUserId(userEntity.getId());

        String token = encryptService.generateToken(userJwtSubject);

        UserToken userToken = new UserToken();
        userToken.setToken(token);
        Set<Role> roles = poToVo(userEntity).orElse(new User()).getRoles();
        Set<Power> powers = new HashSet<>();
        if (!CollectionUtils.isEmpty(roles)) {
            roles.forEach(role -> powers.addAll(role.getPowers()));
        }
        userToken.setRoles(roles);
        userToken.setPowers(powers);
        return userToken;
    }

    /**
     * 初始化注册用户
     * @param userRegister 注册用户信息
     * @return 用户实体
     */
    private UserEntity initUserEntity(UserRegister userRegister) {
        UserEntity userEntity = voToPo(userRegister).orElseThrow(() -> new BusinessException("注册信息错误"));
        userEntity.setPassword(encryptService.encryptPassword(userRegister.getPassword(), userRegister.getUsername()));
        userEntity.setNickname(userRegister.getUsername());
        userEntity.setCreateTime(LocalDateTime.now());
        userEntity.setStatus(UserStatus.NOT_EMAIL.getCode());
        return userEntity;
    }

    /**
     * 校验注册信息是否合法
     * @param captcha 验证码
     * @param userRegister 用户注册信息
     */
    private void validUserRegister(String captcha, UserRegister userRegister) {
        if (!userRegister.getPassword().equals(userRegister.getConfirmPassword())) {
            throw new BusinessException("密码不相同");
        }
        if (!captcha.equalsIgnoreCase(userRegister.getCaptcha())) {
            throw new BusinessException("验证码错误");
        }
        if (StringUtils.isEmpty(captcha)) {
            throw new BusinessException("验证码已过期");
        }
        userRepository.findByUsername(userRegister.getUsername()).ifPresent(user -> {
            throw new BusinessException("用户名已被使用");
        });
        userRepository.findByEmail(userRegister.getEmail()).ifPresent(user -> {
            throw new BusinessException("邮箱已被使用");
        });
    }

    @Override
    public Optional<User> poToVo(UserEntity po) {
        if (super.poToVo(po).isPresent()) {
            User user = super.poToVo(po).get();
            Set<Role> roles = roleService.posToVos(po.getUserRoles().stream().map(UserRoleEntity::getRole).collect(Collectors.toSet()));
            user.setRoles(roles);
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }

}
