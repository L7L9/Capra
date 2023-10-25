package com.capra.account.controller;

import com.capra.account.entity.dto.RegisterDTO;
import com.capra.account.service.AccountService;
import com.capra.core.result.CommonResult;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 账户controller
 *
 * @author lql
 * @date 2023/10/24
 */
@RestController
public class AccountController {
    @Resource
    private AccountService accountService;

    @PostMapping("register")
    public CommonResult<?> register(@Validated @RequestBody RegisterDTO registerDTO){
        if(accountService.register(registerDTO)){
            return CommonResult.successWithMeg("注册成功");
        }
        return CommonResult.fail();
    }
}
