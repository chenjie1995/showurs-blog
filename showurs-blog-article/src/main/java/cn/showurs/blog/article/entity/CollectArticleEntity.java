package cn.showurs.blog.article.entity;

import javax.persistence.*;

/**
 * Created by CJ on 2019/1/28 14:31.
 */
@Entity
@Table(name = "collect_article", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "article_id"})})
public class CollectArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "article_id", nullable = false)
    private ArticleEntity article;
}