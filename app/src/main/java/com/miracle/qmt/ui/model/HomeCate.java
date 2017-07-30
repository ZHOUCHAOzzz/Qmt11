package com.miracle.qmt.ui.model;

/**
 * Created by Administrator on 2017/7/29.
 */

public class HomeCate {

    /**
     * information_id : 1
     * type : 1
     * status : 1
     * information_name : 资讯
     * information_pic : http://47.94.172.244/uploads/12/1.png
     */

    private int information_id;
    private int type;
    private int status;
    private String information_name;
    private String information_pic;

    public int getInformation_id() {
        return information_id;
    }

    public void setInformation_id(int information_id) {
        this.information_id = information_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInformation_name() {
        return information_name;
    }

    public void setInformation_name(String information_name) {
        this.information_name = information_name;
    }

    public String getInformation_pic() {
        return information_pic;
    }

    public void setInformation_pic(String information_pic) {
        this.information_pic = information_pic;
    }
}
