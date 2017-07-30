package com.miracle.qmt.network;


// @formatter:off

import com.miracle.qmt.ui.model.Carousel;
import com.miracle.qmt.ui.model.ContractItem;
import com.miracle.qmt.ui.model.HomeCate;
import com.miracle.qmt.ui.model.LoginSuccModel;
import com.miracle.qmt.ui.model.NewsItem;
import com.miracle.qmt.util.UserManager;

import java.util.ArrayList;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
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
    @POST("home/index/contract")
    public Observable<MyResult<ArrayList<ContractItem>>> contract(
            @Field("detail_id") String detail_id
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
    public Observable<MyResult<LoginSuccModel>> login(
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
    public Observable<MyResult<Object>> gerengai(
            @Field("user_id") String user_id,
            @Field("nick_name") String nick_name,
            @Field("address") String address,
            @Field("management") String management,
            @Field("buy") String buy,
            @Field("company_name") String company_name,
            @Field("name") String name,
            @Field("head_pic") String head_pic,
            @Field("phone") String phone,
            @Field("pic") String pic
    );
    //10.新闻列表
    @FormUrlEncoded
    @POST("home/index/informationlist")
    public Observable<MyResult<ArrayList<NewsItem>>> Informationlist(
            @Field("type") String type,
            @Field("page") int page
    );

    //11.上传图片
    @FormUrlEncoded
    @POST("home/index/tutu")
    public Observable<MyResult<Object>> tutu(
            @PartMap Map<String, RequestBody> params
    );

}
