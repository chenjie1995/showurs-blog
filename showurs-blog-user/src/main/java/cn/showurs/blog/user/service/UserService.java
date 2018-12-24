package cn.showurs.blog.user.service;

import cn.showurs.blog.user.entity.UserEntity;
import cn.showurs.blog.user.vo.User;
import cn.showurs.blog.user.vo.UserRegister;

/**
 * Created by CJ on 2018/11/10 23:54.
 */
public interface UserService extends EntityService<UserEntity, User, Long> {
    User findById(Long id);
    User register(UserRegister userRegister);
}
