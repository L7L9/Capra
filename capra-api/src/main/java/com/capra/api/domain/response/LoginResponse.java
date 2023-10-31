package com.capra.api.domain.response;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户信息的dto
 *
 * @author lql
 * @date 2023/10/31
 */
@Data
@Accessors(chain = true)
public class LoginResponse {
    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 账号状态
     */
    private Integer status;
}
