package com.capra.core.utils;

import java.util.Objects;

/**
 * 字符串工具类
 *
 * @author lql
 * @date 2023/10/24
 */

public class StringUtils {
    private final static String EMPTY = "";

    /**
     * 判断是否为空字符串
     */
    public static boolean isEmpty(String s){
        return Objects.isNull(s) || s.equals(EMPTY);
    }

    /**
     * 判断是否为非空字符串
     */
    public static boolean notEmpty(String s){
        return !isEmpty(s);
    }
}
