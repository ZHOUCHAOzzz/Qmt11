package com.miracle.qmt.ui.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.JsonObject;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.compress.Luban;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.miracle.qmt.AppController;
import com.miracle.qmt.R;
import com.miracle.qmt.base.BaseFragment;
import com.miracle.qmt.ui.activity.OrderActivity;
import com.miracle.qmt.ui.activity.UpdateAddrActivity;
import com.miracle.qmt.ui.activity.UpdateBuyActivity;
import com.miracle.qmt.ui.activity.UpdateCompanyActivity;
import com.miracle.qmt.ui.activity.UpdateManageActivity;
import com.miracle.qmt.ui.activity.UpdateNameActivity;
import com.miracle.qmt.ui.activity.UpdateTelActivity;
import com.miracle.qmt.ui.activity.UploadImgsActivity;
import com.miracle.qmt.ui.contract.UpLoadContract;
import com.miracle.qmt.ui.model.UpUserBean;
import com.miracle.qmt.ui.model.UserBean;
import com.miracle.qmt.ui.presenter.UpLoadPresenter;
import com.miracle.qmt.ui.view.CustomDialog;
import com.miracle.qmt.util.ConstantValue;
import com.miracle.qmt.util.PreferencesUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import me.leefeng.promptlibrary.PromptDialog;

/**
 * Created by Administrator on 2017/7/25.
 * 上传
 */
public class UploadFragment extends BaseFragment<UpLoadPresenter> implements UpLoadContract.View {

    public static UploadFragment mFragment;

    @Bind(R.id.iv_head)
    CircleImageView ivHead;
    @Bind(R.id.ll_sc)
    LinearLayout llSc;
    @Bind(R.id.tv_nike_name)
    TextView tv_nike;
    @Bind(R.id.iv_into_name)
    ImageView ivIntoName;
    @Bind(R.id.tv_tel)
    TextView tvTel;
    @Bind(R.id.iv_into_tel)
    ImageView ivIntoTel;
    @Bind(R.id.tv_caddr)
    TextView tvCaddr;
    @Bind(R.id.iv_into_caddr)
    ImageView ivIntoCaddr;
    @Bind(R.id.tv_addr)
    TextView tvAddr;
    @Bind(R.id.iv_into_addr)
    ImageView ivIntoAddr;
    @Bind(R.id.iv_into_jynr)
    ImageView ivIntoJynr;
    @Bind(R.id.iv_into_qg)
    ImageView ivIntoQg;
    @Bind(R.id.iv_into_upload)
    ImageView ivIntoUpload;
    @Bind(R.id.tv_submit)
    TextView tvSubmit;

    PopupWindow mImgPopu;
    private PromptDialog promptDialog;
    private LogBroadcastReceiver logBroadcastReceiver;


    public static UploadFragment newInstance() {
        mFragment = new UploadFragment();
        return mFragment;
    }
    List<LocalMedia> selectListPic = new ArrayList<>();

    @Override
    public void initView() {
        super.initView();
        mTvTitle.setText("上传");
        //fillData();
        //创建对象
        promptDialog = new PromptDialog(getActivity());
        //设置自定义属性
        promptDialog.getDefaultBuilder().touchAble(true).round(3).loadingDuration(3000);
        UserBean userBean=AppController.getInstance().getUser();
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.icon_headpic)
                .error(R.drawable.icon_headpic);
        Glide.with(this)
                .load(userBean.getHead_pic())
                .thumbnail(0.4f)
                .apply(options)
                .into(ivHead);
        tv_nike.setText(userBean.getNick_name());//姓名
        tvTel.setText(userBean.getPhone());//电话号码
        tvCaddr.setText(userBean.getCompany_name());//公司名称
        tvAddr.setText(userBean.getAddress());//地址
        Log.d("picpic",userBean.getHead_pic());

        RegisterBroadcastReceiver();

       // CommonFunction.showImage(getActivity(), userBean.getHead_pic(), ivHead, R.drawable.icon_headpic);

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_uoload;
    }

    @Override
    public void onNetErrorClick() {

    }


    /*
    * 上传图片成功的回调
    * */
    @Override
    public void onUpLoadSucc(String imgs) {
        Log.d("imgspic",ConstantValue.BASE_URL  + imgs);
        PreferencesUtils.setPreferences(mContext, PreferencesUtils.USER_HEAD_PIC, ConstantValue.BASE_URL  + imgs);
    }


    /*
    * 判断是否是分销合伙人
    * */
    @Override
    public void checkVipSucc(int result) {
        Log.d("vip", result + "");
        switch (result) {
            case 0:
              //  是分销合伙人 直接上传
                mPresenter.uploadAll(getActivity());
                break;
            case 1:
                // 不是分销合伙人 跳到支付页面
               showActivityForResult(mContext, new Intent(mContext, OrderActivity.class), 108);

                break;
        }


    }
    /*
    *
    * 个人资料上传成功后的回调
    * */
    @Override
    public void updateSucc(UpUserBean user) {
        Toast.makeText(getActivity(), "上传成功", Toast.LENGTH_SHORT).show();

        AppController.getInstance().putUser(user.getData(),user.getData().getUser_id()+"");
        UserBean userb=AppController.getInstance().getUser();
        tv_nike.setText(userb.getNick_name());
        tvAddr.setText(userb.getAddress());
        tvTel.setText(userb.getPhone());
        tvCaddr.setText(userb.getCompany_name());

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.icon_headpic)
                .error(R.drawable.icon_headpic);
        Glide.with(this)
                .load( user.getData().getHead_pic())
                .thumbnail(0.4f)
                .apply(options)
                .into(ivHead);

      //  CommonFunction.showImage(mContext, ConstantValue.BASE_URL  + user.getData().getHead_pic(), ivHead, R.drawable.icon_headpic);
    }



    @OnClick(R.id.iv_head)
    public void updateHeadImg() {
        selectPic() ;

    }

    /**
     * 相册
     */
    private void selectPic() {
        PictureSelector.create(getActivity())
                .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
                //  .theme()//主题样式(不设置为默认样式) 也可参考demo values/styles下 例如：R.style.picture.white.style
                .maxSelectNum(20)// 最大图片选择数量 int
                .minSelectNum(0)// 最小选择数量 int
                .imageSpanCount(4)// 每行显示个数 int
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .previewImage(false)// 是否可预览图片 true or false
                // .previewVideo(true)// 是否可预览视频 true or false
                .compressGrade(Luban.THIRD_GEAR)// luban压缩档次，默认3档 Luban.THIRD_GEAR、Luban.FIRST_GEAR、Luban.CUSTOM_GEAR
                .isCamera(true)// 是否显示拍照按钮 true or false
                //.enableCrop(true)// 是否裁剪 true or false
                .compress(true)// 是否压缩 true or false
                .compressMode(PictureConfig.LUBAN_COMPRESS_MODE)//系统自带 or 鲁班压缩 PictureConfig.SYSTEM_COMPRESS_MODE or LUBAN_COMPRESS_MODE
                // .glideOverride()// int glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .withAspectRatio(1, 1)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                // .hideBottomControls()// 是否显示uCrop工具栏，默认不显示 true or false
                //  .isGif()// 是否显示gif图片 true or false
                //.freeStyleCropEnabled()// 裁剪框是否可拖拽 true or false
                // .circleDimmedLayer()// 是否圆形裁剪 true or false
               /* .showCropFrame()// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
                .showCropGrid()// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                .openClickSound()// 是否开启点击声音 true or false
                .selectionMedia()// 是否传入已选图片 List<LocalMedia> list
                .previewEggs()// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
                .cropCompressQuality()// 裁剪压缩质量 默认90 int
                .compressMaxKB()//压缩最大值kb compressGrade()为Luban.CUSTOM_GEAR有效 int
                .compressWH() // 压缩宽高比 compressGrade()为Luban.CUSTOM_GEAR有效  int
                .cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效 int
                .rotateEnabled() // 裁剪是否可旋转图片 true or false
                .scaleEnabled()// 裁剪是否可放大缩小图片 true or false
                .videoQuality()// 视频录制质量 0 or 1 int*/
                //  .videoSecond(60)//显示多少秒以内的视频 int
                //  .recordVideoSecond()//视频秒数录制 默认60s int
                .forResult(166);//结果回调onActivityResult code
    }


    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {

        }
    }

    //修改姓名
    @OnClick(R.id.ll_name)
    public void mNameClick() {
        showActivityForResult(mContext, new Intent(mContext, UpdateNameActivity.class), 101);
    }

    //修改电话号码
    @OnClick(R.id.ll_tel)
    public void mTelClick() {
        showActivityForResult(mContext, new Intent(mContext, UpdateTelActivity.class), 102);
    }

    //修改公司名称
    @OnClick(R.id.ll_company)
    public void mCompanyClick() {
        showActivityForResult(mContext, new Intent(mContext, UpdateCompanyActivity.class), 103);
    }

    //修改地址
    @OnClick(R.id.ll_addr)
    public void mAddrClick() {
        showActivityForResult(mContext, new Intent(mContext, UpdateAddrActivity.class), 104);
    }

    //修改经营内容
    @OnClick(R.id.ll_jynr)
    public void mManageClick() {

        Intent intentm=new Intent(getActivity(),UpdateManageActivity.class);
        intentm.putExtra("Management",AppController.getInstance().getUser().getManagement());
        showActivityForResult(getActivity(),intentm,105);
        //showActivityForResult(mContext, new Intent(mContext, UpdateManageActivity.class), 100);
    }

    //修改求购
    @OnClick(R.id.ll_qg)
    public void mBuyClick() {
        Intent intentb=new Intent(getActivity(),UpdateBuyActivity.class);
        if (AppController.getInstance().getUser().getBuy()!=null){
            intentb.putExtra("buy",AppController.getInstance().getUser().getBuy());
            showActivityForResult(getActivity(),intentb,106);
        }else {
            showActivityForResult(getActivity(),intentb,106);
        }

        //showActivityForResult(mContext, new Intent(mContext, UpdateBuyActivity.class), 100);
    }

    //图片
    @OnClick(R.id.ll_sctp)
    public void mUploadPic() {

        showActivityForResult(mContext, new Intent(mContext, UploadImgsActivity.class), 100);
    }

    //提交
    @OnClick(R.id.tv_submit)
    public void mSubmit() {
        //自定义dialog
        final CustomDialog dialog = new CustomDialog(getActivity(), R.style.customDialog, R.layout.custom_dialog);
        dialog.show();
        TextView tvCancel = (TextView) dialog.findViewById(R.id.cancel);
        TextView tvOk = (TextView) dialog.findViewById(R.id.ok);
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                mPresenter.checkVip();

            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("11111111",requestCode+"");

        switch(requestCode){
            case 101:
                if (data!=null){
                    tv_nike.setText(data.getStringExtra("name"));
                }

                break;
            case 102:
                if (data!=null){
                    tvTel.setText(data.getStringExtra("tel"));
                }

                break;
            case 103:
                if (data!=null){
                    tvCaddr.setText(data.getStringExtra("company"));
                }

                break;
            case 104:
                if (data!=null){
                    tvAddr.setText(data.getStringExtra("address"));
                }

                break;
            case 166:
                selectListPic = PictureSelector.obtainMultipleResult(data);
                RequestOptions options = new RequestOptions()
                        .centerCrop()
                        .placeholder(R.drawable.icon_headpic)
                        .error(R.drawable.icon_headpic);
                if (selectListPic.size()>0){
                    Glide.with(this)
                            .load(selectListPic.get(0).getCompressPath())
                            .thumbnail(0.4f)
                            .apply(options)
                            .into(ivHead);
                    List<String> list=new ArrayList<>();
                    list.add(selectListPic.get(0).getCompressPath());
                    mPresenter.uploadImg(list);

                }




                break;
        }
        // fillData();
    }
    /**
     * 注册广播
     */
    private void RegisterBroadcastReceiver() {
        logBroadcastReceiver = new LogBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter("payresult");
        getActivity().registerReceiver(logBroadcastReceiver, intentFilter);
    }
    /**
     * 支付成功后接收到广播
     */
    class LogBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("payresult")) {


                Log.d("1111111111","payresult");
                mPresenter.join();


            }


        }
    }
    /*
    * 成为分销合伙人之后的回调
    * */
    @Override
    public void join(JsonObject jsonObject) {
         JSONObject obj = null;
                try {
                    obj = new JSONObject(jsonObject.toString());
                    String info = obj.getString("info");

                        Toast.makeText(getActivity(), info, Toast.LENGTH_SHORT).show();
                        mPresenter.uploadAll(getActivity());



                } catch (JSONException e) {
                    e.printStackTrace();
                }

    }


}


