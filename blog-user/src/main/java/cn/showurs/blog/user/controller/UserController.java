package cn.showurs.blog.user.controller;

import cn.showurs.blog.user.service.UserService;
import cn.showurs.blog.user.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by CJ on 2018/8/27 10:44.
 */
@Api(value = "/users", tags = "用户相关接口")
@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "get示例方法", notes = "示例作用，根据用户ID获取用户信息")
    @ApiImplicitParam(name="id", value="用户ID", required = true)
    @GetMapping("/{id}")
    public UserVo get(@PathVariable("id") Integer id) {
        return userService.findById(id);
    }

//    @ApiOperation(value = "Redis Set示例方法", notes = "示例作用，再Redis中设置键为userValue的值")
//    @ApiImplicitParam(name="value", value="userValue", required = true)
//    @PostMapping("/value")
//    public String postUserValue(@RequestParam("value") String value) {
//        return userService.setUserValue(value);
//    }
}