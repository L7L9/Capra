package com.capra.core.exception;

/**
 * 数据库层面的异常
 *
 * @author lql
 * @date 2023/10/25
 */
public class DaoException extends RuntimeException{
    public DaoException(String message) {
        super(message);
    }
}
