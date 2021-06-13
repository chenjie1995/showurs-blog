package cn.showurs.blog.user.entity;

import cn.showurs.blog.common.core.entity.GenericEntity;

import javax.persistence.*;

@Entity
@Table(name = "power")
public class PowerEntity extends GenericEntity<Long> {

    @Column(nullable = false, unique = true)
    private String name;

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
