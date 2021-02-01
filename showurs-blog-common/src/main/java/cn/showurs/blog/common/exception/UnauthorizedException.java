package cn.showurs.blog.common.exception;

import cn.showurs.blog.common.enumz.BusinessCode;

/**
 * Created by CJ on 2018/12/12 0:15.
 */
public class UnauthorizedException extends BusinessException {
    private static final long serialVersionUID = 5792637866566222873L;

    public UnauthorizedException() {
        super(BusinessCode.UNAUTHORIZED.getCode(), BusinessCode.UNAUTHORIZED.getDescription());
    }

    public UnauthorizedException(String message) {
        super(BusinessCode.UNAUTHORIZED.getCode(), message);
    }
}
