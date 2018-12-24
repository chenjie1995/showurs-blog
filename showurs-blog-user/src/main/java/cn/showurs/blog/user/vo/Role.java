package cn.showurs.blog.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by CJ on 2018/12/24 16:06.
 */
@ApiModel(value = "角色")
public class Role {

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("拥有的权限")
    private List<Power> powers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<Power> getPowers() {
        return powers;
    }

    public void setPowers(List<Power> powers) {
        this.powers = powers;
    }
}
