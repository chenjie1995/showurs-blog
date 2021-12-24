package cn.showurs.blog.user.service.impl;

import cn.showurs.blog.common.core.repository.GenericRepository;
import cn.showurs.blog.common.core.service.impl.GenericServiceImpl;
import cn.showurs.blog.common.util.AssertUtils;
import cn.showurs.blog.common.vo.user.Power;
import cn.showurs.blog.user.entity.*;
import cn.showurs.blog.user.repository.PowerRepository;
import cn.showurs.blog.user.service.PowerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class PowerServiceImpl extends GenericServiceImpl<PowerEntity, Power, Long> implements PowerService {

    private final PowerRepository powerRepository;

    public PowerServiceImpl(PowerRepository powerRepository) {
        this.powerRepository = powerRepository;
    }

    @Override
    public GenericRepository<PowerEntity, Long> getRepository() {
        return powerRepository;
    }

    @Override
    public List<String> getPowerNames(UserEntity userEntity) {
        AssertUtils.notNull(userEntity, "userEntity不能为空");

        final List<UserRoleEntity> userRoleEntities = userEntity.getUserRoleEntities();
        AssertUtils.notNull(userRoleEntities, "userRoleEntities不能为空");

        return userRoleEntities.stream()
                .map(UserRoleEntity::getRoleEntity)
                .map(RoleEntity::getRolePowerEntities)
                .flatMap(Collection::stream)
                .map(RolePowerEntity::getPowerEntity)
                .map(PowerEntity::getName)
                .collect(Collectors.toList());
    }
}
