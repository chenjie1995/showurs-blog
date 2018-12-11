package cn.showurs.blog.user.exception;

import cn.showurs.blog.user.constant.ResultCode;

/**
 * Created by CJ on 2018/12/12 0:15.
 */
public class UnauthorizedException extends BusinessException {
    private static final long serialVersionUID = 5792637866566222873L;

    public UnauthorizedException() {
        super(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getDescription());
    }

    public UnauthorizedException(String message) {
        super(ResultCode.UNAUTHORIZED.getCode(), message);
    }
}
