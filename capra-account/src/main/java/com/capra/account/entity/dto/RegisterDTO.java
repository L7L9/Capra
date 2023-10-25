package com.capra.account.entity.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

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
    @NotBlank
    @Length(min = 2,max = 20)
    private String username;

    /**
     * 密码
     */
    @NotBlank
    @Length(min = 6,max = 20)
    private String password;
}
