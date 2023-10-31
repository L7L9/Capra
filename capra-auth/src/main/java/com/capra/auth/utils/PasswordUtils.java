package com.capra.auth.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

/**
 * 针对密码的工具类，统一规范
 *
 * @author lql
 * @date 2023/10/31
 */
public class PasswordUtils {
    /**
     * 盐
     */
    private static final String SALT = "cross the bridge when you come to it";

    /**
     * 加密工具（sha256算法）
     */
    private static final Digester DIGESTER = new Digester(DigestAlgorithm.SHA256);

    private static final int MIDDLE = 6;

    /**
     * 加密
     *
     * @param password 密码
     * @return 返回加密后的密码
     */
    public static String encrypt(String password){
        return DIGESTER.digestHex(password.substring(0,MIDDLE) + SALT + password.substring(MIDDLE));
    }

    /**
     * 比较密码
     *
     * @param password 密码
     * @param source 源密码
     * @return boolean 相同返回true;否则返回false
     */
    public static boolean compare(String password,String source){
        return encrypt(password).equals(source);
    }
}
