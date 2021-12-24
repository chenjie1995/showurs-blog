package cn.showurs.blog.user.service.impl;

import cn.showurs.blog.common.constant.RedisKey;
import cn.showurs.blog.common.core.repository.GenericRepository;
import cn.showurs.blog.common.core.service.impl.GenericServiceImpl;
import cn.showurs.blog.common.enumz.UserStatus;
import cn.showurs.blog.common.exception.BusinessException;
import cn.showurs.blog.common.util.AssertUtils;
import cn.showurs.blog.common.util.CaptchaUtils;
import cn.showurs.blog.common.vo.user.*;
import cn.showurs.blog.user.entity.UserEntity;
import cn.showurs.blog.user.repository.UserRepository;
import cn.showurs.blog.user.service.EncryptService;
import cn.showurs.blog.user.service.PowerService;
import cn.showurs.blog.user.service.RoleService;
import cn.showurs.blog.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Transactional
@Service
public class UserServiceImpl extends GenericServiceImpl<UserEntity, User, Long> implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private static final int CAPTCHA_LENGTH = 4;
    private static final int CAPTCHA_EXPIRED_SECOND = 60 * 30;

    private final UserRepository userRepository;
    private final EncryptService encryptService;
    private final RedisTemplate<String, Object> redisTemplate;
    private final RoleService roleService;
    private final PowerService powerService;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           EncryptService encryptService,
                           RedisTemplate<String, Object> redisTemplate,
                           RoleService roleService,
                           PowerService powerService,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.encryptService = encryptService;
        this.redisTemplate = redisTemplate;
        this.roleService = roleService;
        this.powerService = powerService;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public GenericRepository<UserEntity, Long> getRepository() {
        return userRepository;
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        Assert.hasText(username, "username不能为空");
        return userRepository.findByUsername(username);
    }

    @Override
    public UserToken register(String key, UserRegister userRegister) {
        String captcha = (String) redisTemplate.opsForValue().get(RedisKey.CAPTCHA_KEY + key);

        log.info("输入验证码:{}  key:{}  验证码{}", userRegister.getCaptcha(), RedisKey.CAPTCHA_KEY + key, captcha);
        validUserRegister(captcha, userRegister);
        redisTemplate.delete(RedisKey.CAPTCHA_KEY + key);

        UserEntity userEntity = initUserEntity(userRegister);
        userRepository.save(userEntity);

        return login(userRegister.getUsername(), userRegister.getPassword());
    }

    @Override
    public CaptchaImage getCaptchaImage(String key, Integer width, Integer height) {
        if (StringUtils.isEmpty(key)) {
            throw new BusinessException("获取验证码的请求不合法");
        }

        String captcha = UUID.randomUUID().toString().substring(0, CAPTCHA_LENGTH);

        CaptchaImage captchaImage = new CaptchaImage();
        captchaImage.setImage(CaptchaUtils.createCaptchaImage(captcha, width, height));
        captchaImage.setKey(key);

        redisTemplate.opsForValue().set(RedisKey.CAPTCHA_KEY + key, captcha, CAPTCHA_EXPIRED_SECOND, TimeUnit.SECONDS);

        log.info("保存验证码:{}  key:{}", captcha, RedisKey.CAPTCHA_KEY + key);

        return captchaImage;
    }

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
        userToken.setUser(poToVo(userEntity));
        return userToken;
    }

    @Override
    public UserDetails buildSecurityUser(UserEntity userEntity) {
        AssertUtils.notNull(userEntity, "userEntity不能为空");

        final String[] roles = roleService.getRoleNames(userEntity).toArray(new String[0]);
        final String[] authorities = powerService.getPowerNames(userEntity).toArray(new String[0]);

        return org.springframework.security.core.userdetails.User.withUsername(userEntity.getUsername())
                .password(userEntity.getPassword())
                .passwordEncoder(passwordEncoder::encode)
                .roles(roles)
                .authorities(authorities)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

    /**
     * 初始化注册用户
     * @param userRegister 注册用户信息
     * @return 用户实体
     */
    private UserEntity initUserEntity(UserRegister userRegister) {
        UserEntity userEntity = voToPoOptional(userRegister).orElseThrow(() -> new BusinessException("注册信息错误"));
        userEntity.setPassword(encryptService.encryptPassword(userRegister.getPassword(), userRegister.getUsername()));
        userEntity.setNickname(userRegister.getUsername());
        userEntity.setCreateTime(LocalDateTime.now());
        userEntity.setStatusCode(UserStatus.NOT_EMAIL.getCode());
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
}
