package com.miracle.qmt.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import com.miracle.qmt.R;
import com.miracle.qmt.base.BaseActivity;
import com.miracle.qmt.ui.contract.WebviewContract;
import com.miracle.qmt.ui.presenter.WebviewPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ZhouChao on 2017/9/6.
 */

public class WebViewActivity extends BaseActivity<WebviewPresenter> implements WebviewContract.View {
    @Bind(R.id.webview)
    WebView webview;



    @Override
    public int getLayoutId() {
        return R.layout.activity_webview;
    }

    @Override
    protected void onNetErrorClick() {


    }

    @Override
    public void getQrcode(String url) {
        Log.d("url1",url);
        webview.loadUrl("http://"+url);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
