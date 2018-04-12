package com.miracle.qmt.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by hs on 2016/7/11.
 */
public class PreferencesUtils {

    //用户相关信息
    public final static String USER_ID = "user_id";//用户id
    public final static String USER_TEL = "user_tel";
    public final static String USER_PWD = "user_pwd";
    public final static String NICK_NAME = "nick_name";//昵称
    public final static String USER_ADDRESS = "address";
    public final static String USER_MANAGEMENT = "management";
    public final static String USER_BUY= "buy";
    public final static String USER_COMPANY_NAME = "company_name";
    public final static String USER_NAME= "name";
    public final static String USER_HEAD_PIC = "head_pic";
    public final static String USER_PIC = "pic";

    public static final String PREFERENCES_NAME = "info";
    public static final String TICKET = "ticket";
    public static final String JPUSH_SET_SUCC = "JPUSH_SET_SUCC";

    public static final String LATITUDE = "LATITUDE";
    public static final String LONTITUDE = "LONTITUDE";

    //适配因子，在首次启动的activity里面初始化
    public static final String ZOOM_RATIO = "zoom_ratio";

    public static String getPreferences(Context context, String name) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        return preferences.getString(name, "");
    }

    public static void setPreferences(Context context, String name, String value) {
        if (context != null && value != null) {
            SharedPreferences.Editor sharedata = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE).edit();
            sharedata.putString(name, value);
            sharedata.commit();
        }
    }

    public static Float getFloatPreferences(Context context, String name) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        return preferences.getFloat(name, 0);
    }

    public static void setFloatPreferences(Context context, String name, float value) {
        if (context != null && value != 0) {
            SharedPreferences.Editor sharedata = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE).edit();
            sharedata.putFloat(name, value);
            sharedata.commit();
        }
    }


    public static int getIntPreferences(Context context, String name) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        return preferences.getInt(name, 0);
    }

    public static void setIntPreferences(Context context, String name, int value) {
        SharedPreferences.Editor sharedata = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE).edit();
        sharedata.putInt(name, value);
        sharedata.commit();
    }

    public static boolean getBooleanPreferences(Context context, String name) {
        if (context == null) {
            return false;
        }
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        return preferences.getBoolean(name, false);
    }

    public static void setBooleanPreferences(Context context, String name, boolean value) {
        if (context != null) {
            SharedPreferences.Editor sharedata = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE).edit();
            sharedata.putBoolean(name, value);
            sharedata.commit();
        }

    }

    public static long getLongPreferences(Context context, String name) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        return preferences.getLong(name, 0L);
    }

    public static void setLongPreferences(Context context, String name, long value) {
        SharedPreferences.Editor sharedata = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE).edit();
        sharedata.putLong(name, value);
        sharedata.commit();
    }

    //清空数据
    public static void reset(Context context) {
        SharedPreferences.Editor sharedata = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE).edit();
        sharedata.clear().commit();
    }
}
