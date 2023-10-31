package com.capra.account.controller;

import com.capra.account.domain.po.User;
import com.capra.account.service.AccountService;
import com.capra.api.annotation.InnerCall;
import com.capra.api.domain.request.RegisterRequest;
import com.capra.api.domain.response.LoginResponse;
import com.capra.api.result.RemoteResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * 账户controller
 *
 * @author lql
 * @date 2023/10/24
 */
@RestController
@RequestMapping("account")
public class AccountController {
    @Resource
    private AccountService accountService;

    /**
     * 获取需要认证的用户信息
     *
     * @param username 用户名
     * @return 返回需要认证的用户信息
     */
    @InnerCall
    @PostMapping("/info/{username}")
    public RemoteResult<LoginResponse> getAuthUserMessage(@PathVariable String username){
        User userInfo = accountService.getUserInfo(username);
        if(Objects.isNull(userInfo)){
            return RemoteResult.successWithMeg("用户不存在");
        }
        return RemoteResult.successWithData(new LoginResponse()
                .setId(userInfo.getId())
                .setUsername(userInfo.getUsername())
                .setPassword(userInfo.getPassword())
                .setStatus(userInfo.getStatus()));
    }

    @InnerCall
    @PostMapping("/register")
    public RemoteResult<Boolean> register(@RequestBody RegisterRequest registerRequest){
        return RemoteResult.successWithData(accountService.register(registerRequest));
    }
}
