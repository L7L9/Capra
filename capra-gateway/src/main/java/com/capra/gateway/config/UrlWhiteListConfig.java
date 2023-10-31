package com.capra.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * URL的白名单
 * 不需要鉴权，直接放行
 *
 * @author lql
 * @date 2023/10/26
 */
@Data
@Component
@RefreshScope
@ConfigurationProperties(prefix="security.ignore")
public class UrlWhiteListConfig {
    private List<String> urls;

    private final int initialLength = urls.size();
}