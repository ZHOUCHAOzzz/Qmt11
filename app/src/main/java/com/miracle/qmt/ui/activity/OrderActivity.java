package com.miracle.qmt.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.burgstaller.okhttp.digest.fromhttpclient.NameValuePair;
import com.google.gson.JsonObject;
import com.miracle.qmt.R;
import com.miracle.qmt.base.BaseActivity;
import com.miracle.qmt.ui.contract.OrderPayContract;
import com.miracle.qmt.ui.model.WepayBean;
import com.miracle.qmt.ui.presenter.OrderPresenter;
import com.miracle.qmt.util.ConstantValue;
import com.miracle.qmt.util.MD5;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ZhouChao on 2017/9/5.
 */

public class OrderActivity extends BaseActivity<OrderPresenter> implements OrderPayContract.View {

    @Bind(R.id.iv_title_left)
    ImageView ivTitleLeft;
    @Bind(R.id.title_txt)
    TextView titleTxt;
    @Bind(R.id.iv_title_right)
    ImageView ivTitleRight;
    @Bind(R.id.tv_wechatpay)
    TextView tvWechatpay;
    @Bind(R.id.et_tuijian)
    EditText etPhone;
    private IWXAPI msgApi;


    @Override
    public int getLayoutId() {
        return R.layout.activity_order;
    }

    @Override
    public void initView() {
        super.initView();
        mTvTitle.setText("确认支付");
       // getOpenid();
        Intent intent=getIntent();
        if (intent.getIntExtra("succeed",0)==1){
            finish();
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

    @OnClick(R.id.tv_wechatpay)
    public void onViewClicked() {
        //手机号不能为空
        if (TextUtils.isEmpty(etPhone.getText().toString().trim())) {
            final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
            builder.setTitle("你确定不输入手机号吗？");
            builder.setMessage("如果有推荐人请输入推荐人手机号，推荐人会获得佣金奖励，如果没有请点确定");
            // builder.setMessage("这是 android.support.v7.app.AlertDialog 中的样式");
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                  //  mPresenter.checkphone(etPhone.getText().toString().trim());
                    mPresenter.pay();

                }
            });
            builder.show();
           // showErrorDialog("手机号不能为空");
            return;
        }
        mPresenter.checkphone(etPhone.getText().toString().trim());



    }



    @Override
    public void pay(WepayBean bean) {
        // 将该app注册到微信
        msgApi = WXAPIFactory.createWXAPI(this, ConstantValue.APP_ID,true);
        PayReq request = new PayReq();
        request.appId = bean.getAppid();
        request.partnerId = bean.getPartnerid();
        request.prepayId = bean.getPrepayid();
        request.packageValue =bean.getPackageX();
        request.nonceStr = bean.getNoncestr();bean.getSign();
        request.timeStamp = bean.getTimestamp();
        request.sign = bean.getSign();
                Toast.makeText(OrderActivity.this, "正在前往微信", Toast.LENGTH_SHORT).show();
        msgApi.sendReq(request);
        this.finish();
    }
    /*
    * 推荐人是否是分销合伙人的回调
    * */
    @Override
    public void checkphone(JsonObject obj) {
        switch (Integer.parseInt(obj.get("result").toString())){
            case 0:
                mPresenter.pay();

                break;
            case 1:
                showErrorDialog(obj.get("info").toString());
                break;


        }

    }

    public String getTime(){
        //获取当前时间戳
        long timeStamp = System.currentTimeMillis();

        String  str=String.valueOf(timeStamp);
        return str;

    }
    private long genTimeStamp() {
        return System.currentTimeMillis() / 1000;
    }

    private String genAppSign(List<NameValuePair> params) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < params.size(); i++) {
            sb.append(params.get(i).getName());
            sb.append('=');
            sb.append(params.get(i).getValue());
            sb.append('&');
        }
        sb.append("key=");
        sb.append(ConstantValue.APP_ID);

        String appSign = MD5.getMessageDigest(sb.toString().getBytes()).toUpperCase();
        Log.e("sign", appSign);
        return appSign;
    }
}
