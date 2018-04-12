package com.miracle.qmt.util;

import android.content.Context;
import android.text.TextUtils;

import com.miracle.qmt.ui.model.UserBean;


/**
 * ProjectName:YayaShop
 * Autor： <a href="http://www.cniao5.com">菜鸟窝</a>
 * Description：
 * <p/>
 * 菜鸟窝是一个只专注做Android开发技能的在线学习平台，课程以实战项目为主，对课程与服务”吹毛求疵”般的要求，打造极致课程，是菜鸟窝不变的承诺
 */
public class UserLocalData {


    public static void putUser(Context context, UserBean user){


        String user_json =  JSONUtil.toJSON(user);
       // L.e(user_json);
        PUtils.putString(context, Contants.USER_JSON,user_json);

    }

    public static void putToken(Context context, String token){

        PUtils.putString(context, Contants.TOKEN,token);
    }


    public static UserBean getUser(Context context){

        String user_json= PUtils.getString(context,Contants.USER_JSON);
        if(!TextUtils.isEmpty(user_json)){

            return  JSONUtil.fromJson(user_json,UserBean.class);
        }
        return  null;
    }

    public static String getToken(Context context){

        return  PUtils.getString( context,Contants.TOKEN);

    }
    public static String getOpenId(Context context){

        return  PUtils.getString( context,Contants.OPENID);

    }
    public static void putOpenId(Context context, String openid){

        PUtils.putString(context, Contants.OPENID,openid);
    }


    public static void clearUser(Context context){


        PUtils.putString(context, Contants.USER_JSON,"");

    }

    public static void clearToken(Context context){

        PUtils.putString(context, Contants.TOKEN,"");
    }



}
