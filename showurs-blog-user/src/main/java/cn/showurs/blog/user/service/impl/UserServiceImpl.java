package cn.showurs.blog.user.service.impl;

import cn.showurs.blog.user.entity.UserEntity;
import cn.showurs.blog.user.entity.UserRoleEntity;
import cn.showurs.blog.user.repository.UserRepository;
import cn.showurs.blog.user.service.RoleService;
import cn.showurs.blog.user.service.UserService;
import cn.showurs.blog.user.vo.Role;
import cn.showurs.blog.user.vo.User;
import cn.showurs.blog.user.vo.UserRegister;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by CJ on 2018/11/18 21:58.
 */
@Service
public class UserServiceImpl extends EntityServiceImpl<UserEntity, User, Long> implements UserService {
    private UserRepository userRepository;
    private RoleService roleService;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    @Cacheable(value = "users", key = "#id", unless = "#result == null")
    @Override
    public User findById(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        return poToVo(userEntity);
    }

    @Transactional
    @Override
    public User register(UserRegister userRegister) {
        return null;
    }

    @Override
    public User poToVo(UserEntity po) {
        if (po == null) {
            return null;
        }

        User user = super.poToVo(po);
        List<Role> roles = roleService.posToVos(po.getUserRoles().stream().map(UserRoleEntity::getRole).collect(Collectors.toList()));

        user.setRoles(roles);

        return super.poToVo(po);
    }
}
