package cn.showurs.blog.user.service;

import cn.showurs.blog.common.core.service.GenericService;
import cn.showurs.blog.common.vo.user.Role;
import cn.showurs.blog.user.entity.RoleEntity;
import cn.showurs.blog.user.entity.UserEntity;

import java.util.List;

public interface RoleService extends GenericService<RoleEntity, Role, Long> {

    List<String> getRoleNames(UserEntity userEntity);
}
