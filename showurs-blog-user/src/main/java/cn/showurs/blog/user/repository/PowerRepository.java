package cn.showurs.blog.user.repository;

import cn.showurs.blog.user.entity.PowerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by CJ on 2018/12/7 22:04.
 */
@Repository
public interface PowerRepository extends JpaRepository<PowerEntity, Long> {
}
