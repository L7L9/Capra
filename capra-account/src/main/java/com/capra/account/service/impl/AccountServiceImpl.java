package com.capra.account.service.impl;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import com.capra.account.entity.dto.LoginDTO;
import com.capra.account.entity.dto.RegisterDTO;
import com.capra.account.entity.po.User;
import com.capra.account.mapper.UserMapper;
import com.capra.account.service.AccountService;
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
    public boolean login(LoginDTO loginDTO) {


        return true;
    }
}
