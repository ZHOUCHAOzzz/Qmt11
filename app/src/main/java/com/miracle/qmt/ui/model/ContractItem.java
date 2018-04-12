package com.miracle.qmt.ui.model;

/**
 * Created by Administrator on 2017/7/30.
 */
public class ContractItem  {
    private String hail_fellow_id;
    private String user_id;
    private String address;
    private String nick_name;
    private String head_pic;

    private String name;   //显示的数
    private String sortLetters;  //显示数据拼音的首字母

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSortLetters() {
        return sortLetters;
    }

    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }

    public String getHail_fellow_id() {
        return hail_fellow_id;
    }

    public void setHail_fellow_id(String hail_fellow_id) {
        this.hail_fellow_id = hail_fellow_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getHead_pic() {
        return head_pic;
    }

    public void setHead_pic(String head_pic) {
        this.head_pic = head_pic;
    }


}
