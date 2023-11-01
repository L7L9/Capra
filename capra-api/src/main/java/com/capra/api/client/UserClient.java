package com.capra.api.client;

import com.capra.api.domain.request.RegisterRequest;
import com.capra.api.domain.response.LoginResponse;
import com.capra.api.result.RemoteResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 账户模块远程接口的定义类
 *
 * @author lql
 * @date 2023/10/30
 */
@FeignClient("capra-account")
public interface UserClient {
    /**
     * 通过账户名获取用户需要认证的信息
     *
     * @param username 用户名
     * @return 返回用户信息
     */
    @GetMapping("/user/info/{username}")
    RemoteResult<LoginResponse> getAuthUserMessage(@PathVariable String username);

    /**
     * 将用户信息注册到数据库中
     *
     * @param registerRequest 注册请求
     * @return 成功返回true
     */
    @PostMapping("/user/register")
    RemoteResult<Boolean> register(@RequestBody RegisterRequest registerRequest);
}
