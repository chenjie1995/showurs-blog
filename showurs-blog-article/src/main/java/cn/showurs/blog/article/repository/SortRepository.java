package cn.showurs.blog.article.repository;

import cn.showurs.blog.article.entity.SortEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by CJ on 2019/2/13 15:59.
 */
@Repository
public interface SortRepository extends JpaRepository<SortEntity, Long> {
}
