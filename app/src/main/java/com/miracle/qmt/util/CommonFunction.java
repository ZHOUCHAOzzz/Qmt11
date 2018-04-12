package com.miracle.qmt.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Administrator on 2017/7/29.
 */

public class CommonFunction {
    /**
     * 显示图片
     * @param context
     */
    public static void showImage(Context context, String url, ImageView imageView){
        Glide.with(context).load(url).thumbnail(0.4f).into((imageView));
    }

    /**
     * 显示图片
     * @param context
     */
    public static void showImage(Context context, String url, ImageView imageView,int errorImg){
        //Glide.with(context).load(url).thumbnail(0.4f).error(errorImg).into((imageView));
    }
}
