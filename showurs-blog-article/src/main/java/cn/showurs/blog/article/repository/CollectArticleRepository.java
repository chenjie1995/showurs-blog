package cn.showurs.blog.article.repository;

import cn.showurs.blog.article.entity.CollectArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by CJ on 2019/2/13 15:57.
 */
@Repository
public interface CollectArticleRepository extends JpaRepository<CollectArticleEntity, Long> {
}
