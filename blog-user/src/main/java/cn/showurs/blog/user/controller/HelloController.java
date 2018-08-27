package cn.showurs.blog.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by CJ on 2018/8/27 10:44.
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String index() {
        return "hello，this is first message";
    }
}
