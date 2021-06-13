package cn.showurs.blog.common.vo.article;

import java.util.List;

public class ArticlePublish {

    private String title;

    private String content;

    private String titleImage;

    private Long sortId;

    private List<TagPublish> tagPublishes;

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

    public String getTitleImage() {
        return titleImage;
    }

    public void setTitleImage(String titleImage) {
        this.titleImage = titleImage;
    }

    public Long getSortId() {
        return sortId;
    }

    public void setSortId(Long sortId) {
        this.sortId = sortId;
    }

    public List<TagPublish> getTagPublishes() {
        return tagPublishes;
    }

    public void setTagPublishes(List<TagPublish> tagPublishes) {
        this.tagPublishes = tagPublishes;
    }
}
