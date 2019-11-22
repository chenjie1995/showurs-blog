package cn.showurs.blog.article.client.fallback;

import cn.showurs.blog.article.client.UserClient;
import cn.showurs.blog.common.vo.common.Result;
import cn.showurs.blog.common.vo.user.User;

/**
 * Created by CJ on 2019/3/13 16:53.
 */
public class UserClientFallback implements UserClient {
    @Override
    public Result<User> getById(Long id) {
        return null;
    }
}
