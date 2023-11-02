package com.capra.auth.service;

import com.capra.core.domain.CommonClaims;

/**
 * token服务
 *
 * @author lql
 * @date 2023/11/02
 */
public interface TokenService {
    /**
     * 创建token
     *
     * @param claims 存于token中的信息
     * @return token
     */
    String createToken(CommonClaims claims);

    /**
     * 刷新令牌缓存时间
     *
     * @param token 令牌
     */
    void refreshToken(String token);

    /**
     * 删除token
     * @param token token
     * @return 成功返回true
     */
    Boolean deleteToken(String token);

    /**
     * 检测token的过期时间
     *
     * @param token 令牌
     */
    void verifyToken(String token);
}
