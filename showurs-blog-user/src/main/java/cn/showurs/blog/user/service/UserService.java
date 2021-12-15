package cn.showurs.blog.user.service;

import cn.showurs.blog.common.core.service.GenericService;
import cn.showurs.blog.common.vo.user.CaptchaImage;
import cn.showurs.blog.common.vo.user.User;
import cn.showurs.blog.common.vo.user.UserRegister;
import cn.showurs.blog.common.vo.user.UserToken;
import cn.showurs.blog.user.entity.UserEntity;

import java.util.Optional;

public interface UserService extends GenericService<UserEntity, User, Long> {

    /**
     * 根据用户名获取用户实体
     * @param username 用户名
     * @return 用户实体
     */
    Optional<UserEntity> findByUsername(String username);

    /**
     * 注册用户
     * @param key 注册验证码key
     * @param userRegister 注册信息
     * @return 用户token
     */
    UserToken register(String key, UserRegister userRegister);

    /**
     * 获取验证码图片
     * @param key 验证码key
     * @param width 图片宽度
     * @param height 图片高度
     * @return 验证码图片
     */
    CaptchaImage getCaptchaImage(String key, Integer width, Integer height);

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return 用户token
     */
    UserToken login(String username, String password);

}
