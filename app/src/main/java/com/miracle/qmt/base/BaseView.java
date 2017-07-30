package com.miracle.qmt.base;

/**
 * Created by WJQ on 2016/11/10.
 */

public interface BaseView {

    //网络错误
    public void onNetError();

    //请求完成
    public void onComplete();

    //请求失败或其它
    public void showMsg(String msg);

//    public void onStatusSucc(Boolean hasOrderUnPay);
}
