package cn.showurs.blog.common.vo.user;

import cn.showurs.blog.common.vo.common.GenericValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by CJ on 2018/12/24 16:19.
 */
@ApiModel(value = "权限")
public class Power extends GenericValue<Long> {

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("描述")
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
