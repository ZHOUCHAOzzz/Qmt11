package com.miracle.qmt.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * 处理微信各种回调
 * Created by ZhouChao on 2017/5/8.
 */

public class WXPayEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {
    private static final String TAG = "aaaaaaaaa";

    private IWXAPI api;
    private static final String APP_ID = "wxc281922aa3bdbaa9";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, APP_ID);
        api.handleIntent(getIntent(), this);
      //  RegisterBroadcastReceiver();
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
        Toast.makeText(getApplicationContext(), "onReq", Toast.LENGTH_SHORT).show();

    }

    /**
     * 得到支付结果回调
     */
    @Override
    public void onResp(BaseResp resp) {
        Log.d("wangshuang", "onResp");

        Log.d(TAG, "微信支付结果resp==" + resp.errCode + "/n" + "resp.str" + resp.errStr);
        int paycode = resp.errCode;


        if (paycode == 0) {
            Log.d("11111111", "支付成功");

           // Toast.makeText(this, "支付成功", Toast.LENGTH_SHORT).show();
            /*Intent intent=new Intent(WXPayEntryActivity.this, PaySuccessActivity.class);
            startActivity(intent);*/
          //  joinpartner();

            sendBroadcast(new Intent("payresult"));

            this.finish();

          /*  Intent intent=new Intent(WXPayEntryActivity.this, OrderActivity.class);
           // intent.putExtra("succeed",1);
            startActivity(intent);*/


            //显示充值成功的页面和需要的操作
        }

        if (paycode == -1) {
            //错误
            Toast.makeText(this, "支付错误", Toast.LENGTH_SHORT).show();
            finish();


        }

        if (paycode == -2) {
            Toast.makeText(this, "用户已取消", Toast.LENGTH_SHORT).show();
            finish();

            //用户取消
        }

    }





}
