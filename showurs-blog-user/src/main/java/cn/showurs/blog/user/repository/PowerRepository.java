package cn.showurs.blog.user.repository;

import cn.showurs.blog.common.core.repository.GenericRepository;
import cn.showurs.blog.user.entity.PowerEntity;

import java.util.Optional;

public interface PowerRepository extends GenericRepository<PowerEntity, Long> {

    Optional<PowerEntity> findByName(String name);
}
