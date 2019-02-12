package cn.showurs.blog.article.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by CJ on 2019/2/11 15:18.
 */
@EnableSwagger2
@Configuration
public class Swagger2Config {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.showurs.blog.article"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Showurs Blog Article服务文档")
                .description("Article模块微服务接口文档")
                .termsOfServiceUrl("https://github.com/chenjie1995/showurs-blog/tree/master/showurs-blog-article")
                .version("1.0")
                .contact(new Contact("ChenJie", "https://github.com/chenjie1995", "chenjie1995@live.com"))
                .build();
    }
}
