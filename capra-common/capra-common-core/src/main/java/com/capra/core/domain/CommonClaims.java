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
    /**
     * 用户id
     */
    private Long userId;

    /**
     * uuid
     */
    private String uuid;

    /**
     * 用户名
     */
    private String username;
}