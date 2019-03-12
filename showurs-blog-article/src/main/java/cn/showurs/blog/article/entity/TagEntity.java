package cn.showurs.blog.article.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by CJ on 2019/1/28 11:05.
 */
@Entity
@Table(name = "tag")
public class TagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tag")
    private Set<ArticleTagEntity> articleTags = new HashSet<>();

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

    public Set<ArticleTagEntity> getArticleTags() {
        return articleTags;
    }

    public void setArticleTags(Set<ArticleTagEntity> articleTags) {
        this.articleTags = articleTags;
    }
}
