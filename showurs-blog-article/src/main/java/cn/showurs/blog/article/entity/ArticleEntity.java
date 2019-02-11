package cn.showurs.blog.article.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CJ on 2019/1/24 11:09.
 */
@Entity
@Table(name = "article")
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false, length = 512)
    private String title;

    @Column(nullable = false, length = 10240)
    private String content;

    @Column(nullable = false)
    private LocalDateTime createTime;

    @Column
    private LocalDateTime lastEditTime;

    @Column
    private LocalDateTime lastRePlyTime;

    @Column
    private String titleImage;

    @Column
    private Long clickCount;

    @Column(nullable = false, unique = true)
    private Long userId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "sort_id", nullable = false)
    private SortEntity sort;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "article")
    private List<CollectArticleEntity> collectArticles = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "article")
    private List<LikeArticleEntity> likeArticles = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "article")
    private List<ArticleTagEntity> articleTags = new ArrayList<>();
}
