package cn.showurs.blog.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class ShowursBlogConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShowursBlogConfigApplication.class, args);
    }
}
