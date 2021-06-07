package cn.showurs.blog.common.exception;

import cn.showurs.blog.common.enumz.BusinessCode;

/**
 * Created by CJ on 2018/12/12 0:05.
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1257805953937768429L;

    private final int code;

    public BusinessException() {
        this(BusinessCode.FAIL.getCode(), BusinessCode.FAIL.getText());
    }

    public BusinessException(int code) {
        this(code, BusinessCode.FAIL.getText());
    }

    public BusinessException(String message) {
        this(BusinessCode.FAIL.getCode(), message);
    }

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(Throwable cause) {
        this(BusinessCode.FAIL.getCode(), BusinessCode.FAIL.getText(), cause);
    }

    public BusinessException(String message, Throwable cause) {
        this(BusinessCode.FAIL.getCode(), message, cause);
    }

    public BusinessException(int code, Throwable cause) {
        this(code, BusinessCode.FAIL.getText(), cause);
    }

    public BusinessException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
