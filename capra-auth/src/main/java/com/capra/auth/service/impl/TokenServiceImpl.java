package com.capra.auth.service.impl;

import cn.hutool.core.util.IdUtil;
import com.capra.auth.constant.TokenConstant;
import com.capra.auth.service.TokenService;
import com.capra.core.domain.CommonClaims;
import com.capra.core.utils.JwtUtils;
import com.capra.redis.service.RedisService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * token服务,创建token以及刷新等等
 *
 * @author lql
 * @date 2023/11/02
 */
@Service
public class TokenServiceImpl implements TokenService {
    @Resource
    private RedisService redisService;

    /**
     * 持续时间
     */
    private final static long DURATION = 1000 * 60 * 60  * 2L;

    @Override
    public String createToken(CommonClaims claims) {
        if(Objects.isNull(claims.getUuid())){
            claims.setUuid(IdUtil.fastUUID());
        }
        return JwtUtils.createToken(claims);
    }

    @Override
    public void refreshToken(String token) {
        if(!Objects.isNull(token)){
            // 获取redis中的key
            String userKey = JwtUtils.getUuid(token);
            redisService.set(userKey,token, TokenConstant.TTL);
        }
    }

    @Override
    public Boolean deleteToken(String token) {
        return redisService.delete(JwtUtils.getUuid(token));
    }

    @Override
    public void verifyToken(String token) {
        if(!Objects.isNull(token)){
            long currentTime = System.currentTimeMillis();
            long expireTime = redisService.getExpire(JwtUtils.getUuid(token));
            if(expireTime - currentTime <= DURATION){
                refreshToken(token);
            }
        }
    }
}
