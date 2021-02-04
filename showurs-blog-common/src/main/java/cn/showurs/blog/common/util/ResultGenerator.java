package cn.showurs.blog.common.util;

import cn.showurs.blog.common.enumz.BusinessCode;
import cn.showurs.blog.common.exception.BusinessException;
import cn.showurs.blog.common.vo.common.Result;

/**
 * Created by CJ on 2018/12/8 23:25.
 */
public class ResultGenerator {

    public static Result<Void> getSuccessResult() {
        return new Result<>(BusinessCode.SUCCESS.getCode(), BusinessCode.SUCCESS.getText());
    }

    public static Result<Void> getSuccessResult(String message) {
        return new Result<>(BusinessCode.SUCCESS.getCode(), message);
    }

    public static <T> Result<T> getSuccessResult(T data) {
        return new Result<>(BusinessCode.SUCCESS.getCode(), BusinessCode.SUCCESS.getText(), data);
    }

    public static Result<Void> getFailResult() {
        return new Result<>(BusinessCode.FAIL.getCode(), BusinessCode.FAIL.getText());
    }

    public static Result<Void> getFailResult(String message) {
        return new Result<>(BusinessCode.FAIL.getCode(), message);
    }

    public static Result<Void> getFailResult(int code, String message) {
        return new Result<>(code, message);
    }

    public static Result<Void> getFailResult(BusinessException e) {
        return new Result<>(e.getCode(), e.getMessage());
    }

    public static <T> Result<T> getResult(int code, String message, T data) {
        return new Result<>(code, message, data);
    }
}
