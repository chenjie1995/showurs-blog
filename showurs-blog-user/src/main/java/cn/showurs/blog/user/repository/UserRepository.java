package cn.showurs.blog.user.repository;

import cn.showurs.blog.common.core.repository.GenericRepository;
import cn.showurs.blog.user.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends GenericRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByEmail(String email);
}
