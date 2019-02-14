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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(LocalDateTime lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public LocalDateTime getLastRePlyTime() {
        return lastRePlyTime;
    }

    public void setLastRePlyTime(LocalDateTime lastRePlyTime) {
        this.lastRePlyTime = lastRePlyTime;
    }

    public String getTitleImage() {
        return titleImage;
    }

    public void setTitleImage(String titleImage) {
        this.titleImage = titleImage;
    }

    public Long getClickCount() {
        return clickCount;
    }

    public void setClickCount(Long clickCount) {
        this.clickCount = clickCount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public SortEntity getSort() {
        return sort;
    }

    public void setSort(SortEntity sort) {
        this.sort = sort;
    }

    public List<CollectArticleEntity> getCollectArticles() {
        return collectArticles;
    }

    public void setCollectArticles(List<CollectArticleEntity> collectArticles) {
        this.collectArticles = collectArticles;
    }

    public List<LikeArticleEntity> getLikeArticles() {
        return likeArticles;
    }

    public void setLikeArticles(List<LikeArticleEntity> likeArticles) {
        this.likeArticles = likeArticles;
    }

    public List<ArticleTagEntity> getArticleTags() {
        return articleTags;
    }

    public void setArticleTags(List<ArticleTagEntity> articleTags) {
        this.articleTags = articleTags;
    }
}
