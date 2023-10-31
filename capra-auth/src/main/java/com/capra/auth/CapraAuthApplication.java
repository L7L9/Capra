package com.capra.auth;

import com.capra.api.client.AccountClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author lql
 * @date 2023/10/23
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(clients = AccountClient.class)
public class CapraAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(CapraAuthApplication.class,args);
    }
}
