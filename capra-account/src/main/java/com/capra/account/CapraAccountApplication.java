package com.capra.account;


import com.capra.api.client.FileClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author lql
 * @date 2023/10/24
 */
@MapperScan("com.capra.account.mapper")
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(clients = {FileClient.class})
public class CapraAccountApplication {
    public static void main(String[] args) {
        SpringApplication.run(CapraAccountApplication.class,args);
    }
}
