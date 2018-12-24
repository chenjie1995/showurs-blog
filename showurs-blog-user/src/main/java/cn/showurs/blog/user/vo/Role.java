package cn.showurs.blog.user.vo;

import java.util.List;
import java.util.Set;

/**
 * Created by CJ on 2018/12/24 16:06.
 */
public class Role {
    private Long id;

    private String name;

    private String description;

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
