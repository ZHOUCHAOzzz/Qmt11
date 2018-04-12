package com.miracle.qmt.ui.model;

/**
 * Created by Administrator on 2017/9/10.
 */

public class UpUserBean {
    /**
     * state : 0
     * msg : 失败
     * data : {"user_id":3,"phone":"15803063024","password":"3723f55ca6b18b0662a4e1bbbf20f9c5","salt":5082,"nick_name":"hhh","sex":1,"address":"沈阳","Management":"ppp","buy":"555","company_name":"111","name":"323","head_pic":"88","pic":"323","add_time":1500450550}
     */

    private String state;
    private String msg;
    private UserBean data;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public UserBean getData() {
        return data;
    }

    public void setData(UserBean data) {
        this.data = data;
    }


}
