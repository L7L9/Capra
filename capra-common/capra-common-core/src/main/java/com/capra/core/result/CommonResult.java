package com.capra.core.result;

import com.capra.core.constant.ResultConstant;
import lombok.Data;
import org.apache.logging.log4j.util.Strings;

import java.io.Serializable;

/**
 * @author lql
 * @date 2023/10/23
 */
@Data
public class CommonResult<T> implements Serializable {
    /**
     * 状态码
     */
    private int code;

    /**
     * 状态说明
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    private CommonResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> CommonResult<T> success(){
        return new CommonResult<>(ResultConstant.SUCCESS_CODE, Strings.EMPTY,null);
    }

    public static <T> CommonResult<T> successWithMeg(String message){
        return new CommonResult<>(ResultConstant.SUCCESS_CODE,message,null);
    }

    public static <T> CommonResult<T> successWithData(T data){
        return new CommonResult<>(ResultConstant.SUCCESS_CODE,Strings.EMPTY,data);
    }

    public static <T> CommonResult<T> successWithDetail(String message,T data){
        return new CommonResult<>(ResultConstant.SUCCESS_CODE,message,data);
    }

    public static <T> CommonResult<T> fail(){
        return new CommonResult<>(ResultConstant.FAILED_CODE,Strings.EMPTY,null);
    }

    public static <T> CommonResult<T> failWithMsg(String message){
        return new CommonResult<>(ResultConstant.FAILED_CODE,message,null);
    }

    public static <T> CommonResult<T> failWithData(T data){
        return new CommonResult<>(ResultConstant.FAILED_CODE,Strings.EMPTY,data);
    }

    public static <T> CommonResult<T> failWithDetail(String message,T data){
        return new CommonResult<>(ResultConstant.FAILED_CODE,message,data);
    }

}
