package com.miracle.qmt.ui.model;

import java.util.List;

/**
 * Created by Administrator on 2017/10/12.
 */

public class TXLscBean {
    /**
     * result : 0
     * info : [{"head_pic":"http://qmtong.nethttp://qmtong.net/uploads/20171010/59dc9b527a2c2.jpg","nick_name":"于腊梅","name":"于腊梅","user_id":15,"address":"长春市宽城区一匡街康泰乐园15-6-611","Management":"苗木展会！策划","buy":"长春市宽城区一匡街康泰乐园15-6-611","company_name":"吉林德隆文化传媒有限责任公司","pic":"/uploads/20171010/59dc9b7eb83f6.jpg,/uploads/20171010/59dc9b7eb86c0.jpg,/uploads/20171010/59dc9b7eb8938.jpg,/uploads/20171010/59dc9b7eb8ba5.jpg,/uploads/20171010/59dc9b7eb8e06.jpg,/uploads/20171010/59dc9b7eb9071.jpg,/uploads/20171010/59dc9b7eb92d8.jpg","add_time":"2017-10-12 09:47:30"}]
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
         * head_pic : http://qmtong.nethttp://qmtong.net/uploads/20171010/59dc9b527a2c2.jpg
         * nick_name : 于腊梅
         * name : 于腊梅
         * user_id : 15
         * address : 长春市宽城区一匡街康泰乐园15-6-611
         * Management : 苗木展会！策划
         * buy : 长春市宽城区一匡街康泰乐园15-6-611
         * company_name : 吉林德隆文化传媒有限责任公司
         * pic : /uploads/20171010/59dc9b7eb83f6.jpg,/uploads/20171010/59dc9b7eb86c0.jpg,/uploads/20171010/59dc9b7eb8938.jpg,/uploads/20171010/59dc9b7eb8ba5.jpg,/uploads/20171010/59dc9b7eb8e06.jpg,/uploads/20171010/59dc9b7eb9071.jpg,/uploads/20171010/59dc9b7eb92d8.jpg
         * add_time : 2017-10-12 09:47:30
         */

        private String head_pic;
        private String nick_name;
        private String name;
        private int user_id;
        private String address;
        private String Management;
        private String buy;
        private String company_name;
        private String pic;
        private String add_time;

        public String getHead_pic() {
            return head_pic;
        }

        public void setHead_pic(String head_pic) {
            this.head_pic = head_pic;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
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

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }
    }
}
