package com.capra.auth.service.impl;

import com.capra.auth.service.TokenService;
import com.capra.security.domain.CommonClaims;
import com.capra.security.utils.JwtUtils;
import org.springframework.stereotype.Service;

/**
 * 令牌服务实现类
 * @author lql
 * @date 2023/10/24
 */
@Service
public class TokenServiceImpl implements TokenService {
    @Override
    public String getToken(CommonClaims claims) {
        JwtUtils jwtUtils = new JwtUtils();
        return jwtUtils.createToken(claims);
    }
}
