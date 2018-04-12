package com.miracle.qmt.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.miracle.qmt.R;
import com.miracle.qmt.base.BaseActivity;
import com.miracle.qmt.ui.contract.CommissionContract;
import com.miracle.qmt.ui.model.ComBean;
import com.miracle.qmt.ui.presenter.CommissionPresenter;
import com.miracle.qmt.util.ConstantValue;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.leefeng.promptlibrary.PromptDialog;

/**
 * Created by ZhouChao on 2017/9/7.
 */

public class CommissionActivity extends BaseActivity<CommissionPresenter> implements CommissionContract.View {
    @Bind(R.id.iv_title_left)
    ImageView ivTitleLeft;
    @Bind(R.id.title_txt)
    TextView titleTxt;
    @Bind(R.id.tv_title_right)
    TextView tvTitleRight;
    @Bind(R.id.tv_money)
    TextView tvMoney;
    @Bind(R.id.et_money)
    EditText etMoney;
    @Bind(R.id.tv_submit)
    TextView tvSubmit;
    private PromptDialog promptDialog;
    private IWXAPI msgApi;
    private int yue;
    private LogBroadcastReceiver logBroadcastReceiver;

    @Override
    public void getData(JsonObject comBean) {
        if (promptDialog!=null){
            promptDialog.dismiss();
        }


    if (comBean.get("result").toString().equals("1")){
        Toast.makeText(CommissionActivity.this,"您还不是分销合伙人",Toast.LENGTH_SHORT).show();
        tvMoney.setText("0.0");

    }else {
        ComBean comBean1=new Gson().fromJson(comBean.toString(),ComBean.class);
        yue=comBean1.getInfo().getBalance();

        tvMoney.setText(comBean1.getInfo().getBalance()+"");

        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etMoney.getText().toString())) {
                    showErrorDialog("请输入提现金额");
                    return;
                }
                if (Integer.parseInt(etMoney.getText().toString())>yue) {
                    showErrorDialog("请输入正确的提现金额");
                    return;
                }
                if(etMoney.getText().toString().equals("0")){
                    showErrorDialog("请输入正确的提现金额");
                    return;
                }
                    dialog();



            }
        });
    }

      /*  tvMoney.setText(comBean.toString());*/


    }
    /*
    * 提现之后的回调
    * */
    @Override
    public void tixian(JsonObject comBean) {

        showSuccDialog("提现成功");
        mPresenter.getData();

    }

    @Override
    public int getLayoutId() {

        return R.layout.activity_comm;

    }

    @Override
    public void initView() {
        promptDialog=new PromptDialog(CommissionActivity.this);
        super.initView();
        titleTxt.setText("佣金提现");
        showProgressDialog("获取中");
        RegisterBroadcastReceiver();

    }

    private void dialog(){
        final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setTitle("登录微信");
        builder.setMessage("请登录你要提现到的微信号");
        // builder.setMessage("这是 android.support.v7.app.AlertDialog 中的样式");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                getOpenid();


            }
        });
        builder.show();
    }
    public void getOpenid(){
        msgApi = WXAPIFactory.createWXAPI(this, ConstantValue.APP_ID,true);
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_demo_test";
        Toast.makeText(mContext, "前往微信登录", Toast.LENGTH_SHORT).show();
        msgApi.sendReq(req);
    }
    /**
     * 注册广播
     */
    private void RegisterBroadcastReceiver() {
        logBroadcastReceiver = new LogBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter("tixian");
        registerReceiver(logBroadcastReceiver, intentFilter);
    }
    /**
     * 支付成功后接收到广播
     */
    class LogBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("tixian")) {
                Bundle bundle = intent.getExtras();
                String openid = bundle.getString("openid");
                mPresenter.tixian(etMoney.getText().toString(),openid);


            }


        }
    }

    @Override
    protected void onNetErrorClick() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
