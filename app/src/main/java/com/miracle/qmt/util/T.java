package com.miracle.qmt.util;


import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.miracle.qmt.R;

/**
 * Toast统一管理类
 */
public class T {
    // Toast
    private static Toast toast;

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, CharSequence message) {
        if (null == toast) {
//            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
//            LinearLayout linearLayout = (LinearLayout) toast.getView();
//            TextView messageTextView = (TextView) linearLayout.getChildAt(0);
////            messageTextView.setTextSize(25);
//            messageTextView.setTextColor(context.getResources().getColor(R.color.colorPrimary));
//            messageTextView.setBackgroundResource(R.color.white);
//            toast.setGravity(Gravity.CENTER, 0, 0);

            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            View view = toast.getView();
//            view.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
            view.setBackgroundResource(R.drawable.shape_primary_corner);
            ViewGroup.LayoutParams params = view.getLayoutParams();
            view.setPadding(10,5,10,5);
            toast.setView(view);
            toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, int message) {
        if (null == toast) {
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);

            View view = toast.getView();
//            view.setBackgroundColor(context.getResources().getColor(R.color.green_1));
            view.setBackgroundResource(R.drawable.shape_primary_corner);
            view.setPadding(10,5,10,5);
            toast.setView(view);
            // toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showLong(Context context, CharSequence message) {
        if (null == toast) {
            toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
            // toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showLong(Context context, int message) {
        if (null == toast) {
            toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
            // toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param message
     * @param duration
     */
    public static void show(Context context, CharSequence message, int duration) {
        if (null == toast) {
            toast = Toast.makeText(context, message, duration);
            // toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param message
     * @param duration
     */
    public static void show(Context context, int message, int duration) {
        if (null == toast) {
            toast = Toast.makeText(context, message, duration);
            // toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * Hide the toast, if any.
     */
    public static void hideToast() {
        if (null != toast) {
            toast.cancel();
        }
    }
}
