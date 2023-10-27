package com.capra.account.service.impl;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import com.capra.account.entity.dto.LoginDTO;
import com.capra.account.entity.dto.RegisterDTO;
import com.capra.account.entity.po.User;
import com.capra.account.mapper.UserMapper;
import com.capra.account.result.response.LoginResponse;
import com.capra.account.service.AccountService;
import com.capra.api.client.AuthClient;
import com.capra.api.domain.SysUser;
import com.capra.api.result.RemoteResult;
import com.capra.core.exception.DaoException;
import com.capra.core.exception.ServiceException;
import com.capra.core.utils.StringUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

/**
 * 账号服务实现类
 *
 * @author lql
 * @date 2023/10/24
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Resource
    private AuthClient authClient;

    @Resource
    private UserMapper userMapper;

    @Override
    public boolean register(RegisterDTO registerDTO) {
        if(StringUtils.isEmpty(registerDTO.getUsername()) || StringUtils.isEmpty(registerDTO.getPassword())){
            throw new ServiceException("请填写用户名/密码");
        }

        // 加密
        Digester digester = new Digester(DigestAlgorithm.SHA256);
        String encodePassword = digester.digestHex(registerDTO.getPassword());

        User user = new User();
        user.setUsername(registerDTO.getUsername()).setNickname(registerDTO.getUsername()).setPassword(encodePassword);

        if(Objects.nonNull(userMapper.selectByUsername(user.getUsername()))){
            throw new ServiceException("用户名重复，请重新输入");
        }

        if(userMapper.insert(user) != 1){
            throw new DaoException("数据库插入失败,请检查数据");
        }
        return true;
    }

    @Override
    public LoginResponse login(LoginDTO loginDTO) {
        User user = userMapper.selectByUsername(loginDTO.getUsername());

        if(Objects.isNull(user)){
            throw new ServiceException("该用户不存在");
        }
        // 判断密码
        Digester digester = new Digester(DigestAlgorithm.SHA256);
        String encodePassword = digester.digestHex(loginDTO.getPassword());
        if(!encodePassword.equals(user.getPassword())){
            throw new ServiceException("用户密码错误,请检查账号或者密码");
        }
        // 生成jwt -> 调用
        SysUser sysUser = new SysUser();
        sysUser.setId(user.getId());
        sysUser.setUsername(user.getUsername());
        RemoteResult<String> result = authClient.getToken(sysUser);

        LoginResponse response = new LoginResponse();
        response.setUser(user);
        response.setToken(result.getData());
        // 设置返回响应
        return response;
    }
}
