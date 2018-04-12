package com.miracle.qmt.ui.model;

import java.util.List;

/**
 * Created by Administrator on 2017/10/13.
 */

public class MessageBean {
    /**
     * state : 1
     * msg : 成功
     * data : [{"news_id":2,"news_content":"你帮全苗通发展，全苗通帮你赚钱\r\n\r\n1、做为一个苗木人谁不想拥有全国万人以上通讯录？\r\n2、做为一个苗木人谁不想让更多的人看到自己的广告？\r\n3、做为一个苗木人谁不想随时了解全国各地的苗木行情？\r\n4、做为一个苗木人谁不想赚更多的钱？\r\n5、做为一个苗木人谁不想让自己的好友能够在自己的带动下一起赚钱？\r\n这一切全苗通能够帮你实现！！！","add_time":"2017-10-13 14:05:23","type_id":1}]
     */

    private String state;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * news_id : 2
         * news_content : 你帮全苗通发展，全苗通帮你赚钱

         1、做为一个苗木人谁不想拥有全国万人以上通讯录？
         2、做为一个苗木人谁不想让更多的人看到自己的广告？
         3、做为一个苗木人谁不想随时了解全国各地的苗木行情？
         4、做为一个苗木人谁不想赚更多的钱？
         5、做为一个苗木人谁不想让自己的好友能够在自己的带动下一起赚钱？
         这一切全苗通能够帮你实现！！！
         * add_time : 2017-10-13 14:05:23
         * type_id : 1
         */

        private int news_id;
        private String news_content;
        private String add_time;
        private int type_id;

        public int getNews_id() {
            return news_id;
        }

        public void setNews_id(int news_id) {
            this.news_id = news_id;
        }

        public String getNews_content() {
            return news_content;
        }

        public void setNews_content(String news_content) {
            this.news_content = news_content;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public int getType_id() {
            return type_id;
        }

        public void setType_id(int type_id) {
            this.type_id = type_id;
        }
    }
}
