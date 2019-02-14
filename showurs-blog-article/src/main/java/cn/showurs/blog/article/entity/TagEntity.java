package cn.showurs.blog.article.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CJ on 2019/1/28 11:05.
 */
@Entity
@Table(name = "tag")
public class TagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tag")
    private List<ArticleTagEntity> articleTags = new ArrayList<>();

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

    public List<ArticleTagEntity> getArticleTags() {
        return articleTags;
    }

    public void setArticleTags(List<ArticleTagEntity> articleTags) {
        this.articleTags = articleTags;
    }
}
