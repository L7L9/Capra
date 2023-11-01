package com.capra.core.utils;

import java.util.Objects;

/**
 * 字符串工具类
 *
 * @author lql
 * @date 2023/10/24
 */

public class StringUtils {
    /**
     * 空字符串
     */
    public final static String EMPTY = "";

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

    /**
     * 判断字符串长度是否符合: min <= s.len <= max
     * @param s 字符串
     * @param min 最小值
     * @param max 最大值
     * @return boolean 符合返回true,否则返回false
     */
    public static boolean lenBetween(String s,int min,int max){
        if(min < 0 || max < 0 || min > max){
            throw new RuntimeException("参数越界");
        }
        int length = s.length();
        return length >= min && length <= max;
    }
}
