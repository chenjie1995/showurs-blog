package cn.showurs.blog.user.service;

import cn.showurs.blog.user.vo.UserVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by CJ on 2018/9/4 11:33.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void save() {
        UserVo userVo = userService.findById(1);
        userVo.setNickname("陈杰");
        userService.save(userVo);
    }
}