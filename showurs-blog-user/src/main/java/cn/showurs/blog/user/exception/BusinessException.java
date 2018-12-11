package cn.showurs.blog.user.exception;

import cn.showurs.blog.user.constant.ResultCode;

/**
 * Created by CJ on 2018/12/12 0:05.
 */
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1257805953937768429L;

    private int code;

    public BusinessException() {
        this(ResultCode.FAIL.getCode(), ResultCode.FAIL.getDescription());
    }

    public BusinessException(int code) {
        this(code, ResultCode.FAIL.getDescription());
    }

    public BusinessException(String message) {
        this(ResultCode.FAIL.getCode(), message);
    }

    public BusinessException(int code, String message) {
        super(message);
        this.setCode(code);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
