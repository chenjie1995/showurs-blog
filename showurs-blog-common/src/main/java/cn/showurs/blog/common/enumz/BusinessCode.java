package cn.showurs.blog.common.enumz;

/**
 * Created by CJ on 2018/12/8 23:27.
 */
public enum BusinessCode {
    SUCCESS(200, "成功"),
    FAIL(400, "业务异常"),
    UNAUTHORIZED(401, "未认证"),
    UNSUPPORTED_MEDIA_TYPE(415, "请求格式错误"),
    UNPROCESSABLE_ENTITY(422, "不可处理的实体"),
    ERROR(500, "服务器错误");

    private Integer code;
    private String description;

    BusinessCode(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
