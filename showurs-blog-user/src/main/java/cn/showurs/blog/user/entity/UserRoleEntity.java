package cn.showurs.blog.user.entity;

import cn.showurs.blog.common.core.entity.GenericEntity;

import javax.persistence.*;

@Entity
@Table(name = "user_role", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role_id"})})
public class UserRoleEntity extends GenericEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity roleEntity;

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public RoleEntity getRoleEntity() {
        return roleEntity;
    }

    public void setRoleEntity(RoleEntity roleEntity) {
        this.roleEntity = roleEntity;
    }
}
