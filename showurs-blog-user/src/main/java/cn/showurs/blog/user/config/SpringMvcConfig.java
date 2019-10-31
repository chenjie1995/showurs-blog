package cn.showurs.blog.user.config;

import cn.showurs.blog.common.constant.RequestInfo;
import cn.showurs.blog.common.constant.ResponseInfo;
import cn.showurs.blog.user.config.interceptor.AuthInterceptor;
import cn.showurs.blog.user.config.resolver.CurrentUserArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * Created by CJ on 2018/11/18 21:42.
 */
@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    // 解析Token的拦截器
    @Bean
    public HandlerInterceptor authInterceptor() {
        return new AuthInterceptor();
    }

    // 获取Token信息的参数解析器
    @Bean
    public HandlerMethodArgumentResolver currentUserArgumentResolver() {
        return new CurrentUserArgumentResolver();
    }

    // 添加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor())
                .addPathPatterns("/**");
    }

    // 添加参数解析器
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(currentUserArgumentResolver());
    }

    // 跨域放行
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .exposedHeaders(ResponseInfo.HEADER_TOKEN_NAME)
                .allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH", "OPTIONS");
    }
}
