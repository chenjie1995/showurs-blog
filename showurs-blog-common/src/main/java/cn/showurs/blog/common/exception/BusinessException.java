package cn.showurs.blog.common.exception;

import cn.showurs.blog.common.enumz.BusinessCode;

/**
 * Created by CJ on 2018/12/12 0:05.
 */
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1257805953937768429L;

    private int code;

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
        this.setCode(code);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
