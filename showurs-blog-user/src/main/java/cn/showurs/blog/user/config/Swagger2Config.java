package cn.showurs.blog.user.config;

import cn.showurs.blog.common.constant.RequestInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CJ on 2018/11/5 10:29.
 * Swagger2基本信息配置
 */
@EnableSwagger2
@Configuration
public class Swagger2Config {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.showurs.blog.user"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(tokenHeader());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Showurs Blog User服务文档")
                .description("User模块微服务接口文档")
                .termsOfServiceUrl("https://github.com/chenjie1995/showurs-blog/tree/master/showurs-blog-user")
                .version("1.0")
                .contact(new Contact("ChenJie", "https://github.com/chenjie1995", "chenjie1995@live.com"))
                .build();
    }

    private List<Parameter> tokenHeader() {
        List<Parameter> parameters = new ArrayList<>();
        ParameterBuilder tokenPar = new ParameterBuilder();
        tokenPar.name(RequestInfo.HEADER_TOKEN_NAME)
                .description("用户TOKEN")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();
        parameters.add(tokenPar.build());
        return parameters;
    }
}
