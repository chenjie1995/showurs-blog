package cn.showurs.blog.article;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = {"cn.showurs.blog.article.client"})
@SpringBootApplication
public class BlogArticleApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogArticleApplication.class, args);
    }
}
