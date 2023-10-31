package com.capra.account.service;

import com.capra.account.domain.po.User;
import com.capra.api.domain.request.RegisterRequest;

/**
 *
 * @author lql
 * @date 2023/10/31
 */
public interface AccountService {
    /**
     * 获取用户信息
     * @param username 用户名
     * @return 用户po
     */
    User getUserInfo(String username);

    /**
     * 注册用户信息到数据库
     *
     * @param registerRequest 注册请求
     * @return 成功返回true
     */
    boolean register(RegisterRequest registerRequest);
}
