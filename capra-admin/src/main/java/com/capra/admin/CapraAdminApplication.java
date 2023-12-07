package com.capra.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 *
 * @author lql
 * @date 2023/12/06
 */
@SpringBootApplication(scanBasePackages = "com.capra")
public class CapraAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(CapraAdminApplication.class,args);
    }
}
