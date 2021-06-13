package cn.showurs.blog.common.vo.article;

import java.time.LocalDateTime;

public class Article {
    private Long id;

    private String title;

    private String content;

    private LocalDateTime createTime;

    private LocalDateTime lastEditTime;

    private LocalDateTime lastRePlyTime;

    private String titleImage;

    private Long clickCount;

    private Long author;

    private Sort sort;

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

    public Long getAuthor() {
        return author;
    }

    public void setAuthor(Long author) {
        this.author = author;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }
}
