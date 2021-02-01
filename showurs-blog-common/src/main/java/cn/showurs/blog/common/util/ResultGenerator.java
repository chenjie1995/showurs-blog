package cn.showurs.blog.common.util;

import cn.showurs.blog.common.enumz.BusinessCode;
import cn.showurs.blog.common.exception.BusinessException;
import cn.showurs.blog.common.vo.common.Result;

/**
 * Created by CJ on 2018/12/8 23:25.
 */
public class ResultGenerator {

    public static Result<Void> genSuccessResult() {
        return new Result<>(BusinessCode.SUCCESS.getCode(), BusinessCode.SUCCESS.getText());
    }

    public static <T> Result<T> genSuccessResult(T data) {
        return new Result<>(BusinessCode.SUCCESS.getCode(), BusinessCode.SUCCESS.getText(), data);
    }

    public static Result<Void> genFailResult(int code, String message) {
        return new Result<>(code, message);
    }

    public static Result<Void> genFailResult(BusinessException e) {
        return new Result<>(e.getCode(), e.getMessage());
    }

    public static <T> Result<T> genResult(int code, String message, T data) {
        return new Result<>(code, message, data);
    }
}
