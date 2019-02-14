package cn.showurs.blog.article.repository;

import cn.showurs.blog.article.entity.ArticleTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by CJ on 2019/2/13 10:59.
 */
@Repository
public interface ArticleTagRepository extends JpaRepository<ArticleTagEntity, Long> {
}
