package cn.showurs.blog.user.entity;

import cn.showurs.blog.common.core.entity.GenericEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CJ on 2018/11/18 22:47.
 */
@Entity
@Table(name = "role")
public class RoleEntity extends GenericEntity<Long> {

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "roleEntity")
    private List<RolePowerEntity> rolePowerEntities = new ArrayList<>();

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

    public List<RolePowerEntity> getRolePowerEntities() {
        return rolePowerEntities;
    }

    public void setRolePowerEntities(List<RolePowerEntity> rolePowerEntities) {
        this.rolePowerEntities = rolePowerEntities;
    }
}
