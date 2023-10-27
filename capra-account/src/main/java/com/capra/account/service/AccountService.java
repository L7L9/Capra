package com.capra.account.service;

import com.capra.account.entity.dto.LoginDTO;
import com.capra.account.entity.dto.RegisterDTO;
import com.capra.account.result.response.LoginResponse;

/**
 * 账号服务接口
 * @author lql
 * @date 2023/10/24
 */
public interface AccountService {
    /**
     * 注册
     *
     * @param registerDTO 注册dto
     * @return boolean 注册成功返回true
     */
    boolean register(RegisterDTO registerDTO);

    /**
     * 登录
     *
     * @param loginDTO 登录dto
     * @return LoginResponse 登录返回消息
     */
    LoginResponse login(LoginDTO loginDTO);
}
