package com.miracle.qmt.ui.model;

/**
 * Created by Administrator on 2017/7/29.
 */

public class Carousel {

    /**
     * carousel_id : 3
     * pic_name : 轮播第三张
     * pic : http://47.94.172.244/uploads/12/3.png
     * add_time : 2017-07-19 18:17:40
     */

    private int carousel_id;
    private String pic_name;
    private String pic;
    private String add_time;

    public int getCarousel_id() {
        return carousel_id;
    }

    public void setCarousel_id(int carousel_id) {
        this.carousel_id = carousel_id;
    }

    public String getPic_name() {
        return pic_name;
    }

    public void setPic_name(String pic_name) {
        this.pic_name = pic_name;
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
