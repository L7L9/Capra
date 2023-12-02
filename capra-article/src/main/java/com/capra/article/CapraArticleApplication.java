package com.capra.article;

import com.capra.api.client.FileClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 文章模块启动类
 * @author lql
 * @date 2023/11/07
 */
@MapperScan("com.capra.article.mapper")
@SpringBootApplication
@EnableFeignClients(clients = FileClient.class)
public class CapraArticleApplication {
    public static void main(String[] args) {
        SpringApplication.run(CapraArticleApplication.class,args);
    }
}