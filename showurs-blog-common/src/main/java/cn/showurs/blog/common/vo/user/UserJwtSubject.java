package cn.showurs.blog.common.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by CJ on 2018/12/24 10:59.
 */
@ApiModel(value = "用户JWT存储信息")
public class UserJwtSubject {
    @ApiModelProperty("用户ID")
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
