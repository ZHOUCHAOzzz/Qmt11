package com.miracle.qmt.util;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/7/30.
 */
public class UserManager {
    public static void saveUserInfo(Context context,User user) {
        PreferencesUtils.setPreferences(context,PreferencesUtils.USER_NAME,user.getName());
        PreferencesUtils.setPreferences(context,PreferencesUtils.USER_TEL,user.getPhone());
        PreferencesUtils.setPreferences(context,PreferencesUtils.USER_HEAD_PIC,user.getHead_pic());
        PreferencesUtils.setPreferences(context,PreferencesUtils.USER_ADDRESS,user.getAddress());
        PreferencesUtils.setPreferences(context,PreferencesUtils.USER_COMPANY_NAME,user.getCompany_name());
    }

    public static void saveLoginSuccInfo(Context context, String userID, String pwd, String tel){
        PreferencesUtils.setPreferences(context,PreferencesUtils.USER_ID,userID);
        PreferencesUtils.setPreferences(context,PreferencesUtils.USER_PWD,pwd);
        PreferencesUtils.setPreferences(context,PreferencesUtils.USER_TEL,tel);
    }

    public class User {
        private String user_id;//用户id
        private String nick_name;//	昵称
        private String address;//	地址
        private String management;//	经营
        private String buy;//	求购
        private String company_name;//	公司名称
        private String name;//真实名称
        private String head_pic;//头像
        private ArrayList<String> pic;//图组
        private String phone;
        private String pics;

        public String getPics() {
            return pics;
        }

        public void setPics(String pics) {
            this.pics = pics;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getManagement() {
            return management;
        }

        public void setManagement(String management) {
            this.management = management;
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

        public ArrayList<String> getPic() {
            return pic;
        }

        public void setPic(ArrayList<String> pic) {
            this.pic = pic;
        }
    }
}
