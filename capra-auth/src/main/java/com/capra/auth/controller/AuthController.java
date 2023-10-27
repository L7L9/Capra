package com.capra.auth.controller;

import com.capra.api.domain.SysUser;
import com.capra.auth.service.TokenService;
import com.capra.core.result.CommonResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/getToken")
    public CommonResult<String> getToken(SysUser sysUser){
        return CommonResult.successWithDetail("获取token成功",tokenService.getToken(sysUser));
    }
}
