package com.capra.core.exception;

/**
 * 系统级别的错误异常
 *
 * @author lql
 * @date 2023/10/31
 */
public class SystemException extends RuntimeException{
    public SystemException(String message) {
        super(message);
    }
}
