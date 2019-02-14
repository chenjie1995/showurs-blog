package cn.showurs.blog.article.repository;

import cn.showurs.blog.article.entity.LikeArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by CJ on 2019/2/13 15:58.
 */
public interface LikeArticleRepository extends JpaRepository<LikeArticleEntity, Long> {
}
