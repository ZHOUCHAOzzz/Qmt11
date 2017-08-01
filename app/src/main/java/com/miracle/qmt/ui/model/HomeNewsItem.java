package com.miracle.qmt.ui.model;

/**
 * Created by Administrator on 2017/7/31.
 */
public class HomeNewsItem {

    /**
     * news_id : 1
     * news_content : 携手合作共创未来
     * add_time : 2017-07-19 18:16:50
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
