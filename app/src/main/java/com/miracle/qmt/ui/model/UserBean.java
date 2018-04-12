package com.miracle.qmt.ui.model;

/**
 * Created by Administrator on 2017/9/10.
 */

public class UserBean {
    /**
     * user_id : 2
     * phone : 18240408225
     * password : def26451f27ca3fabae968f247780dae
     * salt : 8009
     * nick_name :
     * sex : 1
     * address :
     * Management : 你好
     * buy : null
     * company_name :
     * name : 汪
     * head_pic :
     * pic : null
     * add_time : 1504499497
     */

    private int user_id;
    private String phone;
    private String password;
    private int salt;
    private String nick_name;
    private int sex;
    private String address;
    private String Management;
    private String buy;
    private String company_name;
    private String name;
    private String head_pic;
    private String pic;
    private int add_time;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSalt() {
        return salt;
    }

    public void setSalt(int salt) {
        this.salt = salt;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getManagement() {
        return Management;
    }

    public void setManagement(String Management) {
        this.Management = Management;
    }

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHead_pic() {
        return head_pic;
    }

    public void setHead_pic(String head_pic) {
        this.head_pic = head_pic;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getAdd_time() {
        return add_time;
    }

    public void setAdd_time(int add_time) {
        this.add_time = add_time;
    }
}
