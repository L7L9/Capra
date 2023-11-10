package com.capra.article;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 文章模块启动类
 * @author lql
 * @date 2023/11/07
 */
@MapperScan("com.capra.article.mapper")
@SpringBootApplication
public class CapraArticleApplication {
    public static void main(String[] args) {
        SpringApplication.run(CapraArticleApplication.class,args);
    }
}