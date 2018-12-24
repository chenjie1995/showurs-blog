package cn.showurs.blog.user.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CJ on 2018/11/25 21:58.
 */
@Entity
@Table(name = "power")
public class PowerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(length = 512)
    private String description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "power")
    private List<RolePowerEntity> rolePowers = new ArrayList<>();

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

    public List<RolePowerEntity> getRolePowers() {
        return rolePowers;
    }

    public void setRolePowers(List<RolePowerEntity> rolePowers) {
        this.rolePowers = rolePowers;
    }
}
