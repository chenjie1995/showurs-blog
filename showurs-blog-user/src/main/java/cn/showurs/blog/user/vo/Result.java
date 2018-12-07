package cn.showurs.blog.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by CJ on 2018/11/30 22:44.
 * 接口统一返回的结果
 */
@ApiModel(value = "结果", description = "统一的返回结果")
public class Result<T> {
    @ApiModelProperty("编码")
    private Integer code;

    @ApiModelProperty("消息")
    private String message;

    @ApiModelProperty("数据")
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
