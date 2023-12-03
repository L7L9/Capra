package com.capra.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author lql
 * @date 2023/10/23
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.capra")
@EnableDiscoveryClient
public class CapraGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(CapraGatewayApplication.class,args);
    }
}
