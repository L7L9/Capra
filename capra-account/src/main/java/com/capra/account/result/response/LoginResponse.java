package com.capra.account.result.response;

import com.capra.account.entity.po.User;
import lombok.Data;

import java.io.Serializable;

/**
 * 登录接口的响应类
 *
 * @author lql
 * @date 2023/10/27
 */

@Data
public class LoginResponse implements Serializable {
    /**
     * 用户信息
     */
    private User user;

    /**
     * 用户登录token
     */
    private String token;
}
