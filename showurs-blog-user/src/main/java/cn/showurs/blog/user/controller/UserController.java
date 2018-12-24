package cn.showurs.blog.user.controller;

import cn.showurs.blog.user.common.exception.BusinessException;
import cn.showurs.blog.user.common.util.ResultGenerator;
import cn.showurs.blog.user.service.UserService;
import cn.showurs.blog.user.vo.Result;
import cn.showurs.blog.user.vo.User;
import cn.showurs.blog.user.vo.UserRegister;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "register", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<User> register(@Validated @RequestBody UserRegister userRegister,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BusinessException(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }

        return ResultGenerator.genSuccessResult(userService.register(userRegister));
    }
}
