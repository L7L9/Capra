package com.capra.security.domain;

import lombok.Data;

/**
 * 存储于jwt中的数据
 *
 * @author lql
 * @date 2023/10/27
 */
@Data
public class MyClaims {
    private Long userId;

    private String username;
}