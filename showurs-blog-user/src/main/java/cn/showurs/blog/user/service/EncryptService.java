package cn.showurs.blog.user.service;

import cn.showurs.blog.common.vo.user.UserJwtSubject;

/**
 * Created by CJ on 2018/12/24 9:39.
 */
public interface EncryptService {

    /**
     * 加密密码
     *
     * @param password 密码
     * @param salt     盐
     * @return 密文
     */
    String encryptPassword(String password, String salt);

    /**
     * 生成简单随机字符串token
     *
     * @return token
     */
    String generateToken();

    /**
     * 生成JWT字符串token
     *
     * @param userJwtSubject 存储的用户信息
     * @return token
     */
    String generateToken(UserJwtSubject userJwtSubject);
}
