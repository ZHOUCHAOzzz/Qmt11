package com.miracle.qmt.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

/**
 * Created by ZhouChao on 2017/9/4.
 */

public class CustomDialog extends Dialog {
    private Context context;
    private int resId;
    public CustomDialog(Context context, int resLayout) {
        this(context,0,0);
    }
    public CustomDialog(Context context, int themeResId, int resLayout) {
        super(context, themeResId);
        this.context = context;
        this.resId = resLayout;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(resId);
    }

}
