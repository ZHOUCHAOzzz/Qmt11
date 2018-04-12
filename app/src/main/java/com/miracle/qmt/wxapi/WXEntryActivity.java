package com.miracle.qmt.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.miracle.qmt.AppController;
import com.miracle.qmt.util.ConstantValue;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private static final int RETURN_MSG_TYPE_LOGIN = 1;
    private static final int RETURN_MSG_TYPE_SHARE = 2;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        //如果没回调onResp，八成是这句没有写
        AppController.mWxApi.handleIntent(getIntent(), this);
    }

    // 微信发送请求到第三方应用时，会回调到该方法
    @Override
    public void onReq(BaseReq req) {
    }
    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
    //app发送消息给微信，处理返回消息的回调

    @Override
    public void onResp(BaseResp resp) {
        Log.d("wangshuang","resp");

        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                if (RETURN_MSG_TYPE_SHARE == resp.getType())
                    Toast.makeText(this, "分享失败", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
                break;
            case BaseResp.ErrCode.ERR_OK:
                switch (resp.getType()) {
                    case RETURN_MSG_TYPE_LOGIN:
                        //拿到了微信返回的code,立马再去请求 access_token
                        Log.d("wangshuang","登录回调");
                        String code = ((SendAuth.Resp) resp).code;
                        getOpenID(code);
                        break;

                    case RETURN_MSG_TYPE_SHARE:
                        Toast.makeText(this, "分享成功", Toast.LENGTH_SHORT).show();
                        finish();
                        break;


                }


        }
    }
    private void getOpenID(String code) {
        // APP_ID和APP_Secret在微信开发平台添加应用的时候会生成，grant_type 用默认的"authorization_code"即可.
        String urlStr = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+ ConstantValue.APP_ID+"&secret="+ConstantValue.APP_Secret_ID+
                "&code="+code+"&grant_type=authorization_code";
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(urlStr, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String data = new String(bytes);
                JSONObject obj = null;
                try {
                    obj=new JSONObject(data);
                    String openid=obj.getString("openid");
                    Log.d("wangshuang",openid);
                    postOpenid(openid);
                   // sendBroadcast(new Intent("payresult"));


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                /*Log.d("wangshuang",data);
                Toast.makeText(WXEntryActivity.this, data, Toast.LENGTH_SHORT).show();*/

            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Log.d("wangshuang",throwable.toString());
            }
        });

       /* HttpUtils http = new HttpUtils();
        // 设置超时时间
        //        http.configCurrentHttpCacheExpiry(1000 * 10);
        http.send(HttpRequest.HttpMethod.GET, urlStr, null,
                new RequestCallBack<String>() {
                    // 接口回调
                    @Override
                    public void onSuccess(ResponseInfo<String> info) {
                        System.out.println("返回的json字符串：" + info.result);
                        Toast.makeText(getApplicationContext(), info.result, Toast.LENGTH_SHORT).show();

                        JSONObject obj = null;
                        try {
                            obj = new JSONObject(info.result);
                            //toast  OpenID
                            Toast.makeText(getApplicationContext(), obj.getString("openid"), Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(com.lidroid.xutils.exception.HttpException e, String s) {

                    }
                });*/
    }
    /*
    把openid上传给对应的用户
    * */
    private void postOpenid(final String openid) {

        String urlStr = ConstantValue.BASE_URL+"/api/common/writeinopenid";

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("userid", AppController.getInstance().getUser().getUser_id()+"");
        params.put("openid", openid);

        client.post(urlStr,params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String data = new String(bytes);

                Log.d("1111111111111","OpenId上传成功");
                Toast.makeText(WXEntryActivity.this, "OpenId上传成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.putExtra("openid", openid);
                intent.setAction("tixian");
                sendBroadcast(intent);
              //  sendBroadcast(new Intent("tixian"));

            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Log.d("1111111111111111",throwable.toString());
            }
        });


    }
}
