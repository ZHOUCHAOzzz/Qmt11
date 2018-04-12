package com.miracle.qmt.ui.activity;

import android.content.Intent;
import android.widget.TextView;

import com.miracle.qmt.R;
import com.miracle.qmt.base.BaseActivity;

/**
 * Created by ZhouChao on 2017/9/27.
 */

public class TextActivity extends BaseActivity {
    private TextView textView;
    @Override
    public int getLayoutId() {
        return R.layout.activity_text;
    }

    @Override
    public void initView() {
        super.initView();
        textView= (TextView) findViewById(R.id.text);
        Intent intent=getIntent();
        String  manage=intent.getStringExtra("manage");
        textView.setText(manage);

    }

    @Override
    protected void onNetErrorClick() {

    }
}
