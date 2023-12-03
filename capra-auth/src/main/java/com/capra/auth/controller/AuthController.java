package com.capra.auth.controller;

import com.capra.auth.domain.bo.LoginBO;
import com.capra.auth.domain.bo.RegisterBO;
import com.capra.auth.service.AuthService;
import com.capra.core.result.CommonResult;
import com.capra.security.service.TokenService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * 账号api
 *
 * @author lql
 * @date 2023/10/23
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Resource
    private AuthService authService;

    @Resource
    private TokenService tokenService;

    @PostMapping("/login")
    public CommonResult<String> login(@RequestBody LoginBO loginBO){
        return CommonResult.successWithDetail("登录成功", authService.login(loginBO));
    }

    @PostMapping("/register")
    public CommonResult<Void> register(@RequestBody RegisterBO registerBO){
        if(authService.register(registerBO)){
            return CommonResult.successWithMeg("注册成功");
        }
        return CommonResult.failWithMsg("网络繁忙,请重试");
    }
}