package cn.showurs.blog.user.service;

import cn.showurs.blog.common.core.service.GenericService;
import cn.showurs.blog.common.vo.user.Power;
import cn.showurs.blog.user.entity.PowerEntity;
import cn.showurs.blog.user.entity.UserEntity;

import java.util.List;

public interface PowerService extends GenericService<PowerEntity, Power, Long> {

    List<String> getPowerNames(UserEntity userEntity);
}
