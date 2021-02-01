package cn.showurs.blog.common.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by CJ on 2019/1/17 17:19.
 */
@ApiModel("用户Token")
public class UserToken {
    @ApiModelProperty("Token")
    private String token;

    @ApiModelProperty("用户信息")
    private User user;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
