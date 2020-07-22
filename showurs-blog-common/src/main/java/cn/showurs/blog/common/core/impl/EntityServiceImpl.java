package cn.showurs.blog.common.core.impl;

import cn.showurs.blog.common.core.EntityService;
import cn.showurs.blog.common.exception.BusinessException;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

/**
 * PO和VO互相转换抽象实现类
 * Created by CJ on 2018/12/7 23:48.
 *
 * @param <P> 数据库持久化PO
 * @param <V> 缺省返回的VO
 */
public abstract class EntityServiceImpl<P, V> implements EntityService<P, V> {
    private static final int P_INDEX = 0;
    private static final int V_INDEX = 1;

    @Override
    public <VO> P voToPo(VO vo) {
        if (vo == null) {
            return null;
        }
        P po;
        try {
            po = getPClass().newInstance();
            BeanUtils.copyProperties(vo, po);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new BusinessException("Bean转换错误");
        }
        return po;
    }

    @Override
    public <VO> Optional<P> voToPoOptional(VO vo) {
        return Optional.ofNullable(voToPo(vo));
    }

    @Override
    public <VO> List<P> vosToPos(List<VO> vos) {
        if (vos == null) {
            return new ArrayList<>();
        }
        return vos.stream().map(this::voToPo).collect(Collectors.toList());
    }

    @Override
    public <VO> Set<P> vosToPos(Set<VO> vos) {
        if (vos == null) {
            return new HashSet<>();
        }
        return vos.stream().map(this::voToPo).collect(Collectors.toSet());
    }

    @Override
    public <VO> VO poToVo(P po, Class<VO> voClass) {
        if (po == null) {
            return null;
        }
        VO vo;
        try {
            vo = voClass.newInstance();
            BeanUtils.copyProperties(po, vo);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new BusinessException("Bean转换错误");
        }
        return vo;
    }

    @Override
    public <VO> Optional<VO> poToVoOptional(P po, Class<VO> voClass) {
        return Optional.ofNullable(poToVo(po, voClass));
    }

    @Override
    public V poToVo(P po) {
        return poToVo(po, getVClass());
    }

    @Override
    public Optional<V> poToVoOptional(P po) {
        return poToVoOptional(po, getVClass());
    }

    @Override
    public <VO> List<VO> posToVos(List<P> pos, Class<VO> voClass) {
        if (pos == null) {
            return new ArrayList<>();
        }
        return pos.stream().map(po -> poToVo(po, voClass)).collect(Collectors.toList());
    }

    @Override
    public List<V> posToVos(List<P> pos) {
        return posToVos(pos, getVClass());
    }

    @Override
    public <VO> Set<VO> posToVos(Set<P> pos, Class<VO> voClass) {
        if (pos == null) {
            return new HashSet<>();
        }
        return pos.stream().map(po -> poToVo(po, voClass)).collect(Collectors.toSet());
    }

    @Override
    public Set<V> posToVos(Set<P> pos) {
        return posToVos(pos, getVClass());
    }

    /**
     * 获取P的类型
     *
     * @return Class
     */
    @SuppressWarnings("unchecked")
    private Class<P> getPClass() {
        return (Class<P>) getParamsClass(P_INDEX);
    }

    /**
     * 获取V的类型
     *
     * @return Class
     */
    @SuppressWarnings("unchecked")
    private Class<V> getVClass() {
        return (Class<V>) getParamsClass(V_INDEX);
    }

    /**
     * 获取其子类赋予的泛型类型
     *
     * @param i 下标
     * @return Class
     */
    private Class<?> getParamsClass(int i) {
        Type superclass = getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) superclass;
        Type[] params = parameterizedType.getActualTypeArguments();
        return (Class<?>) params[i];
    }
}
