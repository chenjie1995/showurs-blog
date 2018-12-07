package cn.showurs.blog.user.repository;

import cn.showurs.blog.user.entity.RolePowerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by CJ on 2018/12/7 22:02.
 */
@Repository
public interface RolePowerRepository extends JpaRepository<RolePowerEntity, Long> {
}
