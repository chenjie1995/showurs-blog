package cn.showurs.blog.common.core.service;

import cn.showurs.blog.common.core.entity.GenericEntity;
import cn.showurs.blog.common.core.repository.GenericRepository;
import cn.showurs.blog.common.vo.common.GenericValue;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface GenericService<T extends GenericEntity<ID>, V extends GenericValue<ID>, ID> {

    GenericRepository<T, ID> getRepository();

    List<T> findAll();

    List<T> findAll(Sort sort);

    List<T> findAllById(Iterable<ID> ids);

    <S extends T> List<S> saveAll(Iterable<S> entities);

    void flush();

    <S extends T> S saveAndFlush(S entity);

    void deleteInBatch(Iterable<T> entities);

    void deleteAllInBatch();

    T getOne(ID id);

    <S extends T> List<S> findAll(Example<S> example);

    <S extends T> List<S> findAll(Example<S> example, Sort sort);

    Page<T> findAll(Pageable pageable);

    <S extends T> S save(S entity);

    Optional<T> findById(ID id);

    boolean existsById(ID id);

    long count();

    void deleteById(ID id);

    void delete(T entity);

    void deleteAll(Iterable<? extends T> entities);

    void deleteAll();

    Optional<T> findOne(Specification<T> spec);

    List<T> findAll(Specification<T> spec);

    Page<T> findAll(Specification<T> spec, Pageable pageable);

    List<T> findAll(Specification<T> spec, Sort sort);

    long count(Specification<T> spec);

    <VO> T voToPo(VO vo);

    <VO> Optional<T> voToPoOptional(VO vo);

    <VO> List<T> vosToPos(List<VO> vos);

    <VO> VO poToVo(T po, Class<VO> voClass);

    <VO> Optional<VO> poToVoOptional(T po, Class<VO> voClass);

    V poToVo(T po);

    Optional<V> poToVoOptional(T po);

    <VO> List<VO> posToVos(List<T> pos, Class<VO> voClass);

    List<V> posToVos(List<T> pos);
}
