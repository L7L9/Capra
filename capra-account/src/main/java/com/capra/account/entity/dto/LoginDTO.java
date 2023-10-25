package com.capra.account.entity.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 登录dto
 * @author lql
 * @date 2023/10/25
 */
@Data
public class LoginDTO{
    /**
     * 用户名
     */
    @NotBlank
    private String username;

    /**
     * 密码
     */
    @NotBlank
    private String password;
}
