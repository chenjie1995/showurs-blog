package cn.showurs.blog.article.entity;

import javax.persistence.*;

/**
 * Created by CJ on 2019/1/30 16:17.
 */
@Entity
@Table(name = "article_tag", uniqueConstraints = {@UniqueConstraint(columnNames = {"article_id", "tag_id"})})
public class ArticleTagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "article_id", nullable = false)
    private ArticleEntity article;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "tag_id", nullable = false)
    private TagEntity tag;
}
