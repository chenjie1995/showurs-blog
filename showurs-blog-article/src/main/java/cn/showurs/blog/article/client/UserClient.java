package cn.showurs.blog.article.client;

import cn.showurs.blog.article.client.fallback.UserClientFallback;
import cn.showurs.blog.common.vo.Result;
import cn.showurs.blog.common.vo.user.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by CJ on 2019/3/13 16:49.
 */
@FeignClient(value = "showurs-blog-user", fallback = UserClientFallback.class)
public interface UserClient {
    @GetMapping("/users/{id}")
    Result<User> getById(@PathVariable Long id);
}
