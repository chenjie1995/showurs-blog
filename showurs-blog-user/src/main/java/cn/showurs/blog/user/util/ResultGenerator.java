package cn.showurs.blog.user.util;

import cn.showurs.blog.user.constant.ResultCode;
import cn.showurs.blog.user.vo.Result;

/**
 * Created by CJ on 2018/12/8 23:25.
 */
public class ResultGenerator {

    public static Result genSuccessResult() {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result;
    }

    public static <T> Result<T> genSuccessResult(T data) {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        result.setData(data);
        return result;
    }

    public static Result genFailResult(int code, String message) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
}
