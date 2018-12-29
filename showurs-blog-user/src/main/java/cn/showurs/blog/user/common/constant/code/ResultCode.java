package cn.showurs.blog.user.common.constant.code;

/**
 * Created by CJ on 2018/12/8 23:27.
 */
public enum ResultCode {
    SUCCESS(200, "成功"),
    FAIL(400, "失败"),
    UNAUTHORIZED(401, "未授权"),
    UNSUPPORTED_MEDIA_TYPE(415, "请求格式错误"),
    UNPROCESSABLE_ENTITY(422, "不可处理的实体"),
    ERROR(500, "服务器错误");

    private int code;
    private String description;

    ResultCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
