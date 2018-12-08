package cn.showurs.blog.user.constant;

/**
 * Created by CJ on 2018/12/8 23:27.
 */
public enum ResultCode {
    SUCCESS(200, "成功"),
    FAIL(400, "失败"),
    ERROR(500, "服务器错误");

    private int code;
    private String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
