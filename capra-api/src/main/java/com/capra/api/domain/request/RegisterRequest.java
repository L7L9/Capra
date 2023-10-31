package com.capra.api.domain.request;

import lombok.Data;

/**
 * 注册请求
 *
 * @author lql
 * @date 2023/10/31
 */
@Data
public class RegisterRequest {
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
