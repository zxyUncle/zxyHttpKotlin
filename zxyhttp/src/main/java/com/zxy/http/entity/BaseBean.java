package com.zxy.http.entity;

/**
 * Created by zxy on 2021/2/27 0027 18:41
 * ******************************************
 * * 所有基类的父类
 * ******************************************
 */
public class BaseBean<T> {
    private int errorCode;
    private String errorMsg;
    private T data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
