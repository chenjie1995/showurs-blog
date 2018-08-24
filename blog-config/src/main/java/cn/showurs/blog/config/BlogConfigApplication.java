package cn.showurs.blog.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class BlogConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogConfigApplication.class, args);
    }
}
