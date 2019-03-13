package cn.showurs.blog.article.service;

import cn.showurs.blog.article.entity.ArticleEntity;
import cn.showurs.blog.common.core.EntityService;
import cn.showurs.blog.common.vo.article.Article;
import cn.showurs.blog.common.vo.article.ArticlePublish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by CJ on 2019/2/13 17:00.
 */
public interface ArticleService extends EntityService<ArticleEntity, Article> {
    Article findById(Long id);
    Page<Article> findPage(Pageable pageable);
    Article publish(ArticlePublish articlePublish, Long author);
    void deleteById(Long id);
}
