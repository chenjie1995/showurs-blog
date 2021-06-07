package cn.showurs.blog.user.repository;

import cn.showurs.blog.common.core.repository.GenericRepository;
import cn.showurs.blog.user.entity.PowerEntity;

import java.util.Optional;

/**
 * Created by CJ on 2018/12/7 22:04.
 */
public interface PowerRepository extends GenericRepository<PowerEntity, Long> {

    Optional<PowerEntity> findByName(String name);
}
