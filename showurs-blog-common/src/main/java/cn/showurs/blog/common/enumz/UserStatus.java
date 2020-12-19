package cn.showurs.blog.common.enumz;

/**
 * Created by CJ on 2018/12/24 20:55.
 */
public enum UserStatus {
    DISABLE(0, "禁用"),
    NORMAL(1, "正常"),
    NOT_EMAIL(2, "邮箱未激活");

    private int code;
    private String description;

    UserStatus(int code, String description) {
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
