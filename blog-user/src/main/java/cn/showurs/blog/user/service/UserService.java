package cn.showurs.blog.user.service;

import cn.showurs.blog.user.vo.UserVo;

/**
 * Created by CJ on 2018/8/29 11:24.
 */
public interface UserService {
    UserVo findById(Integer id);
    UserVo save(UserVo userVo);
}
