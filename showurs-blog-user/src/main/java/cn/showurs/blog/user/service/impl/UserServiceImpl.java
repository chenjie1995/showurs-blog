package cn.showurs.blog.user.service.impl;

import cn.showurs.blog.user.common.constant.RedisKey;
import cn.showurs.blog.user.common.util.Captcha;
import cn.showurs.blog.user.entity.UserEntity;
import cn.showurs.blog.user.entity.UserRoleEntity;
import cn.showurs.blog.user.repository.UserRepository;
import cn.showurs.blog.user.service.EncryptService;
import cn.showurs.blog.user.service.RoleService;
import cn.showurs.blog.user.service.UserService;
import cn.showurs.blog.user.vo.CaptchaImage;
import cn.showurs.blog.user.vo.Role;
import cn.showurs.blog.user.vo.User;
import cn.showurs.blog.user.vo.UserRegister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by CJ on 2018/11/18 21:58.
 */
@Service
public class UserServiceImpl extends EntityServiceImpl<UserEntity, User, Long> implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private static final int CAPTCHA_LENGTH = 4;

    private UserRepository userRepository;
    private RoleService roleService;
    private EncryptService encryptService;
    private RedisTemplate<String, Object> redisTemplate;

    public UserServiceImpl(UserRepository userRepository,
                           RoleService roleService,
                           EncryptService encryptService,
                           RedisTemplate<String, Object> redisTemplate) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.encryptService = encryptService;
        this.redisTemplate = redisTemplate;
    }

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    @Cacheable(value = "blogUser:users", key = "#id", unless = "#result == null")
    @Override
    public User findById(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        return poToVo(userEntity);
    }

    @Transactional
    @Override
    public User register(UserRegister userRegister) {

        return null;
    }

    @Transactional
    @Override
    public CaptchaImage getCaptchaImage(Integer width, Integer height) {

        String captcha = UUID.randomUUID().toString().substring(0, CAPTCHA_LENGTH);
        String key = encryptService.generateToken();

        CaptchaImage captchaImage = new CaptchaImage();
        captchaImage.setImage(Captcha.createCaptchaImage(captcha, width, height));
        captchaImage.setKey(key);

        redisTemplate.opsForValue().set(RedisKey.CAPTCHA_KEY + key, captcha);

        return captchaImage;
    }

    @Override
    public User poToVo(UserEntity po) {
        if (po == null) {
            return null;
        }

        User user = super.poToVo(po);
        List<Role> roles = roleService.posToVos(po.getUserRoles().stream().map(UserRoleEntity::getRole).collect(Collectors.toList()));

        user.setRoles(roles);

        return super.poToVo(po);
    }
}
