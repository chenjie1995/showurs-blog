package cn.showurs.blog.user.config.security;

import cn.showurs.blog.user.config.filter.JsonUsernamePasswordAuthenticationFilter;
import cn.showurs.blog.user.config.security.handler.AccessDeniedJsonHandler;
import cn.showurs.blog.user.config.security.handler.JsonAuthenticationFailureHandler;
import cn.showurs.blog.user.config.security.handler.JwtAuthenticationSuccessHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring Security 配置
 */
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final ObjectMapper objectMapper;

    public SpringSecurityConfig(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * 具体配置
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 禁用CSRF
        http.csrf().disable();

        // 排除相关资源
        http.authorizeRequests()
                // 接口放行
                .antMatchers().permitAll()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated();

        // 未认证未授权处理
        http.exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint(objectMapper))
                .accessDeniedHandler(accessDeniedHandler(objectMapper));

        // 登录认证处理
        http.formLogin();

        // 替换Filter
        http.addFilterAt(jsonUsernamePasswordAuthenticationFilter(objectMapper), UsernamePasswordAuthenticationFilter.class);

        // 禁用session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

    /**
     * Json请求体账号密码方式Filter
     * @param objectMapper Jackson {@link ObjectMapper}
     * @return Json请求体账号密码方式Filter
     */
    @Bean
    public JsonUsernamePasswordAuthenticationFilter jsonUsernamePasswordAuthenticationFilter(ObjectMapper objectMapper) throws Exception {
        final JsonUsernamePasswordAuthenticationFilter filter = new JsonUsernamePasswordAuthenticationFilter(objectMapper);
        filter.setAuthenticationManager(authenticationManagerBean());
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
        filter.setAuthenticationFailureHandler(authenticationFailureHandler());
        return filter;
    }

    /**
     * Json方式的未授权处理
     * @param objectMapper Jackson {@link ObjectMapper}
     * @return Json方式的未授权处理
     */
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint(ObjectMapper objectMapper) {
        return new ContentTypeJsonEntryPoint(objectMapper);
    }

    /**
     * Json方式的未认证处理
     * @param objectMapper Jackson {@link ObjectMapper}
     * @return Json方式的未认证处理
     */
    @Bean
    public AccessDeniedHandler accessDeniedHandler(ObjectMapper objectMapper) {
        return new AccessDeniedJsonHandler(objectMapper);
    }

    /**
     * 认证成功Handler
     * @return 认证成功Handler
     */
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new JwtAuthenticationSuccessHandler();
    }

    /**
     * 认证失败Handler
     * @return 认证失败Handler
     */
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new JsonAuthenticationFailureHandler();
    }
}
