package com.capra.security.service;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.jwt.JWT;
import com.capra.core.constant.JwtConstant;
import com.capra.core.domain.CommonClaims;
import com.capra.core.utils.JwtUtils;
import com.capra.redis.service.RedisService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * token验证工具类
 *
 * @author lql
 * @date 2023/12/03
 */
@Component
public class TokenService {
    @Resource
    private RedisService redisService;

    /**
     * 项目启动时间
     */
    private final static DateTime NOT_BEFORE_TIME = DateUtil.date();

    /**
     * ttl => 1天
     */
    public static final long TTL = 60 * 60;

    /**
     * 持续时间 (2h)
     */
    private final static long DURATION = 1000 * 60 * 60  * 2L;

    /**
     * 创建令牌
     * @param claims payload存储的用户信息
     * @return token
     */
    public String createToken(CommonClaims claims) {
        DateTime date = DateUtil.date();

        JWT jwt = new JWT();
        return jwt
                .setPayload(JwtConstant.CLAIM_USERNAME,claims.getUsername())
                .setPayload(JwtConstant.CLAIM_ID,claims.getUserId())
                .setPayload(JwtConstant.CLAIM_UUID, IdUtil.fastUUID())
                .setIssuer(JwtConstant.ISSUER)
                .setKey(JwtConstant.KEY)
                .setIssuedAt(date)
                .setNotBefore(NOT_BEFORE_TIME)
                .setExpiresAt(DateUtil.offset(date, DateField.MINUTE, JwtConstant.DURATION))
                .sign();
    }

    /**
     * 刷新token
     * @param token 令牌
     */
    public void refreshToken(String token){
        if(!Objects.isNull(token)){
            // 获取redis中的key
            String userKey = JwtUtils.getUuid(token);
            redisService.set(userKey,token, TTL);
        }
    }

    /**
     * 验证令牌有效性
     * @param token 令牌
     * @return 有效返回ture
     */
    public Boolean verifyToken(String token) {
        if(!Objects.isNull(token)){
            // 判断token是否还有效
            if(!JwtUtils.checkValid(token)){
                return false;
            }else {
                long expireTime = redisService.getExpire(JwtUtils.getUuid(token));
                // 判断剩余时间是否小于持续时间
                if(expireTime <= DURATION){
                    refreshToken(token);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 删除token
     * @param token 令牌
     * @return 成功返回true
     */
    public Boolean deleteToken(String token) {
        return redisService.delete(JwtUtils.getUuid(token));
    }
}
