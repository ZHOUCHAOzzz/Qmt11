package com.miracle.qmt.network;


// @formatter:off

import com.google.gson.JsonObject;
import com.miracle.qmt.ui.model.Carousel;
import com.miracle.qmt.ui.model.CollectionBean;
import com.miracle.qmt.ui.model.ConDetailsBean;
import com.miracle.qmt.ui.model.ContractItem;
import com.miracle.qmt.ui.model.HomeCate;
import com.miracle.qmt.ui.model.HomeNewsItem;
import com.miracle.qmt.ui.model.LoginBean;
import com.miracle.qmt.ui.model.MessageBean;
import com.miracle.qmt.ui.model.NewsItem;
import com.miracle.qmt.ui.model.ResultBean;
import com.miracle.qmt.ui.model.SearchBean;
import com.miracle.qmt.ui.model.TXLBean;
import com.miracle.qmt.ui.model.TXLscBean;
import com.miracle.qmt.ui.model.UpUserBean;
import com.miracle.qmt.ui.model.VipBean;
import com.miracle.qmt.ui.model.WepayBean;
import com.miracle.qmt.util.UserManager;

import java.util.ArrayList;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import rx.Observable;

/**
 * Created by drakeet on 8/9/15.
 */
public interface XynApi {

    //1.首页轮播
    @GET("home/index/carousel")
    public Observable<MyResult<ArrayList<Carousel>>> carousel(
    );
    //2.首页类别
    @POST("home/index/information")
    public Observable<MyResult<ArrayList<HomeCate>>> information(
    );
    //3 消息详情
    @FormUrlEncoded
    @POST("home/index/informationdetail")
    public Observable<MyResult<NewsItem>> informationdetail(
            @Field("detail_id") String detail_id
    );
    //4.通讯录
    @FormUrlEncoded
    @POST("home/userinfo/tongxunlu")
    public Observable<MyResult<ArrayList<ContractItem>>> contract(
            @Field("user_id") String user_id,
            @Field("status") String status
    );

    //5.注册
    @FormUrlEncoded
    @POST("home/loginin/register")
    public Observable<MyResult<Object>> register(
            @Field("phone") String phone,
            @Field("password") String password,
            @Field("nick_name") String nick_name,
            @Field("code") String code
//            phone	手机号
//                    password	密码
//                    nick_name	昵称
//                    code	验证码
    );
    //6.注册验证码获取
    @FormUrlEncoded
    @POST("home/loginin/registercode")
    public Observable<MyResult<Object>> registercode(
            @Field("phone") String phone
//            phone	手机号
    );
    //7.登录
    @FormUrlEncoded
    @POST("home/loginin/login")
    public Observable<LoginBean> login(
            @Field("phone") String phone,
            @Field("password") String password
    //phone	手机号
    //password	密码
    );
    //8.个人信息获取
    @FormUrlEncoded
    @POST("home/userinfo/geren")
    public Observable<MyResult<UserManager.User>> geren(
            @Field("user_id") String user_id
    );
    //9.
    @FormUrlEncoded
    @POST("home/userinfo/gerengai")
    public Observable<UpUserBean> gerengai(
             @FieldMap Map<String, String> map
            /*@Field("user_id") String user_id,
            @Field("nick_name") String nick_name,
            @Field("address") String address,
            @Field("management") String management,
            @Field("buy") String buy,
            @Field("company_name") String company_name,
            @Field("name") String name,
            @Field("head_pic") String head_pic,
            @Field("phone") String phone,
            @Field("pic") String pic*/
    );
    //10.新闻列表
    @FormUrlEncoded
    @POST("home/index/informationlist")
    public Observable<MyResult<ArrayList<NewsItem>>> Informationlist(
            @Field("type") String type,
            @Field("page") int page
    );
    //11.上传图片
    @Multipart
    @POST("home/index/tutu")
    public Observable<MyResult<String>> tutu(
            @PartMap Map<String, RequestBody> params
    );

    //12.首页
    @FormUrlEncoded
    @POST("home/index/news")
    public Observable<MyResult<ArrayList<HomeNewsItem>>> news(
            @Field("type_id") String type
    );
    //13消息
    @FormUrlEncoded
    @POST("home/index/news")
    public Observable<MessageBean> messages(
            @Field("type_id") String type
    );
    //判断是否是合伙人
    @FormUrlEncoded
    @POST("api/common/ifpartner")
    public Observable<VipBean> checkvip(
            @Field("phone") String phone
    );
    //佣金
    @FormUrlEncoded
    @POST("api/common/commission")
    public Observable<JsonObject> commission(
            @Field("userid") String userid
    );
    //获取二维码
    @FormUrlEncoded
    @POST("api/wxpay/qrcode")
    public Observable<String> getqrcode(
            @Field("userid") int userid

    );
    //留言举报
    @FormUrlEncoded
    @POST("api/common/leavemsg")
    public Observable<ResultBean> report(
            @Field("userid") String userid,
            @Field("content") String content

    );

    //通讯录详情
    @FormUrlEncoded
    @POST("api/common/querymemberinfo")
    public Observable<ConDetailsBean> condetails(
            @Field("userid") String userid
    );
    //微信支付订单生产   /api/common/writeinopenid
    @FormUrlEncoded
    @POST("api/wxpay/jspay")
    public Observable<WepayBean> wechatpay(

            @Field("price") String price

    );
    //上传用户的openid   /api/common/joinpartner
    @FormUrlEncoded
    @POST("api/common/writeinopenid")
    public Observable<JsonObject> postopenid(

            @Field("userid") String userid,
            @Field("openid") String openid
    );
    //支付成功后加入分销合伙人  /api/common/joinpartner
    @FormUrlEncoded
    @POST("api/common/joinpartner")
    public Observable<JsonObject> joinpartner(
            @Field("userid") String userid,
            @Field("price") String price


    );
    //支付成功后加入分销合伙人  /api/common/joinpartner
    @FormUrlEncoded
    @POST("api/wxpay/withdraw")
    public Observable<JsonObject> tixian(
            @Field("price") String userid,
            @Field("openid") String openid
    );
    //检查推荐人的手机号是否是分销合伙人
    @FormUrlEncoded
    @POST("api/common/writeinreferrer")
    public Observable<JsonObject> checkphone(
            @Field("userid") String userid,
            @Field("phone") String phone
    );
    //搜索  api/common/selecttx
    @FormUrlEncoded
    @POST("api/common/search")
    public Observable<SearchBean> search(
            @Field("content") String content
    );
    //通讯录
    @FormUrlEncoded
    @POST("api/common/selecttx")
    public Observable<TXLBean> tongxunlu(
            @Field("1") String state
    );
    //咨询点击收藏
    @FormUrlEncoded
    @POST("home/index/informationonclick")
    public Observable<CollectionBean> collection(
            @Field("detail_id") String detail_id,
            @Field("user_id") String user_id
    );
    //10.我的收藏
    @FormUrlEncoded
    @POST("home/index/informationsclist")
    public Observable<MyResult<ArrayList<NewsItem>>> mycollection(
            @Field("user_id") String type

    );
    //通讯录点击收藏  home/index/txsclist
    @FormUrlEncoded
    @POST("home/index/txonclick")
    public Observable<CollectionBean> txl_collection(
            @Field("user_id") String detail_id,
            @Field("tx_id") String tx_id
    );
    //通讯录收藏列表
    @FormUrlEncoded
    @POST("home/index/txsclist")
    public Observable<TXLscBean> txl_collectionList(
            @Field("user_id") String user_id

    );

}
