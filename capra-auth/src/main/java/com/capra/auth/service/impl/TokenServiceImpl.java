package com.capra.auth.service.impl;

import com.capra.api.client.UserClient;
import com.capra.api.domain.request.RegisterRequest;
import com.capra.api.domain.response.LoginResponse;
import com.capra.api.result.RemoteResult;
import com.capra.auth.constant.AccountConstant;
import com.capra.auth.domain.bo.LoginBO;
import com.capra.auth.domain.bo.RegisterBO;
import com.capra.auth.service.TokenService;
import com.capra.auth.utils.PasswordUtils;
import com.capra.core.domain.CommonClaims;
import com.capra.core.exception.ServiceException;
import com.capra.core.utils.JwtUtils;
import com.capra.core.utils.StringUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 令牌服务实现类
 * @author lql
 * @date 2023/10/24
 */
@Service
public class TokenServiceImpl implements TokenService {
    @Resource
    private UserClient userClient;

    @Override
    public String login(LoginBO loginBO) {
        // 判断是否为空
        if(StringUtils.isEmpty(loginBO.getUsername()) || StringUtils.isEmpty(loginBO.getPassword())){
            throw new ServiceException("用户名或密码为空,请重新输入");
        }

        // 获取用户信息
        RemoteResult<LoginResponse> authUserMessage = userClient.getAuthUserMessage(loginBO.getUsername());
        // 判断用户是否存在
        if(Objects.isNull(authUserMessage.getData())){
            throw new ServiceException(authUserMessage.getMessage());
        }

        LoginResponse loginResponse = authUserMessage.getData();
        // 判断信息有无错误
        if(!loginBO.getUsername().equals(loginResponse.getUsername()) || !PasswordUtils.compare(loginBO.getPassword(), loginResponse.getPassword())){
            throw new ServiceException("用户名错误或密码错误,请检查用户名或密码");
        }

        // 判断账户状态
        if(loginResponse.getStatus() == AccountConstant.LOG_OFF){
            throw new ServiceException("该账户已注销");
        } else if (loginResponse.getStatus() == AccountConstant.FREEZE) {
            throw new ServiceException("该账户已被冻结");
        } else if (loginResponse.getStatus() == AccountConstant.OTHER){
            throw new ServiceException("该账户由于其他原因,在黑名单中,请联系管理员检验");
        }

        // 生成token
        CommonClaims claims = new CommonClaims();
        claims.setUserId(loginResponse.getId());
        claims.setUsername(loginResponse.getUsername());
        return JwtUtils.createToken(claims);
    }

    @Override
    public Boolean register(RegisterBO registerBO) {
        // 判断是否为空
        if(StringUtils.isEmpty(registerBO.getUsername()) || StringUtils.isEmpty(registerBO.getPassword())){
            throw new ServiceException("用户名或密码不能为空,请重新输入");
        }

        // 判断长度
        if (!StringUtils.lenBetween(registerBO.getUsername(), 2,20) || !StringUtils.lenBetween(registerBO.getPassword(), 8,20)){
            throw new ServiceException("用户名或密码的长度错误,请重新输入");
        }

        // 调用服务
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername(registerBO.getUsername());
        registerRequest.setPassword(PasswordUtils.encrypt(registerBO.getPassword()));
        RemoteResult<Boolean> result = userClient.register(registerRequest);

        return result.getData();
    }
}
