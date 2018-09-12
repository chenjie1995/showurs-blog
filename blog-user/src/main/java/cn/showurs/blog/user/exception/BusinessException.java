package cn.showurs.blog.user.exception;

/**
 * Created by CJ on 2018/9/12 14:57.
 */
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1257805953937768429L;

    private int code;

    public BusinessException() {
        this(400, "业务异常");
    }

    public BusinessException(int code) {
        this(code, "业务异常");
    }

    public BusinessException(String message) {
        this(400, message);
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
