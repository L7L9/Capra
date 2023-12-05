package com.capra.article;

import com.capra.api.client.FileClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 文章模块启动类
 * @author lql
 * @date 2023/11/07
 */
@MapperScan("com.capra.article.mapper")
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = "com.capra")
@EnableFeignClients(clients = FileClient.class)
public class CapraArticleApplication {
    public static void main(String[] args) {
        SpringApplication.run(CapraArticleApplication.class,args);
    }
}