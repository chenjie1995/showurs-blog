package cn.showurs.blog.user.entity;

import cn.showurs.blog.common.core.entity.GenericEntity;

import javax.persistence.*;

/**
 * Created by CJ on 2018/12/2 23:36.
 */
@Entity
@Table(name = "role_power", uniqueConstraints = {@UniqueConstraint(columnNames = {"role_id", "power_id"})})
public class RolePowerEntity extends GenericEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity roleEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "power_id", nullable = false)
    private PowerEntity powerEntity;

    public RoleEntity getRoleEntity() {
        return roleEntity;
    }

    public void setRoleEntity(RoleEntity roleEntity) {
        this.roleEntity = roleEntity;
    }

    public PowerEntity getPowerEntity() {
        return powerEntity;
    }

    public void setPowerEntity(PowerEntity powerEntity) {
        this.powerEntity = powerEntity;
    }
}
