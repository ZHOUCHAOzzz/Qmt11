package com.miracle.qmt.ui.model;

import java.util.List;

/**
 * Created by Administrator on 2017/10/4.
 */

public class MessBean {
    /**
     * state : 1
     * msg : 成功
     * data : [{"news_id":1,"news_content":"这是一条测试消息","add_time":"2017-09-29 13:13:06","type_id":2}]
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
         * news_id : 1
         * news_content : 这是一条测试消息
         * add_time : 2017-09-29 13:13:06
         * type_id : 2
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
