package com.capra.gateway;

import com.capra.api.client.AuthClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 *
 * @author lql
 * @date 2023/10/23
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(clients = AuthClient.class)
public class CapraGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(CapraGatewayApplication.class,args);
    }
}
