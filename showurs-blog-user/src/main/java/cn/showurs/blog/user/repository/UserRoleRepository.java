package cn.showurs.blog.user.repository;

import cn.showurs.blog.user.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by CJ on 2018/12/7 22:00.
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
}
