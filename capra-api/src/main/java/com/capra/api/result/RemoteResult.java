package com.capra.api.result;

import com.capra.api.constant.ResultConstant;
import lombok.Data;
import org.apache.logging.log4j.util.Strings;

import java.io.Serializable;

/**
 * 远程调用返回的结果
 *
 * @author lql
 * @date 2023/10/27
 */
@Data
public class RemoteResult<T> implements Serializable {
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

    private RemoteResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> RemoteResult<T> success(){
        return new RemoteResult<>(ResultConstant.SUCCESS, Strings.EMPTY,null);
    }

    public static <T> RemoteResult<T> successWithMeg(String message){
        return new RemoteResult<>(ResultConstant.SUCCESS,message,null);
    }

    public static <T> RemoteResult<T> successWithData(T data){
        return new RemoteResult<>(ResultConstant.SUCCESS,Strings.EMPTY,data);
    }

    public static <T> RemoteResult<T> successWithDetail(String message,T data){
        return new RemoteResult<>(ResultConstant.SUCCESS,message,data);
    }

    public static <T> RemoteResult<T> fail(){
        return new RemoteResult<>(ResultConstant.FAILED,Strings.EMPTY,null);
    }

    public static <T> RemoteResult<T> failWithMsg(String message){
        return new RemoteResult<>(ResultConstant.FAILED,message,null);
    }

    public static <T> RemoteResult<T> failWithData(T data){
        return new RemoteResult<>(ResultConstant.FAILED,Strings.EMPTY,data);
    }

    public static <T> RemoteResult<T> failWithDetail(String message,T data){
        return new RemoteResult<>(ResultConstant.FAILED,message,data);
    }
}
