package cn.showurs.blog.article.controller;

import cn.showurs.blog.article.service.ArticleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by CJ on 2019/3/10 1:12.
 */
@RestController
@RequestMapping("/articles")
public class ArticleController {
    @Resource
    private ArticleService articleService;


}
