package com.miracle.qmt.network;

/**
 * Created by Administrator on 2016/2/24.
 */
public class MyResult<T> {
    String msg;

    String state;
    T data;

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
