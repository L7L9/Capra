package com.capra.auth.service;

import com.capra.security.domain.MyClaims;

/**
 * token服务
 *
 * @author lql
 * @date 2023/10/23
 */
public interface TokenService {
    /**
     * 获取token
     *
     * @param claims 存于preload中的用户信息
     * @return token jwt
     */
    String getToken(MyClaims claims);
}
