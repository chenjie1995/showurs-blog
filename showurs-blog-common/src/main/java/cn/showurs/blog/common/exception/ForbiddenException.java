package cn.showurs.blog.common.exception;

import cn.showurs.blog.common.enumz.BusinessCode;

/**
 * 未授权异常
 */
public class ForbiddenException extends BusinessException {

    private static final long serialVersionUID = -879260226292573043L;

    private static final int DEFAULT_CODE = BusinessCode.FORBIDDEN.getCode();
    private static final String DEFAULT_MESSAGE = BusinessCode.FORBIDDEN.getText();

    public ForbiddenException() {
        this(DEFAULT_MESSAGE);
    }

    public ForbiddenException(String message) {
        super(DEFAULT_CODE, message);
    }

    public ForbiddenException(Throwable cause) {
        this(DEFAULT_MESSAGE, cause);
    }

    public ForbiddenException(String message, Throwable cause) {
        super(DEFAULT_CODE, message, cause);
    }
}
