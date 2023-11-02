package com.capra.auth.service;


import com.capra.auth.domain.bo.LoginBO;
import com.capra.auth.domain.bo.RegisterBO;

/**
 * 鉴权服务接口
 *
 * @author lql
 * @date 2023/10/23
 */
public interface AuthService {
    /**
     * 登录并且获取token
     *
     * @param loginBO 登录bo类
     * @return token
     */
    String login(LoginBO loginBO);

    /**
     * 注册用户
     *
     * @param registerBO 注册bo类
     * @return 成功返回true
     */
    Boolean register(RegisterBO registerBO);
}
