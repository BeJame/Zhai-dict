package com.book.base;

import com.book.constant.ResultCode;

import java.io.Serializable;

public class BaseResponse<T> implements Serializable {

    private int code;

    private String msg;

    private T data;

    public BaseResponse() {
    }

    public BaseResponse(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> BaseResponse<T> init(int code, String msg, T data){
        return new BaseResponse<>(code, msg, data);
    }

    public static <T> BaseResponse<T> init(ResultCode resultCode, T data){
        return init(resultCode.getCode(), resultCode.getMsg(), data);
    }

    public static <T> BaseResponse<T> success(T data){
        return BaseResponse.init(ResultCode.SUCCESS, data);
    }

    public static BaseResponse success(){
        return BaseResponse.init(ResultCode.SUCCESS, null);
    }

    public static BaseResponse failed(){
        return BaseResponse.init(ResultCode.FAILED, null);
    }

    public static BaseResponse failed(String msg){
        return BaseResponse.init(ResultCode.FAILED.getCode(), msg, null);
    }
}
