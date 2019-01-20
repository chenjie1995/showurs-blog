package cn.showurs.blog.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by CJ on 2019/1/17 17:19.
 */
@ApiModel("用户Token")
public class UserToken implements Serializable {
    private static final long serialVersionUID = 936262283449057619L;

    @ApiModelProperty("Token")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
