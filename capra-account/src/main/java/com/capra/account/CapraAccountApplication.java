package com.capra.account;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author lql
 * @date 2023/10/24
 */
@MapperScan("com.capra.account.mapper")
@SpringBootApplication
@EnableDiscoveryClient
public class CapraAccountApplication {
    public static void main(String[] args) {
        SpringApplication.run(CapraAccountApplication.class,args);
    }
}
