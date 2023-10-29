package com.capra.core.domain;

import lombok.Data;

/**
 * 存储于jwt中的数据
 *
 * @author lql
 * @date 2023/10/27
 */
@Data
public class CommonClaims {
    private Long userId;

    private String username;
}