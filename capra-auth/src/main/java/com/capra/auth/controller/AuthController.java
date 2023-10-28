package com.capra.auth.controller;

import com.capra.api.domain.SysUser;
import com.capra.api.result.RemoteResult;
import com.capra.auth.service.TokenService;
import com.capra.security.domain.CommonClaims;
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
    public RemoteResult<String> getToken(SysUser sysUser){
        CommonClaims claims = new CommonClaims();
        claims.setUserId(sysUser.getId());
        claims.setUsername(sysUser.getUsername());
        return RemoteResult.successWithDetail("获取token成功",tokenService.getToken(claims));
    }
}
