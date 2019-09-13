package cn.showurs.blog.common.core;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * PO和VO相互转换接口
 * Created by CJ on 2018/12/7 23:37.
 *
 * @param <P> 数据库持久化PO
 * @param <V> 缺省返回的VO对象
 */
public interface EntityService<P, V> {

    /**
     * 值对象转数据库对象
     *
     * @param vo   值对象
     * @param <VO> 值对象泛型
     * @return 数据库对象
     */
    <VO> Optional<P> voToPo(VO vo);

    /**
     * 值对象列表转数据库对象列表
     *
     * @param vos  值对象列表
     * @param <VO> 值对象泛型
     * @return 数据库对象列表
     */
    <VO> List<P> vosToPos(List<VO> vos);

    /**
     * 值对象集合转数据库对象集合
     *
     * @param vos  值对象集合
     * @param <VO> 值对象泛型
     * @return 数据库对象集合
     */
    <VO> Set<P> vosToPos(Set<VO> vos);

    /**
     * 数据库对象转值对象
     *
     * @param po      数据库对象
     * @param voClass 值对象Class
     * @param <VO>    值对象泛型
     * @return 值对象
     */
    <VO> Optional<VO> poToVo(P po, Class<VO> voClass);

    /**
     * 数据库对象转缺省的值对象
     *
     * @param po 数据库对象
     * @return 缺省的值对象
     */
    Optional<V> poToVo(P po);

    /**
     * 数据库对象列表转值对象列表
     *
     * @param pos     数据库对象列表
     * @param voClass 值对象Class
     * @param <VO>    值对象泛型
     * @return 值对象列表
     */
    <VO> List<VO> posToVos(List<P> pos, Class<VO> voClass);

    /**
     * 数据库对象列表转缺省的值对象列表
     *
     * @param pos 数据库对象列表
     * @return 缺省的值对象列表
     */
    List<V> posToVos(List<P> pos);

    /**
     * 数据库对象集合转值对象集合
     *
     * @param pos     数据库对象集合
     * @param voClass 值对象Class
     * @param <VO>    值对象泛型
     * @return 值对象集合
     */
    <VO> Set<VO> posToVos(Set<P> pos, Class<VO> voClass);

    /**
     * 数据库对象集合转缺省的值对象集合
     *
     * @param pos 数据库对象集合
     * @return 缺省的值对象集合
     */
    Set<V> posToVos(Set<P> pos);
}
