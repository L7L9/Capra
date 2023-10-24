package com.capra.core.exception;

/**
 * 自定义业务层面的异常
 *
 * @author lql
 * @date 2023/10/24
 */
public class ServiceException extends RuntimeException{
    public ServiceException(String message) {
        super(message);
    }
}
