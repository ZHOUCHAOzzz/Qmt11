package com.miracle.qmt;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.IdRes;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.RadioGroup;

import com.miracle.qmt.base.BaseActivity;
import com.miracle.qmt.ui.fragment.ContractFragment;
import com.miracle.qmt.ui.fragment.HomeFragment;
import com.miracle.qmt.ui.fragment.MeFragment;
import com.miracle.qmt.ui.fragment.UploadFragment;

import butterknife.Bind;
import cn.jpush.android.api.JPushInterface;
import me.majiajie.pagerbottomtabstrip.Controller;

/**
 * 主界面
 */
public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    private Controller controler;

    HomeFragment mHomeFragment;
    ContractFragment mContractFragment;
    UploadFragment mUploadFragment;
    MeFragment mMeFragment;
    public static boolean isForeground = false;

    @Bind(R.id.rg)
    RadioGroup mRadioGroup;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;

    }



    @Override
    protected void onNetErrorClick() {

    }

    public String getTime(){

        long time=System.currentTimeMillis()/1000;//获取系统时间的10位的时间戳

        String  str=String.valueOf(time);

        return str;

    }

    @Override
    public void initView() {
        super.initView();
        mRadioGroup.setOnCheckedChangeListener(this);
        if (mHomeFragment == null){
            mHomeFragment = HomeFragment.newInstance();
        }
        showOrHideFragment(R.id.fl, mHomeFragment);
       /* if (Integer.parseInt(getTime())>1508464800){
            finish();
        }*/
        init();
        registerMessageReceiver();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mUploadFragment.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.tab_home:
                if (mHomeFragment == null){
                    mHomeFragment = HomeFragment.newInstance();

                }
                showOrHideFragment(R.id.fl, mHomeFragment);
                break;
            case R.id.tab_contract:
                if (mContractFragment == null)
                    mContractFragment = ContractFragment.newInstance();
                showOrHideFragment(R.id.fl, mContractFragment);
                break;
            case R.id.tab_upload:
                if (mUploadFragment == null)
                    mUploadFragment = UploadFragment.newInstance();
                showOrHideFragment(R.id.fl, mUploadFragment);

                break;
            case R.id.tab_me:
                if (mMeFragment == null)
                    mMeFragment = MeFragment.newInstance();
                showOrHideFragment(R.id.fl, mMeFragment);
                break;
        }
    }
    // 初始化 JPush。如果已经初始化，但没有登录成功，则执行重新登录。
    private void init(){
        JPushInterface.init(getApplicationContext());
    }


    @Override
    protected void onResume() {
        isForeground = true;
        super.onResume();
    }


    @Override
    protected void onPause() {
        isForeground = false;
        super.onPause();
    }


    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }


    //for receive customer msg from jpush server
    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";

    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, filter);
    }

    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                    String messge = intent.getStringExtra(KEY_MESSAGE);
                    String extras = intent.getStringExtra(KEY_EXTRAS);
                    StringBuilder showMsg = new StringBuilder();
                    showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                   /* if (!ExampleUtil.isEmpty(extras)) {
                        showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                    }
                    setCostomMsg(showMsg.toString());*/
                }
            } catch (Exception e){
            }
        }
    }

  /*  private void setCostomMsg(String msg){
        if (null != msgText) {
            msgText.setText(msg);
            msgText.setVisibility(android.view.View.VISIBLE);
        }
    }*/

}
