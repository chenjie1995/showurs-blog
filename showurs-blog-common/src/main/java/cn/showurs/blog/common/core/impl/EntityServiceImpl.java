package cn.showurs.blog.common.core.impl;

import cn.showurs.blog.common.core.EntityService;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by CJ on 2018/12/7 23:48.
 *
 * @param <P>  数据库持久化PO
 * @param <V>  缺省返回的VO
 */
public abstract class EntityServiceImpl<P, V> implements EntityService<P, V> {
    private static final int P_INDEX = 0;
    private static final int V_INDEX = 1;

    public <VO> P voToPo(VO vo) {
        if (vo == null) {
            return null;
        }
        P po = null;
        try {
            po = getPClass().newInstance();
            BeanUtils.copyProperties(vo, po);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return po;
    }

    public <VO> List<P> vosToPos(List<VO> vos) {
        return vos.stream().map(this::voToPo).collect(Collectors.toList());
    }

    public <VO> VO poToVo(P po, Class<VO> voClass) {
        if (po == null) {
            return null;
        }
        VO vo = null;
        try {
            vo = voClass.newInstance();
            BeanUtils.copyProperties(po, vo);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return vo;
    }

    public V poToVo(P po) {
        return poToVo(po, getVClass());
    }

    public <VO> List<VO> posToVos(List<P> pos, Class<VO> voClass) {
        return pos.stream().map(po -> poToVo(po, voClass)).collect(Collectors.toList());
    }

    public List<V> posToVos(List<P> pos) {
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
    private Class getParamsClass(int i) {
        Type superclass = getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) superclass;
        Type[] params = parameterizedType.getActualTypeArguments();
        return (Class) params[i];
    }
}
