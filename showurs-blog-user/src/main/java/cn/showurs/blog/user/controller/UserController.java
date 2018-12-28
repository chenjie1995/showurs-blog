package cn.showurs.blog.user.controller;

import cn.showurs.blog.user.common.constant.ResponseInfo;
import cn.showurs.blog.user.common.exception.BusinessException;
import cn.showurs.blog.user.common.util.ResultGenerator;
import cn.showurs.blog.user.service.UserService;
import cn.showurs.blog.user.vo.CaptchaImage;
import cn.showurs.blog.user.vo.Result;
import cn.showurs.blog.user.vo.User;
import cn.showurs.blog.user.vo.UserRegister;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by CJ on 2018/12/23 22:10.
 */
@Api(value = "用户", description = "用户相关接口😉")
@RestController
@RequestMapping(value = "user/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation("注册用户")
    @PostMapping(value = "register", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<User> register(@Validated @RequestBody UserRegister userRegister,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BusinessException(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }

        return ResultGenerator.genSuccessResult(userService.register(userRegister));
    }

    @ApiOperation("获取验证码图片")
    @GetMapping(value = "captcha-image")
    public void getCaptchaImage(@RequestParam Integer width,
                               @RequestParam Integer height,
                               HttpServletResponse response) {

        try {
            CaptchaImage captchaImage = this.userService.getCaptchaImage(width, height);

            response.setHeader(ResponseInfo.HEADER_CAPTCHA_KEY_NAME, captchaImage.getKey());

            OutputStream outputStream = response.getOutputStream();
            ImageIO.write(captchaImage.getImage(), "png", outputStream);

            outputStream.flush();
            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
