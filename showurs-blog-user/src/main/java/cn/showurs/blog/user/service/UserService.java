package cn.showurs.blog.user.service;

import cn.showurs.blog.common.core.EntityService;
import cn.showurs.blog.common.vo.user.CaptchaImage;
import cn.showurs.blog.common.vo.user.User;
import cn.showurs.blog.common.vo.user.UserRegister;
import cn.showurs.blog.common.vo.user.UserToken;
import cn.showurs.blog.user.entity.UserEntity;

/**
 * Created by CJ on 2018/11/10 23:54.
 */
public interface UserService extends EntityService<UserEntity, User> {
    User findById(Long id);

    UserToken register(String key, UserRegister userRegister);

    CaptchaImage getCaptchaImage(String key, Integer width, Integer height);

    UserToken login(String username, String password);
}
