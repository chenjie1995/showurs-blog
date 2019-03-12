package cn.showurs.blog.user.entity;

import javax.persistence.*;

/**
 * Created by CJ on 2018/12/2 23:36.
 */
@Entity
@Table(name = "role_power", uniqueConstraints = {@UniqueConstraint(columnNames = {"role_id", "power_id"})})
public class RolePowerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity role;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "power_id", nullable = false)
    private PowerEntity power;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    public PowerEntity getPower() {
        return power;
    }

    public void setPower(PowerEntity power) {
        this.power = power;
    }
}
