package cn.showurs.blog.user.service;

import cn.showurs.blog.common.constant.RoleInfo;
import cn.showurs.blog.common.core.EntityService;
import cn.showurs.blog.common.vo.user.Role;
import cn.showurs.blog.user.entity.RoleEntity;

/**
 * Created by CJ on 2018/12/24 16:22.
 */
public interface RoleService extends EntityService<RoleEntity, Role> {
    /**
     * 获取初始的blogger角色
     * @return 初始blogger角色
     */
    static RoleEntity getInitBloggerRole() {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setName(RoleInfo.ROLE_DEFAULT_BLOGGER_NAME);
        roleEntity.setDescription(RoleInfo.ROLE_DEFAULT_BLOGGER_DESCRIPTION);
        return roleEntity;
    }
}
