package cn.showurs.blog.user.config.security;

import cn.showurs.blog.user.entity.UserEntity;
import cn.showurs.blog.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserDetailsJpaService implements UserDetailsService {
    
    private static final Logger log = LoggerFactory.getLogger(UserDetailsJpaService.class);

    private final UserService userService;

    public UserDetailsJpaService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(username)) {
            log.error("获取用户错误，用户名为空");
            throw new UsernameNotFoundException("用户名为空");
        }

        final UserEntity userEntity = userService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("用户不存在"));

        return userService.buildSecurityUser(userEntity);
    }
}
