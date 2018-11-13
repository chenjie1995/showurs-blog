package cn.showurs.blog.user.repository;

import cn.showurs.blog.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by CJ on 2018/11/6 22:29.
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
