package cn.showurs.blog.user.service.impl;

import cn.showurs.blog.common.core.impl.EntityServiceImpl;
import cn.showurs.blog.user.entity.RoleEntity;
import cn.showurs.blog.user.entity.RolePowerEntity;
import cn.showurs.blog.user.service.PowerService;
import cn.showurs.blog.user.service.RoleService;
import cn.showurs.blog.common.vo.user.Power;
import cn.showurs.blog.common.vo.user.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by CJ on 2018/12/24 16:23.
 */
@Service
public class RoleServiceImpl extends EntityServiceImpl<RoleEntity, Role, Long> implements RoleService {
    private PowerService powerService;

    public RoleServiceImpl(PowerService powerService) {
        this.powerService = powerService;
    }

    @Override
    public Role poToVo(RoleEntity po) {
        if (po == null) {
            return null;
        }

        Role role = super.poToVo(po);
        List<Power> powers = powerService.posToVos(po.getRolePowers().stream().map(RolePowerEntity::getPower).collect(Collectors.toList()));
        role.setPowers(powers);

        return role;
    }
}
