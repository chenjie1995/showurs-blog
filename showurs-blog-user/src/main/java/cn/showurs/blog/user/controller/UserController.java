package cn.showurs.blog.user.controller;

import cn.showurs.blog.common.annotation.Auth;
import cn.showurs.blog.common.annotation.CurrentUser;
import cn.showurs.blog.common.constant.RequestInfo;
import cn.showurs.blog.common.constant.ResponseInfo;
import cn.showurs.blog.common.exception.BusinessException;
import cn.showurs.blog.common.util.ResultGenerator;
import cn.showurs.blog.common.vo.common.Result;
import cn.showurs.blog.common.vo.user.*;
import cn.showurs.blog.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Api(tags = "用户")
@RestController
@RequestMapping(value = "users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    
    @Resource
    private UserService userService;

    @ApiImplicitParam(value = "验证码KEY", paramType = "header", required = true, name = RequestInfo.HEADER_CAPTCHA_KEY_NAME, dataType = "String")
    @ApiOperation("注册")
    @PostMapping(value = "register")
    public Result<UserToken> register(@Validated @RequestBody UserRegister userRegister,
                                      @RequestHeader(RequestInfo.HEADER_CAPTCHA_KEY_NAME) String key) {

        if (StringUtils.isEmpty(key)) {
            throw new BusinessException("验证码KEY值不能为空");
        }

        return ResultGenerator.getSuccessResult(userService.register(key, userRegister));
    }

    @ApiOperation("获取验证码图片")
    @GetMapping("captcha-image")
    public void getCaptchaImage(@RequestParam(defaultValue = "120") Integer width,
                                @RequestParam(defaultValue = "32") Integer height,
                                @RequestParam String key,
                                HttpServletResponse response) {
        CaptchaImage captchaImage = userService.getCaptchaImage(key, width, height);
        response.setHeader(ResponseInfo.HEADER_CAPTCHA_KEY_NAME, captchaImage.getKey());
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        OutputStream outputStream;
        try {
            outputStream = response.getOutputStream();
            ImageIO.write(captchaImage.getImage(), "png", outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            logger.error("获取验证码图片IO错误，错误信息：{}", e.getMessage());
            e.printStackTrace();
        }

    }

    @ApiOperation("登录")
    @PostMapping("login")
    public Result<UserToken> login(@Validated @RequestBody UserLogin userLogin) {
        return ResultGenerator.getSuccessResult(userService.login(userLogin.getUsername(), userLogin.getPassword()));
    }

    @Auth
    @ApiOperation("根据用户ID获取用户信息")
    @GetMapping("{id}")
    public Result<User> getById(@PathVariable Long id) {
        return ResultGenerator.getSuccessResult(userService.findUser(id));
    }

    @Auth
    @ApiOperation("获取当前用户信息")
    @GetMapping("me")
    public Result<User> me(@ApiIgnore @CurrentUser User user) {
        return ResultGenerator.getSuccessResult(user);
    }
}
