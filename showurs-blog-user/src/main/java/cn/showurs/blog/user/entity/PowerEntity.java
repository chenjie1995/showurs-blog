package cn.showurs.blog.user.entity;

import javax.persistence.*;

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


}
