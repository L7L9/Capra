package com.capra.account.service.impl;

import com.capra.account.domain.po.User;
import com.capra.account.mapper.UserMapper;
import com.capra.account.service.AccountService;
import com.capra.api.domain.request.RegisterRequest;
import com.capra.core.exception.DaoException;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author lql
 * @date 2023/10/31
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User getUserInfo(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public boolean register(RegisterRequest registerRequest) {
        if(!Objects.isNull(userMapper.selectByUsername(registerRequest.getUsername()))){
            return false;
        }

        User user = new User()
                .setUsername(registerRequest.getUsername())
                .setNickname(registerRequest.getUsername())
                .setPassword(registerRequest.getPassword());
        if(userMapper.insert(user) != 1){
            throw new DaoException("数据库插入失败");
        }

        return true;
    }
}
