package com.capra.core.aspect;

import com.capra.core.exception.DaoException;
import com.capra.core.exception.ServiceException;
import com.capra.core.result.CommonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 *
 * @author lql
 * @date 2023/12/05
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public CommonResult<Void> exceptionHandler(Exception e){
        return CommonResult.failWithMsg(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(ServiceException.class)
    public CommonResult<Void> exceptionHandler(ServiceException e){
        return CommonResult.failWithMsg(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(DaoException.class)
    public CommonResult<Void> exceptionHandler(DaoException e){
        return CommonResult.failWithMsg(e.getMessage());
    }
}
