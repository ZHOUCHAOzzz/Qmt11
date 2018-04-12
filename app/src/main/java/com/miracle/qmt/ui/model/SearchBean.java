package com.miracle.qmt.ui.model;

import java.util.List;

/**
 * Created by ZhouChao on 2017/9/26.
 */

public class SearchBean {
    /**
     * result : 0
     * info : [{"user_id":6,"phone":"18240408225","password":"fec5c26156e0afb44e4871a3bac58fbd","salt":3014,"nick_name":"嗨狗","sex":1,"address":"哈哈哈哈哈哈","Management":"买水果好好好好好好好好好好好好好好呵呵哈哈哈，","buy":"水果呵呵哈哈哈","company_name":"沈阳公司","name":"","head_pic":"http://182.92.201.208:8080/uploads/20170913/59b897120ec5a.jpg","pic":"","add_time":1504707946,"openid":null,"referrerid":8},{"user_id":8,"phone":"13080808285","password":"540373344d5c383a4fd38e505773b3f3","salt":5553,"nick_name":"周超","sex":1,"address":"哦哦哦","Management":"哈哈哈哈哈哈哈哈默默哦哦哦默默默默默默","buy":"你好","company_name":"哈哈哈哈哈哈","name":"","head_pic":"http://182.92.201.208:8080/uploads/20170918/59bf61504139e.png","pic":"/uploads/20170926/59ca06c50703d.jpg,/uploads/20170926/59ca06c5075be.jpg,/uploads/20170926/59ca06c507847.jpg,/uploads/20170926/59ca06c507aab.jpg","add_time":1504967989,"openid":"oJNokxM0u_rrPVzyoGKpNCBGMEfM","referrerid":8}]
     */

    private int result;
    private List<InfoBean> info;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public List<InfoBean> getInfo() {
        return info;
    }

    public void setInfo(List<InfoBean> info) {
        this.info = info;
    }

    public static class InfoBean {
        /**
         * user_id : 6
         * phone : 18240408225
         * password : fec5c26156e0afb44e4871a3bac58fbd
         * salt : 3014
         * nick_name : 嗨狗
         * sex : 1
         * address : 哈哈哈哈哈哈
         * Management : 买水果好好好好好好好好好好好好好好呵呵哈哈哈，
         * buy : 水果呵呵哈哈哈
         * company_name : 沈阳公司
         * name :
         * head_pic : http://182.92.201.208:8080/uploads/20170913/59b897120ec5a.jpg
         * pic :
         * add_time : 1504707946
         * openid : null
         * referrerid : 8
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
        private Object openid;
        private int referrerid;

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

        public Object getOpenid() {
            return openid;
        }

        public void setOpenid(Object openid) {
            this.openid = openid;
        }

        public int getReferrerid() {
            return referrerid;
        }

        public void setReferrerid(int referrerid) {
            this.referrerid = referrerid;
        }
    }
}
