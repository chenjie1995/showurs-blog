package cn.showurs.blog.article.config;

import cn.showurs.blog.common.constant.ResponseInfo;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .exposedHeaders(ResponseInfo.HEADER_TOKEN_NAME)
                .allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH", "OPTIONS");
    }
}
