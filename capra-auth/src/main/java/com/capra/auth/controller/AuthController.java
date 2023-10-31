package com.capra.auth.controller;

import com.capra.auth.domain.bo.LoginBO;
import com.capra.auth.domain.bo.RegisterBO;
import com.capra.auth.service.TokenService;
import com.capra.core.result.CommonResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 账号api
 *
 * @author lql
 * @date 2023/10/23
 */
@RestController
@RequestMapping("auth")
public class AuthController {
    @Resource
    private TokenService tokenService;

    @PostMapping("/login")
    public CommonResult<String> login(@RequestBody LoginBO loginBO){
        return CommonResult.successWithDetail("登录成功",tokenService.login(loginBO));
    }

    @PostMapping("/register")
    public CommonResult<Void> register(@RequestBody RegisterBO registerBO){
        if(tokenService.register(registerBO)){
            return CommonResult.successWithMeg("注册成功");
        }
        return CommonResult.failWithMsg("网络繁忙,请重试");
    }
}
