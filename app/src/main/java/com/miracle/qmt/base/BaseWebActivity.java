package com.miracle.qmt.base;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.miracle.qmt.R;
import com.orhanobut.logger.Logger;
/**
 * Created by Miracle on 2016/11/23 16:06
 */
public class BaseWebActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    public static final String URL = "url";
    public static final String TITLE = "title";

    public String mUrl;
    public WebView wv;
    private SwipeRefreshLayout mSwipeRefreshLayout;


    @Override
    public int getLayoutId() {
        return R.layout.activity_baseweb;
    }


    @Override
    public void initView() {
        super.initView();
        mTvTitle.setText(getIntent().hasExtra(TITLE)?getIntent().getStringExtra(TITLE):"QMT");
        mUrl = getIntent().getStringExtra(URL);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        wv = (WebView) findViewById(R.id.wv_fragment);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        wv.getSettings().setBlockNetworkImage(true);
        // 设置渲染优先级
        wv.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        // 支持通过JS打开新窗口
        wv.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        // 设置可以访问文件
        wv.getSettings().setAllowFileAccess(true);
        // 开启应用程序缓存
        wv.getSettings().setAppCacheEnabled(true);

        wv.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        wv.getSettings().setLoadWithOverviewMode(true);
        wv.getSettings().setDomStorageEnabled(true);
//        wv.getSettings().setUseWideViewPort(true);

        wv.setWebViewClient(new WebViewClient() {

            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                if (url.endsWith("apk")) {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    Uri content_url = Uri.parse(url);
                    intent.setData(content_url);
                    startActivity(intent);
                } else {        //正常页
                    view.loadUrl(url);
                }
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                showLoading();
                mUrl = url;
                super.onPageStarted(view, url, favicon);
                Logger.d("onPageStarted" + url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                wv.getSettings().setBlockNetworkImage(false);
                onStateSuccess();
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                onStateNetError();
            }
        });
        wv.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
                Logger.d("BaseWebFragment onCreateWindow");
                return super.onCreateWindow(view, isDialog, isUserGesture, resultMsg);
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                Logger.d("BaseWebFragment onJsAlert");
                return super.onJsAlert(view, url, message, result);
            }
        });

        wv.loadUrl(mUrl);
    }

    @Override
    public void onNetErrorClick() {

    }



    @Override
    public void onRefresh() {
        showLoading();
        wv.loadUrl(mUrl);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onBackPressed() {
        if (wv != null && wv.canGoBack()) {
            wv.goBack();
        } else {
            finish();
        }
    }

    public void onBack() {
        if (wv != null && wv.canGoBack()) {
            wv.goBack();
        } else {
//            getActivity().onBackPressed();、
            finish();
        }
    }


}
