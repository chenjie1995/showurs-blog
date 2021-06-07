package cn.showurs.blog.user.repository;

import cn.showurs.blog.common.core.repository.GenericRepository;
import cn.showurs.blog.user.entity.RoleEntity;

import java.util.Optional;

/**
 * Created by CJ on 2018/12/7 22:02.
 */
public interface RoleRepository extends GenericRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(String name);
}
