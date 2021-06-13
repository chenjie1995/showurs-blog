package cn.showurs.blog.common.exception;

import cn.showurs.blog.common.enumz.BusinessCode;

/**
 * 未认证异常
 */
public class UnauthorizedException extends BusinessException {

    private static final long serialVersionUID = 5792637866566222873L;

    private static final int DEFAULT_CODE = BusinessCode.UNAUTHORIZED.getCode();
    private static final String DEFAULT_MESSAGE = BusinessCode.UNAUTHORIZED.getText();

    public UnauthorizedException() {
        this(DEFAULT_MESSAGE);
    }

    public UnauthorizedException(String message) {
        super(DEFAULT_CODE, message);
    }

    public UnauthorizedException(Throwable cause) {
        this(DEFAULT_MESSAGE, cause);
    }

    public UnauthorizedException(String message, Throwable cause) {
        super(DEFAULT_CODE, message, cause);
    }
}
