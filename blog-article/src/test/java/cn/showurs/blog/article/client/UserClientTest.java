package cn.showurs.blog.article.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by CJ on 2018/9/3 15:31.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserClientTest {
    @Autowired
    private UserClient userClient;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void get() {
        logger.info(userClient.get(1).getUsername());
    }
}