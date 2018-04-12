package com.miracle.qmt.ui.model;

import java.util.List;

/**
 * Created by Administrator on 2017/10/10.
 */

public class CollectionBean {
    /**
     * state : 1
     * msg : 成功
     * data : []
     */

    private String state;
    private String msg;
    private List<?> data;

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

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
