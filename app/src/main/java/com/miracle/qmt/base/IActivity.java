package com.miracle.qmt.base;

/**
 * Activity和Fragment共同实现的一些方法
 * Created by WJQ on 2016/11/10.
 */

public interface IActivity {
    //初始化视图 在OnCreate中调用
    public void initView();

    //初始化数据 在OnCreate中调用
    public void initData();

    //成功消息提示
    public void showSuccDialog(String msg);

    //失败消息提示
    public void showErrorDialog(String msg);

    //进度条
    public void showProgressDialog(String msg);

    //联网状态
    public void onStateSuccess();
    public void onStateFail();
    public void onStateNetError();
    public void showLoading();
    public void hideLoading();
    public void showNoData();
    public void hideNoData();
    public void showNetErr();
    public void hideNetErr();

}
