package com.capra.account.entity.dto;

import lombok.Data;

/**
 * 注册dto
 *
 * @author lql
 * @date 2023/10/24
 */
@Data
public class RegisterDTO {
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
