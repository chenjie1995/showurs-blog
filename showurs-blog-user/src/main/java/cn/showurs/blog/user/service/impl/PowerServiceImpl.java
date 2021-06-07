package cn.showurs.blog.user.service.impl;

import cn.showurs.blog.common.core.repository.GenericRepository;
import cn.showurs.blog.common.core.service.impl.GenericServiceImpl;
import cn.showurs.blog.user.entity.PowerEntity;
import cn.showurs.blog.user.repository.PowerRepository;
import cn.showurs.blog.user.service.PowerService;
import cn.showurs.blog.common.vo.user.Power;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by CJ on 2018/12/24 16:26.
 */
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


}
