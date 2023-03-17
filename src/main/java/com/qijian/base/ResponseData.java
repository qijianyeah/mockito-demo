package com.qijian.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author mahuahong
 * @Date 2023/3/16 18:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData<T> {

    private int code;

    private String message;

    private T content;

    ResponseData(int code , String message){
        this.code = code;
        this.message =message;
    }

    public static <T> ResponseData<T> errorWithMeg(String message){
        return new ResponseData<>(500,message);
    }

    public static <T> ResponseData<T> okWithMeg(String message){
        return new ResponseData<>(200,message);
    }

    public static <T> ResponseData<T> ok(String message,T content){
        return new ResponseData<>(200,message,content);
    }
}
