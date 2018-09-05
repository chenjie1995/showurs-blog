package cn.showurs.blog.article.controller;

import cn.showurs.blog.article.client.UserClient;
import cn.showurs.blog.article.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by CJ on 2018/9/3 15:35.
 */
@RefreshScope
@RestController
@RequestMapping("articles")
public class ArticleController {
    private UserClient userClient;

    @Value("${test.name}")
    private String value;

    @Autowired
    public ArticleController(UserClient userClient) {
        this.userClient = userClient;
    }

    @GetMapping("user")
    public UserVo getArticleUser() {
        return userClient.get(1);
    }

    @GetMapping("value")
    public String getValue() {
        return value;
    }
}
