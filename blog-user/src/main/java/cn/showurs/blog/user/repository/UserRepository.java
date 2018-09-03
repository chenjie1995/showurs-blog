package cn.showurs.blog.user.repository;

import cn.showurs.blog.user.po.UserPo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by CJ on 2018/9/3 9:41.
 */
public interface UserRepository extends JpaRepository<UserPo, Integer> {
}
