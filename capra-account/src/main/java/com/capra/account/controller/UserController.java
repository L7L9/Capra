package com.capra.account.controller;

import com.capra.account.domain.po.User;
import com.capra.account.domain.vo.UserMessageVO;
import com.capra.account.service.UserService;
import com.capra.api.annotation.InnerCall;
import com.capra.api.domain.request.RegisterRequest;
import com.capra.api.domain.response.LoginResponse;
import com.capra.api.result.RemoteResult;
import com.capra.core.constant.HeaderConstant;
import com.capra.core.result.CommonResult;
import com.capra.core.utils.JwtUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * 账户controller
 *
 * @author lql
 * @date 2023/10/24
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Resource
    private UserService accountService;

    @Resource
    private HttpServletRequest request;

    /**
     * 获取用户信息
     * @param id 用户id
     * @return 用户信息
     */
    @GetMapping("/{id}")
    public CommonResult<UserMessageVO> getUserMessage(@PathVariable Long id){
        return CommonResult.successWithData(accountService.getUserMessage(id));
    }

    /**
     * 获取登录用户的全部信息
     * @return 返回登录用户全部信息
     */
    @GetMapping("/center")
    public CommonResult<User> getUserMessage(){
        Long userId = JwtUtils.getUserId(request.getHeader(HeaderConstant.TOKEN_HEADER));
        return CommonResult.successWithData(accountService.getUserInfo(userId));
    }

    /**
     * 获取需要认证的用户信息
     * @param username 用户名
     * @return 返回需要认证的用户信息
     */
    @InnerCall
    @GetMapping("/info/{username}")
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

    /**
     * 注册用户信息到数据库
     * @param registerRequest 注册请求
     * @return 成功返回true
     */
    @InnerCall
    @PostMapping("/register")
    public RemoteResult<Boolean> register(@RequestBody RegisterRequest registerRequest){
        return RemoteResult.successWithData(accountService.register(registerRequest));
    }
}
