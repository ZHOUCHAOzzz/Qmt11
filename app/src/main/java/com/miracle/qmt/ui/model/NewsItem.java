package com.miracle.qmt.ui.model;

import java.util.List;

/**
 * Created by Administrator on 2017/7/26.
 * 通用消息
 */
public class NewsItem {

    /**
     * detail_id : 1
     * detail_name : 盘锦绿化苗批发
     * first_pic : http://47.94.172.244/public/uploads/12/1.png
     * content : 111111111111111111111111111111111
     * pic : ["http://47.94.172.244"]
     * nick_name : 王大力
     * add_time : 2017-07-19 18:16:50
     * collection : 2
     * love : 0
     */

    private int detail_id;
    private String detail_name;
    private String first_pic;
    private String content;
    private String nick_name;
    private String add_time;
    private int collection;
    private int love;
    private List<String> pic;

    public int getDetail_id() {
        return detail_id;
    }

    public void setDetail_id(int detail_id) {
        this.detail_id = detail_id;
    }

    public String getDetail_name() {
        return detail_name;
    }

    public void setDetail_name(String detail_name) {
        this.detail_name = detail_name;
    }

    public String getFirst_pic() {
        return first_pic;
    }

    public void setFirst_pic(String first_pic) {
        this.first_pic = first_pic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public int getCollection() {
        return collection;
    }

    public void setCollection(int collection) {
        this.collection = collection;
    }

    public int getLove() {
        return love;
    }

    public void setLove(int love) {
        this.love = love;
    }

    public List<String> getPic() {
        return pic;
    }

    public void setPic(List<String> pic) {
        this.pic = pic;
    }
}
