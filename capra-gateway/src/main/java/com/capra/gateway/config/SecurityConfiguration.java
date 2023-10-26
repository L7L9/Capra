package com.capra.gateway.config;

import cn.hutool.core.util.ArrayUtil;
import com.capra.gateway.authorization.AuthorizationManager;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * security配置类
 *
 * @author lql
 * @date 2023/10/26
 */
@AllArgsConstructor
@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration{

    private final UrlWhiteListConfig urlWhiteListConfig;

    private final AuthorizationManager authorizationManager;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverHttpSecurity){
        return serverHttpSecurity
                .authorizeExchange()
                .pathMatchers(ArrayUtil.toArray(urlWhiteListConfig.getUrls(),String.class)).permitAll()
                .anyExchange().access(authorizationManager)
                .and()
                .csrf().disable()
                .build();
    }
}
