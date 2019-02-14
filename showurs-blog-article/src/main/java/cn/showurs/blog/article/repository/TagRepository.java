package cn.showurs.blog.article.repository;

import cn.showurs.blog.article.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by CJ on 2019/2/13 16:00.
 */
@Repository
public interface TagRepository extends JpaRepository<TagEntity, Long> {
}
