package cn.showurs.blog.common.util;

import cn.showurs.blog.common.enumz.BusinessCode;
import cn.showurs.blog.common.vo.common.Result;

/**
 * Created by CJ on 2018/12/8 23:25.
 */
public class ResultGenerator {

    public static Result<Void> genSuccessResult() {
        return new Result<>(BusinessCode.SUCCESS.getCode(), BusinessCode.SUCCESS.getDescription());
    }

    public static <T> Result<T> genSuccessResult(T data) {
        return new Result<>(BusinessCode.SUCCESS.getCode(), BusinessCode.SUCCESS.getDescription(), data);
    }

    public static Result<Void> genFailResult(int code, String message) {
        Result<Void> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }



}
