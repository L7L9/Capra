package com.capra.core.utils;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTException;
import com.capra.core.constant.JwtConstant;
import com.capra.core.domain.CommonClaims;

/**
 * jwt工具类
 *
 * @author lql
 * @date 2023/10/27
 */
public class JwtUtils {
    private final static byte[] KEY = JwtConstant.KEY.getBytes();

    /**
     * 生成token
     *
     * @return token
     */
    public String createToken(CommonClaims claims){
        DateTime date = DateUtil.date();

        JWT jwt = new JWT();
        return jwt
                .setPayload(JwtConstant.CLAIM_ID,claims.getUserId())
                .setPayload(JwtConstant.CLAIM_NAME,claims.getUsername())
                .setIssuer(JwtConstant.ISSUER)
                .setKey(KEY)
                .setIssuedAt(date)
                .setExpiresAt(DateUtil.offset(date, DateField.SECOND, JwtConstant.DURATION))
                .sign();
    }

    /**
     * 获取用户id
     *
     * @param token 用户token
     * @return 用户id
     */
    public String getUserId(String token){
        JWT jwt = JWT.of(token).setKey(KEY);
        if(!jwt.validate(JwtConstant.DURATION)){
            throw new JWTException("token无效或者过期");
        }
        return (String) jwt.getPayload(JwtConstant.CLAIM_ID);
    }

    /**
     * 获取用户名
     *
     * @param token 用户token
     * @return 用户id
     */
    public String getUsername(String token){
        JWT jwt = JWT.of(token).setKey(KEY);
        if(!jwt.validate(JwtConstant.DURATION)){
            throw new JWTException("token无效或者过期");
        }
        return (String) jwt.getPayload(JwtConstant.CLAIM_NAME);
    }
}