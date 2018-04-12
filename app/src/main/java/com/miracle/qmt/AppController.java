package com.miracle.qmt;

import android.app.Application;

import com.miracle.qmt.ui.model.UserBean;
import com.miracle.qmt.util.ConstantValue;
import com.miracle.qmt.util.UserLocalData;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;


/**
 * Created by Administrator on 2017/7/31.
 */

public class AppController extends Application {
    private static AppController application;
    private UserBean user;
    public static IWXAPI mWxApi;
    @Override
    public void onCreate() {

        super.onCreate();
        application = this;
        initCamer();
        initUser();
        registToWX();
        PlatformConfig.setWeixin(ConstantValue.APP_ID, ConstantValue.APP_Secret_ID);
        UMShareAPI.get(this);




    }



    //注册微信
    private void registToWX() {

         mWxApi = WXAPIFactory.createWXAPI(this, ConstantValue.APP_ID, false);
         //将该app注册到微信
         mWxApi.registerApp(ConstantValue.APP_ID);
    }





    private void initUser(){
        //getUserOnline();

        this.user = UserLocalData.getUser(this);
    }


    public UserBean getUser(){
        return user;
    }

    public void putUser(UserBean user, String token){
        this.user = user;
        UserLocalData.putUser(this,user);
        UserLocalData.putToken(this,token);
    }

    public void clearUser(){
        this.user =null;
        UserLocalData.clearUser(this);
        UserLocalData.clearToken(this);


    }
    public String getOpenId(){

        return  UserLocalData.getOpenId(this);
    }
    public void putOpenId(String openid){
        UserLocalData.putOpenId(this,openid);

    }
    public static AppController getInstance() {
        return application;
    }

    private void initCamer() {
        /*//配置功能
        FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(true)
                .setEnableEdit(true)
                .setEnableCrop(true)
                .setCropSquare(true)
                .setEnableRotate(true)
                .setEnablePreview(true)
                .setForceCrop(true)
                .build();
        //ThemeConfig.CYAN
        ThemeConfig theme = new ThemeConfig.Builder()
                .setTitleBarBgColor(Color.rgb(29, 207, 155))
                .build();*/

    }
}
