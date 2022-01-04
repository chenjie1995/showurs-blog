package cn.showurs.blog.user.config.security.handler;

import cn.showurs.blog.user.config.security.SecurityUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用于处理成功用户身份验证的策略
 */
public class JsonAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger log = LoggerFactory.getLogger(JsonAuthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("认证成功: {}", authentication);

        if (response.isCommitted()) {
            log.debug("Response has already been committed");
            return;
        }

        final SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();


    }

}
