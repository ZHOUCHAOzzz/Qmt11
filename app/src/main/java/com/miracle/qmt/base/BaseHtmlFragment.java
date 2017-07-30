package com.miracle.qmt.base;


import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;


import com.miracle.qmt.R;

import butterknife.Bind;

/**
 * Created by Miracle on 2016/11/23 16:07
 */
public class BaseHtmlFragment extends BaseFragment {
    public static final String CONTENT = "content";
    public static final String TITLE = "title";
    @Bind(R.id.wv)
    WebView wvContent;

    public static BaseHtmlFragment mFragment;
    public static BaseHtmlFragment newInstance(String content){
        mFragment = new BaseHtmlFragment();
        Bundle bundle = new Bundle();
        bundle.putString(CONTENT,content);
        mFragment.setArguments(bundle);
        return mFragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_basehtml;
    }

    @Override
    public void initView() {
        super.initView();
//        mTvTitle.setText((getIntent().hasExtra(TITLE)?getIntent().getStringExtra(TITLE):"百家安"));
        String newContent = (getArguments().getString(CONTENT));
        wvContent.setWebChromeClient(new WebChromeClient());
        wvContent.getSettings().setAllowFileAccess(true);
        wvContent.getSettings().setPluginState(WebSettings.PluginState.ON);
        wvContent.getSettings().setJavaScriptEnabled(true);
        wvContent.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        wvContent.getSettings().setLoadWithOverviewMode(true);
        wvContent.loadDataWithBaseURL(null, newContent, null, "utf-8", null);

    }

    @Override
    public void onNetErrorClick() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (wvContent != null) {
            wvContent.destroy();
        }
    }
}
