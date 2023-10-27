package com.capra.security.utils;

import cn.hutool.jwt.JWT;
import com.capra.security.constant.JwtConstant;
import com.capra.security.domain.MyClaims;

import java.util.Calendar;
import java.util.Date;

/**
 * jwt工具类
 *
 * @author lql
 * @date 2023/10/27
 */
public class JwtUtils {
    private final byte[] key = JwtConstant.KEY.getBytes();

    /**
     * 生成token
     *
     * @return token
     */
    public String createToken(MyClaims claims){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        JWT jwt = new JWT();
        jwt.setPayload(JwtConstant.CLAIM_ID,claims.getUserId())
                .setPayload(JwtConstant.CLAIM_NAME,claims.getUsername())
                .setIssuer(JwtConstant.ISSUER)
                .setKey(key)
                .setIssuedAt(calendar.getTime())
                .sign();

        return "";
    }
}
