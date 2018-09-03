package cn.showurs.blog.user.service.impl;

import cn.showurs.blog.user.service.EntityService;
import cn.showurs.blog.user.po.UserPo;
import cn.showurs.blog.user.repository.UserRepository;
import cn.showurs.blog.user.service.UserService;
import cn.showurs.blog.user.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by CJ on 2018/8/29 11:24.
 */
@Service
public class UserServiceImpl extends EntityService<UserPo, UserVo> implements UserService {
    private UserRepository userRepository;
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           StringRedisTemplate stringRedisTemplate) {
        this.userRepository = userRepository;
        this.stringRedisTemplate = stringRedisTemplate;
    }


    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    @Cacheable(value = "users", key = "#id", unless = "#result == null")
    @Override
    public UserVo findById(Integer id) {
        Optional<UserPo> optionalUserPo = userRepository.findById(id);
        return optionalUserPo.map(this::poToVo).orElse(null);
    }

    @Override
    public String setUserValue(String value) {
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        valueOperations.set("userValue", value);
        return valueOperations.get("userValue");
    }
}
