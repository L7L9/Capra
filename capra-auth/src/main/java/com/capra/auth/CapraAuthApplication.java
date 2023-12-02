package com.capra.auth;

import com.capra.api.client.UserClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author lql
 * @date 2023/10/23
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.capra")
@EnableDiscoveryClient
@EnableFeignClients(clients = UserClient.class)
public class CapraAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(CapraAuthApplication.class,args);
    }
}
