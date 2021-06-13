package cn.showurs.blog.common.exception;

import cn.showurs.blog.common.enumz.BusinessCode;

/**
 * 业务异常
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1257805953937768429L;

    private static final int DEFAULT_CODE = BusinessCode.FAIL.getCode();
    private static final String DEFAULT_MESSAGE = BusinessCode.FAIL.getText();

    private final int code;

    public BusinessException() {
        this(DEFAULT_CODE, DEFAULT_MESSAGE);
    }

    public BusinessException(int code) {
        this(code, DEFAULT_MESSAGE);
    }

    public BusinessException(String message) {
        this(DEFAULT_CODE, message);
    }

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(Throwable cause) {
        this(DEFAULT_CODE, DEFAULT_MESSAGE, cause);
    }

    public BusinessException(String message, Throwable cause) {
        this(DEFAULT_CODE, message, cause);
    }

    public BusinessException(int code, Throwable cause) {
        this(code, DEFAULT_MESSAGE, cause);
    }

    public BusinessException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
