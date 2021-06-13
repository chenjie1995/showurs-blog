package cn.showurs.blog.article.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "article")
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false, length = 512)
    private String title;

    @Column(name = "content", nullable = false, length = 10240)
    private String content;

    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;

    @Column(name = "last_edit_time")
    private LocalDateTime lastEditTime;

    @Column(name = "last_reply_time")
    private LocalDateTime lastReplyTime;

    @Column(name = "title_image")
    private String titleImage;

    @Column(name = "click_count")
    private Long clickCount;

    @Column(name = "author", nullable = false, unique = true)
    private Long author;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "sort_id", nullable = false)
    private SortEntity sort;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "article")
    private Set<CollectArticleEntity> collectArticles = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "article")
    private Set<LikeArticleEntity> likeArticles = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "article")
    private Set<ArticleTagEntity> articleTags = new HashSet<>();

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

    public LocalDateTime getLastReplyTime() {
        return lastReplyTime;
    }

    public void setLastReplyTime(LocalDateTime lastReplyTime) {
        this.lastReplyTime = lastReplyTime;
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

    public Long getAuthor() {
        return author;
    }

    public void setAuthor(Long author) {
        this.author = author;
    }

    public SortEntity getSort() {
        return sort;
    }

    public void setSort(SortEntity sort) {
        this.sort = sort;
    }

    public Set<CollectArticleEntity> getCollectArticles() {
        return collectArticles;
    }

    public void setCollectArticles(Set<CollectArticleEntity> collectArticles) {
        this.collectArticles = collectArticles;
    }

    public Set<LikeArticleEntity> getLikeArticles() {
        return likeArticles;
    }

    public void setLikeArticles(Set<LikeArticleEntity> likeArticles) {
        this.likeArticles = likeArticles;
    }

    public Set<ArticleTagEntity> getArticleTags() {
        return articleTags;
    }

    public void setArticleTags(Set<ArticleTagEntity> articleTags) {
        this.articleTags = articleTags;
    }

}
