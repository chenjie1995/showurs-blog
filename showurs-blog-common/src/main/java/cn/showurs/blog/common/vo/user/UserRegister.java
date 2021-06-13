package cn.showurs.blog.common.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@ApiModel(value = "用户注册信息")
public class UserRegister {
    @NotBlank(message = "用户名不能为空")
    @Size(max = 18, message = "用户名长度过长")
    @ApiModelProperty("用户名")
    private String username;

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    @ApiModelProperty("邮箱")
    private String email;

    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 18, message = "密码长度应在6-18位")
    @ApiModelProperty("密码")
    private String password;

    @NotBlank(message = "确认密码不能为空")
    @ApiModelProperty("确认密码")
    private String confirmPassword;

    @NotBlank(message = "验证码不能为空")
    @Size(max = 4, message = "验证码长度错误")
    @ApiModelProperty("验证码")
    private String captcha;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
