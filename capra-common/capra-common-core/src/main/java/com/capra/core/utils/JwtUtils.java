package com.capra.core.utils;

import cn.hutool.core.convert.Convert;
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
    private final static DateTime NOT_BEFORE_TIME = DateUtil.date();

    /**
     * 生成token
     *
     * @return token
     */
    public static String createToken(CommonClaims claims){
        DateTime date = DateUtil.date();

        JWT jwt = new JWT();
        return jwt
                .setPayload(JwtConstant.CLAIM_ID,claims.getUserId())
                .setPayload(JwtConstant.CLAIM_NAME,claims.getUsername())
                .setIssuer(JwtConstant.ISSUER)
                .setKey(JwtConstant.KEY)
                .setIssuedAt(date)
                .setNotBefore(NOT_BEFORE_TIME)
                .setExpiresAt(DateUtil.offset(date, DateField.SECOND, JwtConstant.DURATION))
                .sign();
    }

    /**
     * 用于外部检查token有效性
     *
     * @param token 用户token
     * @return boolean 有效返回true,否则false
     */
    public static boolean checkToken(String token){
        JWT jwt = JWT.of(token).setKey(JwtConstant.KEY);
        return jwt.validate(JwtConstant.DURATION);
    }

    /**
     * 获取用户id
     *
     * @param token 用户token
     * @return 用户id
     */
    public static Long getUserId(String token){
        JWT jwt = JWT.of(token).setKey(JwtConstant.KEY);
        if(!jwt.validate(JwtConstant.DURATION)){
            throw new JWTException("token无效或者过期");
        }
        return Convert.convert(Long.class,jwt.getPayload(JwtConstant.CLAIM_ID));
    }

    /**
     * 获取用户名
     *
     * @param token 用户token
     * @return 用户id
     */
    public static String getUsername(String token){
        JWT jwt = JWT.of(token).setKey(JwtConstant.KEY);
        if(!jwt.validate(JwtConstant.DURATION)){
            throw new JWTException("token无效或者过期");
        }
        return Convert.convert(String.class,jwt.getPayload(JwtConstant.CLAIM_NAME));
    }
}