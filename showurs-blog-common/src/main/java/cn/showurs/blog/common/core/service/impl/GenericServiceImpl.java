package cn.showurs.blog.common.core.service.impl;

import cn.showurs.blog.common.core.entity.GenericEntity;
import cn.showurs.blog.common.core.service.GenericService;
import cn.showurs.blog.common.exception.BusinessException;
import cn.showurs.blog.common.vo.common.GenericValue;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class GenericServiceImpl<T extends GenericEntity<ID>, V extends GenericValue<ID>, ID extends Serializable> implements GenericService<T, V, ID> {

    private static final int P_INDEX = 0;
    private static final int V_INDEX = 1;

    @Override
    public List<T> findAll() {
        return getRepository().findAll();
    }

    @Override
    public List<T> findAll(Sort sort) {
        return getRepository().findAll(sort);
    }

    @Override
    public List<T> findAllById(Iterable<ID> ids) {
        return getRepository().findAllById(ids);
    }

    @Override
    public <S extends T> List<S> saveAll(Iterable<S> entities) {
        return getRepository().saveAll(entities);
    }

    @Override
    public void flush() {
        getRepository().flush();
    }

    @Override
    public <S extends T> S saveAndFlush(S entity) {
        return getRepository().saveAndFlush(entity);
    }

    @Override
    public void deleteInBatch(Iterable<T> entities) {
        getRepository().deleteInBatch(entities);
    }

    @Override
    public void deleteAllInBatch() {
        getRepository().deleteAllInBatch();
    }

    @Override
    public T getOne(ID id) {
        return getRepository().getOne(id);
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example) {
        return getRepository().findAll(example);
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
        return getRepository().findAll(example, sort);
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return getRepository().findAll(pageable);
    }

    @Override
    public <S extends T> S save(S entity) {
        return getRepository().save(entity);
    }

    @Override
    public Optional<T> findById(ID id) {
        return getRepository().findById(id);
    }

    @Override
    public boolean existsById(ID id) {
        return getRepository().existsById(id);
    }

    @Override
    public long count() {
        return getRepository().count();
    }

    @Override
    public void deleteById(ID id) {
        getRepository().deleteById(id);
    }

    @Override
    public void delete(T entity) {
        getRepository().delete(entity);
    }

    @Override
    public void deleteAll(Iterable<? extends T> entities) {
        getRepository().deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        getRepository().deleteAll();
    }

    @Override
    public Optional<T> findOne(Specification<T> spec) {
        return getRepository().findOne(spec);
    }

    @Override
    public List<T> findAll(Specification<T> spec) {
        return getRepository().findAll(spec);
    }

    @Override
    public Page<T> findAll(Specification<T> spec, Pageable pageable) {
        return getRepository().findAll(spec, pageable);
    }

    @Override
    public List<T> findAll(Specification<T> spec, Sort sort) {
        return getRepository().findAll(spec, sort);
    }

    @Override
    public long count(Specification<T> spec) {
        return getRepository().count(spec);
    }

    @Override
    public <VO> T voToPo(VO vo) {
        if (vo == null) {
            return null;
        }

        try {
            T po = getPClass().newInstance();
            BeanUtils.copyProperties(vo, po);
            return po;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new BusinessException("Bean转换异常", e);
        }
    }

    @Override
    public <VO> Optional<T> voToPoOptional(VO vo) {
        return Optional.ofNullable(voToPo(vo));
    }

    @Override
    public <VO> List<T> vosToPos(List<VO> vos) {
        Assert.notNull(vos, "VO集合不能为NULL");
        return vos.stream().map(this::voToPo).collect(Collectors.toList());
    }

    @Override
    public <VO> VO poToVo(T po, Class<VO> voClass) {
        if (po == null) {
            return null;
        }

        try {
            VO vo = voClass.newInstance();
            BeanUtils.copyProperties(po, vo);
            return vo;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new BusinessException("Bean转换异常", e);
        }
    }

    @Override
    public <VO> Optional<VO> poToVoOptional(T po, Class<VO> voClass) {
        return Optional.ofNullable(poToVo(po, voClass));
    }

    @Override
    public V poToVo(T po) {
        return poToVo(po, getVClass());
    }

    @Override
    public Optional<V> poToVoOptional(T po) {
        return poToVoOptional(po, getVClass());
    }

    @Override
    public <VO> List<VO> posToVos(List<T> pos, Class<VO> voClass) {
        Assert.notNull(pos, "PO集合不能为NULL");
        return pos.stream().map(po -> poToVo(po, voClass)).collect(Collectors.toList());
    }

    @Override
    public List<V> posToVos(List<T> pos) {
        return posToVos(pos, getVClass());
    }

    @SuppressWarnings("unchecked")
    private Class<T> getPClass() {
        return (Class<T>) getParamsClass(P_INDEX);
    }

    @SuppressWarnings("unchecked")
    private Class<V> getVClass() {
        return (Class<V>) getParamsClass(V_INDEX);
    }

    /**
     * 获取其子类赋予的泛型类型
     *
     * @param i 泛型下标
     * @return Class
     */
    private Class<?> getParamsClass(int i) {
        Type superclass = getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) superclass;
        Type[] params = parameterizedType.getActualTypeArguments();
        return (Class<?>) params[i];
    }
}
