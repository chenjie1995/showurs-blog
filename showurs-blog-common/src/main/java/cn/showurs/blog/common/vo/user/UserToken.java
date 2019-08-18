package cn.showurs.blog.common.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by CJ on 2019/1/17 17:19.
 */
@ApiModel("用户Token")
public class UserToken implements Serializable {
    private static final long serialVersionUID = 936262283449057619L;

    @ApiModelProperty("Token")
    private String token;

    private Set<Role> roles;

    private Set<Power> powers;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Power> getPowers() {
        return powers;
    }

    public void setPowers(Set<Power> powers) {
        this.powers = powers;
    }
}
