package com.miracle.qmt.util;

import android.os.Environment;

/**
 *
 * 各种常量！！！！
 * Created by ZhouChao on 2017/3/2.
 */

public class Contants {

    public static final String TOKEN="token";
    public static final String USER_JSON="user_json";
    public static final String STOREID="store_id";
    public static final String URL="url";
    public static final String TEL="tel";
    public static final String VERSION="version";
    public static final String ACNAME="acname";
    public static final String OPENID="openId";
    public static final String HXID="quanjihui_client_";
    public static final String HXPASS="123456789";
    public static final String USER_NAME="username";
    public static final String USER_PIC="userpic";




    public static class API{
        public static final String B="http://app.quanjihui.cn/";
        public static final String B1="http://tt.zgxc365.com/";
        public static final String NEWITEM=B+"article/article/item?id=1";
        public static final String BASE="https://mall.quanjihui.cn/app/index.php/";
        /**
         * 发送验证码
         */
        public static final String SEND_VERIFY=B+"user/login/send_verify";
        /**
         * 手机收到验证码匹配
         */
        public static final String CHECK_VERIFY=B+"user/login/check_verify";
        /**
         * 手机用户登录
         */
        public static final String USER_LOGIN=B+"user/login/login";
        /**
         * 首页
         */
        public static final String HOME = B+"home/index/indexdata";
        /**
         * 附近的商家
         */
        public static final String NEARSTORE=B+"service/store/nearbystore";
        /**
         * 所有商家
         */
        public static final String ALLSTORE=B+"service/store/storeList";
        /**
         * 新闻列表
         */
        public static final String NEWS=B+"article/article/articleList";
        /**
         * 商家详情
         */
        public static final String STOREDETAILS=B+"service/store/storeitem";
        /**
         * 订单列表
         */
        public static final String ORDER=B+"service/order/OrderList";

        public static final String NEWSSWIPER=B+"article/article/swiper";
        /**
         * 微信用户登录
         */
        public static final String WECHATLOGIN=B+"user/login/wechat_login_app";
        /**
         * QQ用户登录
         */
        public static final String QQLOGIN=B+"user/login/qq_login_app";
        /**
         * 用户修改头像
         */
        public static final String POSTIMAGE=B+"file/images/headerupload";
        /**
         * 微信登录绑定手机号
         */
        public static final String WECHATBIND=B+"user/login/wechat_bind";
        /**
         * 修改用户信息
         */
        public static final String EDITUSER=B+"user/user/edituser";
        /**
         * 根据UID获取用户信息
         */
        public static final String USERINFO=B+"user/user/userinfo";
        /**
         * 生成订单
         */
        public static final String CREATORDER=B+"service/order/placeOrder";
        /**
         * 支付宝
         */
        public static final String ALIPAY="http://pay.zgxc365.com/alipaywap/alipayapi.php?order_sn=";
        /**
         * 微信支付
         */
        public static final String WECHATPAY="http://pay.zgxc365.com/wx_pay/wxpayApp.php";
        /**
         * 已经反馈
         */
        public static final String FEEDBACK=B+"home/Suggestion/add";

        public static final String COMMENTS=B+"service/store/storeComments";
        /**
         * 用户评价 评论
         */
        public static final String ADDCOMMENT=B+"service/store/addComment";


        /**
         * 评论多图上传
         */
        public static final String POSTIMAGES=B+"file/images/android_upload";





    }

    public static final String SYSTEM_INIT_FILE_NAME = "/ /";
    /**
     * 图片缓存目录
     */
    public static final String CACHE_DIR_IMAGE;
    /**
     * 本地缓存目录
     */
    public static final String CACHE_DIR;
    /**
     * 表情缓存目录
     */
    public static final String CACHE_DIR_EMOJI;

    /**
     * 待上传图片缓存目录
     */
    public static final String CACHE_DIR_UPLOADING_IMG;
    /**
     * 数据库缓存目录
     */
    public static final String CACHE_DIR_DATABASE;

    static {
        // TODO: 2016/7/29  换项目名
        if (Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())) {
            CACHE_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() + SYSTEM_INIT_FILE_NAME;//有sdcard
        } else {
            CACHE_DIR = Environment.getRootDirectory().getAbsolutePath() + SYSTEM_INIT_FILE_NAME;//没有
        }
        CACHE_DIR_EMOJI = CACHE_DIR + "emoji/";
        CACHE_DIR_IMAGE = CACHE_DIR + "pic/";
        CACHE_DIR_UPLOADING_IMG = CACHE_DIR + "uploading_img/";
        CACHE_DIR_DATABASE = CACHE_DIR + "databases/";
    }
   /* public static final int RESULT_OK=0x0101;
    public static final int RESULT_OK_ONE=0x0102;
    public static final int RESULT_OK_TWO=0x0103;
    public static final int RESULT_REQUEST_ONE=0x0201;
    public static final int RESULT_REQUEST_SECOND=0x0202;
    public static final int RESULT_REQUEST_THIRD=0x0203;
    public static final String JUMP_KEY="jump_key";*/
    /**
     * 支付2预约
     */
    public static final int PAY_TO_RESERVATION=0x0300;
    /**
     * 团超详情2预约
     */
    public static final int GCOURSE_TO_BESPEAKLIST=0x0301;
    /**
     * 团超详情2团操
     */
    public static final int GCOURSE_TO_GCOURSE=0x0301;
    /**
     * 私教详情2订单
     */
    public static final int PCOURSE_TO_PCORDER=0x0302;
    /**
     * 吃的详情2订单
     */
    public static final int CATERINGDETAIL_TO_PCORDER=0x0303;

    /**
     * 退群->聊天页面
     */
    public static final int GROUPCANCLE_TO_TALK = 0X0304;

    /**
     * 进门广播
     */
    public static final String ENTER_DOOR_RECEIVER="enter_door_receiver";





    public static final String PAY_TYPE="payType";
    public static final String PAY_RESULT="payResult";

    /**
     * 经度
     */
    public static final String LONGITUDE = "longitude";
    /**
     * 纬度
     */
    public static final String LATITUDE = "latitude";

    /**
     * ？？？？？？
     */
    public static final String CITY = "city";
    /**
     * 城市名
     */
    public static final String CITY_NAME = "city_name";

    /**
     * 用户id
     */
    public static final String MEMID = "memid";
    public static final String USER_ID = "userId";
    public static final String HAS_GUIDE = "hasguide";

    /**
     * 用户昵称
     */
    public static final String NICK_NAME = "nickname";

    /**
     * 用户头像
     */
    public static final String AVATAR = "avatar";

    /**
     * 签到状态
     * 0未签到；1已签到
     */
    public static final String SIGNSTATUS = "signstatus";


    /**
     * ???
     */
    public static final String MY_SIGN = "mysign";
    /**
     * 收货人姓名
     */
    public static final String REC_NAME = "recname";

    /**
     * 收货人电话
     */
    public static final String REC_PHONE = "recphone";
    /**
     * 收货人地址
     */
    public static final String REC_ADDRESS = "recaddress";
    /**
     * 用户生日
     */
    public static final String BRITHDAY = "brithday";

    /**
     * 用户身份
     */
    public static final String MEMROLE = "memrole";
    /**
     * 用户性别
     */
    public static final String GENDER = "gender";

    /**
     * 会籍信息
     */
    public static final String MEMBERSHIP_INFORMATION = "membership_information";
    /**
     * 会籍信息
     * 会员办卡的俱乐部的id
     */
    public static final String MEMBERSHIP_CLUB_ID = "membership_club_id";

    /**
     * 会籍截至时间
     */
    public static final String ORDENDDATE = "ordenddate";

    /**
     * 我的页面刷新标志
     */
    public static final String FALG = "falg";

    /**
     * 门禁判断
     */
    public static final String foorBOOLEAN = "foorBoolean";
    /**
     * 上一次打卡的时间记录
     */
    public static final String foorTime = "foorTime";

    /**
     * 俱乐部的纬度
     */
    public static final String clubLATITUDE = "clubLatitude";

    /**
     * 俱乐部的经度
     */
    public static final String clubLONGITUDE = "clubLongitude";

    /**
     * 门禁的俱乐部id
     */
    public static final String clubID = "clubID";
    /**
     * 群列表
     */
    public static final String GROUP_ID = "groupID";

    /**
     * 是否接收通知
     */
    public static final String NOTIFY = "notify";

    /**
     * 未读消息是否全是群聊
     */
    public static final String GROUP_CHAT ="group_chat";

    /**
     * 未读消息是否全是单聊
     */
    public static final String CHAT = "chat";

    public static final String VERSION_NAME = "version_name";

}
