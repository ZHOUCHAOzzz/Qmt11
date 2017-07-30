package com.miracle.qmt.base;


import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;


import com.miracle.qmt.R;

import butterknife.Bind;

/**
 * Created by WJQ on 2016/9/19.
 */
public class BaseHtmlActivity extends BaseActivity {
    public static final String CONTENT = "content";
    public static final String TITLE = "title";
    @Bind(R.id.wv)
    WebView wvContent;

    @Override
    public int getLayoutId() {
        return R.layout.activity_basehtml;
    }

    @Override
    public void initView() {
        super.initView();
        mTvTitle.setText((getIntent().hasExtra(TITLE)?getIntent().getStringExtra(TITLE):"百家安"));
        String newContent = (getIntent().hasExtra(CONTENT)) ? getIntent().getStringExtra(CONTENT) : "无内容";
        wvContent.setWebChromeClient(new WebChromeClient());
        wvContent.getSettings().setAllowFileAccess(true);
        wvContent.getSettings().setPluginState(WebSettings.PluginState.ON);
        wvContent.getSettings().setJavaScriptEnabled(true);
        wvContent.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        wvContent.getSettings().setLoadWithOverviewMode(true);
        wvContent.loadDataWithBaseURL(null, newContent, null, "utf-8", null);

    }

    @Override
    protected void onNetErrorClick() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (wvContent != null) {
            wvContent.destroy();
        }
    }
}
