package com.capra.core.utils;

import cn.hutool.core.convert.Convert;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTException;
import com.capra.core.constant.JwtConstant;

/**
 * jwt工具类
 *
 * @author lql
 * @date 2023/10/27
 */
public class JwtUtils {
    /**
     * 用于外部检查token有效性
     *
     * @param token 用户token
     * @return boolean 有效返回true,否则false
     */
    public static boolean checkValid(String token){
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
     * 获取uuid
     *
     * @param token 用户token
     * @return 返回uuid
     */
    public static String getUuid(String token){
        JWT jwt = JWT.of(token).setKey(JwtConstant.KEY);
        return Convert.convert(String.class,jwt.getPayload(JwtConstant.CLAIM_UUID));
    }
}