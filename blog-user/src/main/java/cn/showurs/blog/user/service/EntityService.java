package cn.showurs.blog.user.service;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CJ on 2018/8/29 15:58.
 */
public abstract class EntityService<P, V> {
    private static final int P_INDEX = 0;
    private static final int V_INDEX = 1;

    /**
     * 值对象转持久化对象
     * @param vo 值对象
     * @return P
     */
    public P voToPo(V vo) {
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

    /**
     * 持久化对象转值对象
     * @param po 持久化对象
     * @return V
     */
    public V poToVo(P po) {
        if (po == null) {
            return null;
        }
        V vo = null;
        try {
            vo = getVClass().newInstance();
            BeanUtils.copyProperties(po, vo);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return vo;
    }

    /**
     * 持久化对象列表转值对象列表
     * @param pos 持久化对象列表
     * @return 值对象列表
     */
    public List<V> posToVos(List<P> pos) {
        List<V> vos = new ArrayList<>();
        pos.forEach(po -> vos.add(poToVo(po)));
        return vos;
    }

    /**
     * 值对象列表转持久化对象列表
     * @param vos 值对象列表
     * @return 持久化对象列表
     */
    public List<P> vosToPos(List<V> vos) {
        List<P> pos = new ArrayList<>();
        vos.forEach(vo -> pos.add(voToPo(vo)));
        return pos;
    }

    /**
     * 获取P的类型
     * @return Class
     */
    @SuppressWarnings("unchecked")
    private Class<P> getPClass() {
        return (Class<P>) getParamsClass(P_INDEX);
    }

    /**
     * 获取V的类型
     * @return Class
     */
    @SuppressWarnings("unchecked")
    private Class<V> getVClass() {
        return (Class<V>) getParamsClass(V_INDEX);
    }

    /**
     * 获取其子类赋予的泛型类型
     * @param i 下标
     * @return Class
     */
    private Class getParamsClass(int i) {
        Type superclass = getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) superclass;
        Type[] params = parameterizedType.getActualTypeArguments();
        return (Class)params[i];
    }
}
