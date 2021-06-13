package cn.showurs.blog.common.enumz;

public enum UserStatus {

    DISABLE(0, "禁用"),
    NORMAL(1, "正常"),
    NOT_EMAIL(2, "邮箱未激活");

    private Integer code;
    private String text;

    UserStatus(Integer code, String text) {
        this.code = code;
        this.text = text;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
