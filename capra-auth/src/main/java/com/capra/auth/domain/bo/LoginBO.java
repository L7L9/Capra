package com.capra.auth.domain.bo;

import lombok.Data;

/**
 * 登录的bo类
 *
 * @author lql
 * @date 2023/10/30
 */
@Data
public class LoginBO {
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
