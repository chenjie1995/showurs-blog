package cn.showurs.blog.user.service.impl;

import cn.showurs.blog.common.core.repository.GenericRepository;
import cn.showurs.blog.common.core.service.impl.GenericServiceImpl;
import cn.showurs.blog.common.util.AssertUtils;
import cn.showurs.blog.common.vo.user.Role;
import cn.showurs.blog.user.entity.RoleEntity;
import cn.showurs.blog.user.entity.UserEntity;
import cn.showurs.blog.user.entity.UserRoleEntity;
import cn.showurs.blog.user.repository.RoleRepository;
import cn.showurs.blog.user.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class RoleServiceImpl extends GenericServiceImpl<RoleEntity, Role, Long> implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public GenericRepository<RoleEntity, Long> getRepository() {
        return roleRepository;
    }

    @Override
    public List<String> getRoleNames(UserEntity userEntity) {
        AssertUtils.notNull(userEntity, "userEntity不能为空");

        final List<UserRoleEntity> userRoleEntities = userEntity.getUserRoleEntities();
        AssertUtils.notNull(userRoleEntities, "userRoleEntities不能为空");

        return userRoleEntities.stream()
                .map(UserRoleEntity::getRoleEntity)
                .map(RoleEntity::getName)
                .collect(Collectors.toList());
    }
}
