package com.miracle.qmt.ui.model;

/**
 * Created by ZhouChao on 2017/9/6.
 */

public class LoginBean {
    /**
     * result : 0
     * data : {"user_id":2,"phone":"18240408225","password":"def26451f27ca3fabae968f247780dae","salt":8009,"nick_name":"","sex":1,"address":"","Management":"你好","buy":null,"company_name":"","name":"汪","head_pic":"","pic":null,"add_time":1504499497}
     */

    private int result;
    private UserBean data;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public UserBean getData() {
        return data;
    }

    public void setData(UserBean data) {
        this.data = data;
    }


}
