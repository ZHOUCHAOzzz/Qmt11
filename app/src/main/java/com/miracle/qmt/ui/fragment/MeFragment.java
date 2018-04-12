package com.miracle.qmt.ui.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.miracle.qmt.AppController;
import com.miracle.qmt.R;
import com.miracle.qmt.base.BaseFragment;
import com.miracle.qmt.ui.activity.AboutActivity;
import com.miracle.qmt.ui.activity.CommNewsListActivity;
import com.miracle.qmt.ui.activity.LoginActivity;
import com.miracle.qmt.ui.activity.MessageActivity;
import com.miracle.qmt.ui.activity.MyCollectionListActivity;
import com.miracle.qmt.ui.activity.ReportActivity;
import com.miracle.qmt.ui.activity.TxlCollectionActivity;
import com.miracle.qmt.ui.contract.MeContract;
import com.miracle.qmt.ui.presenter.MePresenter;
import com.miracle.qmt.util.CommonFunction;
import com.miracle.qmt.util.ConstantKey;
import com.miracle.qmt.util.ConstantValue;
import com.miracle.qmt.util.PreferencesUtils;
import com.miracle.qmt.util.UserManager;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.update.UpdateManagerListener;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2017/7/25.
 * 我的
 */
public class MeFragment extends BaseFragment<MePresenter> implements MeContract.View {
    @Bind(R.id.iv_pserson)
    CircleImageView ivHead;

    PopupWindow mImgPopu;//修改头像Popup

    public static MeFragment mFragment;
    @Bind(R.id.tv_nikename)
    TextView tvName;
    @Bind(R.id.ll_sc)
    LinearLayout llSc;
    @Bind(R.id.ll_xxjl)
    LinearLayout llXxjl;
    @Bind(R.id.ll_zj)
    LinearLayout llZj;
    @Bind(R.id.ll_lyjb)
    LinearLayout llLyjb;
    @Bind(R.id.ll_update)
    LinearLayout llUpdate;
    @Bind(R.id.ll_aboutus)
    LinearLayout llAboutus;
    @Bind(R.id.tv_logout)
    TextView tvLogout;
    @Bind(R.id.ll_txlsc)
    LinearLayout llTxlsc;



    public static MeFragment newInstance() {
        mFragment = new MeFragment();
        return mFragment;
    }

    @Override
    public void initView() {
        super.initView();
        mTvTitle.setText("我的");
        tvName.setText(AppController.getInstance().getUser().getNick_name());
        String head_pic = AppController.getInstance().getUser().getHead_pic();
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.icon_headpic)
                .error(R.drawable.icon_headpic);
        Glide.with(this)
                .load(head_pic)
                .thumbnail(0.4f)
                .apply(options)
                .into(ivHead);
    }

    @Override
    public void initData() {
        super.initData();
//        PgyUpdateManager.setIsForced(true); //设置是否强制更新。true为强制更新；false为不强制更新（默认值）。
//        PgyUpdateManager.register(getActivity(), getString(R.string.provider_file));
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    public void onNetErrorClick() {

    }

    //收藏
    @OnClick(R.id.ll_sc)
    public void mScClick() {
        Intent intent = new Intent(mContext, MyCollectionListActivity.class);
        intent.putExtra(ConstantKey.STRING_ITEM, AppController.getInstance().getUser().getUser_id()+"");
        intent.putExtra(ConstantKey.STRING_ITEM2, "我的收藏");
        showActivity(intent);
    }

    //消息记录
    @OnClick(R.id.ll_xxjl)
    public void mXXClick() {
        Intent intent = new Intent(mContext, MessageActivity.class);
        showActivity(intent);
    }
    @OnClick(R.id.ll_txlsc)
    public void mTXLClick() {
        Intent intent = new Intent(mContext, TxlCollectionActivity.class);
        showActivity(intent);
    }
    @OnClick(R.id.tv_logout)
    public void logoutClick(){
        final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getActivity());

        builder.setMessage("确定要退出当前账号吗");
        // builder.setMessage("这是 android.support.v7.app.AlertDialog 中的样式");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                AppController.getInstance().clearUser();
                Intent intent=new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);


            }
        });
        builder.show();


    }

    //足迹
    @OnClick(R.id.ll_zj)
    public void mZJClick() {
        Intent intent = new Intent(mContext, CommNewsListActivity.class);
        intent.putExtra(ConstantKey.STRING_ITEM, "zj");
        intent.putExtra(ConstantKey.STRING_ITEM2, "足迹");
        showActivity(intent);
    }

    //举报
    @OnClick(R.id.ll_lyjb)
    public void mJBClick() {
        Intent intent = new Intent(mContext, ReportActivity.class);
        showActivity(intent);
    }

    //关于我们
    @OnClick(R.id.ll_aboutus)
    public void aboutUsClick() {
        Intent infoItent = new Intent(mContext, AboutActivity.class);
        showActivity(infoItent);
    }

    //检查更新
    @OnClick(R.id.ll_update)
    public void updateClick(){
        checkUpdate();
    }

    @OnClick(R.id.iv_pserson)
    public void updateHeadImg() {
        /*LayoutInflater mLayoutInflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);

        View conterview = mLayoutInflater.inflate(R.layout.view_change_img, null);
        TextView mTvCamera = (TextView) conterview.findViewById(R.id.tv_camer);
        TextView mTvGallary = (TextView) conterview.findViewById(R.id.tv_gallary);
        TextView mTvSave = (TextView) conterview.findViewById(R.id.tv_saveimg);
        TextView mTvCancel = (TextView) conterview.findViewById(R.id.tv_cancel);
        mTvCamera.setOnClickListener(this);
        mTvGallary.setOnClickListener(this);
        mTvSave.setOnClickListener(this);
        mTvCancel.setOnClickListener(this);
        mImgPopu = new PopupWindow(conterview, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        mImgPopu.setFocusable(true);
        mImgPopu.setOutsideTouchable(true);
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        mImgPopu.setBackgroundDrawable(dw);
        mImgPopu.setAnimationStyle(R.style.social_pop_anim);
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.7f;
        getActivity().getWindow().setAttributes(lp);
        mImgPopu.showAtLocation(mTvTitle, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        mImgPopu.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                lp.alpha = 1f;
                getActivity().getWindow().setAttributes(lp);
            }
        });*/
    }

    /**
     * 选择头像
     */
    public void setUpPhoto() {
       /* GalleryFinal.openGallerySingle(10000, new GalleryFinal.OnHanlderResultCallback() {
            @Override
            public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                if (resultList != null && resultList.size() > 0) {
                    String mFileUrl = resultList.get(0).getPhotoPath();
                    ArrayList<String> list = new ArrayList<String>();
                    list.add(mFileUrl);
                    mPresenter.uploadImg(list);
                }
            }

            @Override
            public void onHanlderFailure(int requestCode, String errorMsg) {

            }
        });*/
    }
    @Override
    public void onUpLoadSucc(String imgs) {
        if(!TextUtils.isEmpty(imgs) && imgs.contains("uploads")){
            imgs = imgs.replace("uploads","");
        }
        PreferencesUtils.setPreferences(mContext,PreferencesUtils.USER_HEAD_PIC,ConstantValue.BASE_URL+"/uploads"+imgs);
        CommonFunction.showImage(mContext, ConstantValue.BASE_URL+"/uploads"+imgs,ivHead,R.drawable.icon_headpic);
    }
    /**
     * 打开相机
     */
    public void openCarmer() {
      /*  GalleryFinal.openCamera(10001, new GalleryFinal.OnHanlderResultCallback() {
            @Override
            public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                if (resultList != null && resultList.size() > 0) {
                    String mFileUrl = resultList.get(0).getPhotoPath();
                    ArrayList<String> list = new ArrayList<String>();
                    list.add(mFileUrl);
                    mPresenter.uploadImg(list);
                }
            }

            @Override
            public void onHanlderFailure(int requestCode, String errorMsg) {

            }
        });*/
    }


    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {

            case R.id.tv_camer:
                openCarmer();
                mImgPopu.dismiss();
                break;
            case R.id.tv_gallary:
                setUpPhoto();
                mImgPopu.dismiss();
                break;
            case R.id.tv_cancel:
                mImgPopu.dismiss();
                break;
        }
    }

    @Override
    public void getInfoSucc(UserManager.User user) {
        UserManager.saveUserInfo(mContext, user);

    }

    private void checkUpdate() {
        PgyUpdateManager.register(getActivity(),
                getString(R.string.provider_file),
                new UpdateManagerListener() {
                    @Override
                    public void onUpdateAvailable(final String result) {

                        final SweetAlertDialog dialog = new SweetAlertDialog(mContext,SweetAlertDialog.SUCCESS_TYPE);
                        dialog.setTitleText("检测到新版本");
                        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialog) {
                                dialog.dismiss();
                            }
                        });
                        dialog.setConfirmText("更新");
                        dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                dialog.dismiss();
                                String url;
                                JSONObject jsonData;
                                try {
                                    jsonData = new JSONObject(
                                            result);
                                    if ("0".equals(jsonData
                                            .getString("code"))) {
                                        JSONObject jsonObject = jsonData
                                                .getJSONObject("data");
                                        url = jsonObject
                                                .getString("downloadURL");

                                        startDownloadTask(
                                                getActivity(),
                                                url);


                                    }

                                } catch (JSONException e) {
                                    // TODO Auto-generated
                                    // catch block
                                    e.printStackTrace();
                                }
                            }
                        });
                        dialog.show();

                    }

                    @Override
                    public void onNoUpdateAvailable() {
                        showErrorDialog("没有新版本");
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        PgyUpdateManager.unregister();
    }
}
