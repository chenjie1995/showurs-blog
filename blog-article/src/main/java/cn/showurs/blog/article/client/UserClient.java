package cn.showurs.blog.article.client;

import cn.showurs.blog.article.vo.UserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by CJ on 2018/9/3 15:14.
 */
@Component
@FeignClient("blog-user")
public interface UserClient {
    @GetMapping(value = "/users/{id}")
    UserVo get(@PathVariable("id") Integer id);
}
