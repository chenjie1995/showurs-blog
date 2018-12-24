package cn.showurs.blog.user.service.impl;

import cn.showurs.blog.user.common.constant.JwtInfo;
import cn.showurs.blog.user.common.exception.BusinessException;
import cn.showurs.blog.user.common.util.Encrypt;
import cn.showurs.blog.user.service.EncryptService;
import cn.showurs.blog.user.vo.UserJwtSubject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by CJ on 2018/12/24 9:47.
 */
@Service
public class EncryptServiceImpl implements EncryptService {
    @Override
    public String encryptPassword(String password, String salt) {
        String temp = Encrypt.sha256(password + salt);
        return Encrypt.sha256(temp + salt);
    }

    @Override
    public String generateToken(UserJwtSubject userJwtSubject) {
        ObjectMapper objectMapper = new ObjectMapper();
        String subject;

        try {
            subject = objectMapper.writeValueAsString(userJwtSubject);
        } catch (JsonProcessingException e) {
            throw new BusinessException("Token生成失败");
        }

        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, JwtInfo.KEY)
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + JwtInfo.EXPIRATION))
                .compact();
    }
}
