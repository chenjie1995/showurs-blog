package cn.showurs.blog.article.repository;

import cn.showurs.blog.article.entity.ArticleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by CJ on 2019/2/13 10:54.
 */
@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {
}
