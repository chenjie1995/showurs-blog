package cn.showurs.blog.user.service.impl;

import cn.showurs.blog.common.core.repository.GenericRepository;
import cn.showurs.blog.common.core.service.impl.GenericServiceImpl;
import cn.showurs.blog.common.vo.user.Role;
import cn.showurs.blog.user.entity.RoleEntity;
import cn.showurs.blog.user.repository.RoleRepository;
import cn.showurs.blog.user.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by CJ on 2018/12/24 16:23.
 */
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
}
