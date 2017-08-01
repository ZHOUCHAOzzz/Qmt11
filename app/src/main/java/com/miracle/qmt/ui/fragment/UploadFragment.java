package com.miracle.qmt.ui.fragment;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.miracle.qmt.R;
import com.miracle.qmt.base.BaseFragment;
import com.miracle.qmt.ui.activity.UpdateAddrActivity;
import com.miracle.qmt.ui.activity.UpdateBuyActivity;
import com.miracle.qmt.ui.activity.UpdateCompanyActivity;
import com.miracle.qmt.ui.activity.UpdateManageActivity;
import com.miracle.qmt.ui.activity.UpdateNameActivity;
import com.miracle.qmt.ui.activity.UpdateTelActivity;
import com.miracle.qmt.ui.activity.UploadPicturesActivity;
import com.miracle.qmt.ui.contract.UpLoadContract;
import com.miracle.qmt.ui.presenter.UpLoadPresenter;
import com.miracle.qmt.util.CommonFunction;
import com.miracle.qmt.util.ConstantKey;
import com.miracle.qmt.util.ConstantValue;
import com.miracle.qmt.util.PreferencesUtils;
import com.miracle.qmt.util.UserManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * Created by Administrator on 2017/7/25.
 * 上传
 */
public class UploadFragment extends BaseFragment<UpLoadPresenter> implements UpLoadContract.View {

    public static UploadFragment mFragment;

    @Bind(R.id.iv_head)
    ImageView ivHead;
    @Bind(R.id.ll_sc)
    LinearLayout llSc;
    @Bind(R.id.tv_name)
    TextView tvName;
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

    PopupWindow mImgPopu;

    public static UploadFragment newInstance() {
        mFragment = new UploadFragment();
        return mFragment;
    }

    @Override
    public void initView() {
        super.initView();
        mTvTitle.setText("上传");
        fillData();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_uoload;
    }

    @Override
    public void onNetErrorClick() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        fillData();
    }

    @Override
    public void onUpLoadSucc(String imgs) {
        PreferencesUtils.setPreferences(mContext,PreferencesUtils.USER_HEAD_PIC,ConstantValue.BASE_URL+imgs);
        CommonFunction.showImage(mContext, ConstantValue.BASE_URL+imgs,ivHead,R.drawable.icon_headpic);
    }

    @Override
    public void getInfoSucc(UserManager.User user) {
        UserManager.saveUserInfo(mContext,user);
        CommonFunction.showImage(mContext,user.getHead_pic(),ivHead,R.drawable.icon_headpic);//头像
        tvName.setText(user.getName());//姓名
        tvTel.setText(user.getPhone());//电话号码
        tvCaddr.setText(user.getCompany_name());//公司名称
        tvAddr.setText(user.getAddress());//地址
    }

    private void fillData(){
        String name = PreferencesUtils.getPreferences(mContext,PreferencesUtils.USER_NAME);
        String tel = PreferencesUtils.getPreferences(mContext,PreferencesUtils.USER_TEL);
        String company = PreferencesUtils.getPreferences(mContext,PreferencesUtils.USER_COMPANY_NAME);
        String addr = PreferencesUtils.getPreferences(mContext,PreferencesUtils.USER_ADDRESS);
        String head_pic = PreferencesUtils.getPreferences(mContext,PreferencesUtils.USER_HEAD_PIC);

        if(!TextUtils.isEmpty(head_pic))
            CommonFunction.showImage(mContext,head_pic,ivHead,R.drawable.icon_headpic);//头像
        tvName.setText(name);//姓名
        tvTel.setText(tel);//电话号码
        tvCaddr.setText(company);//公司名称
        tvAddr.setText(addr);//地址
    }

    @OnClick(R.id.iv_head)
    public void updateHeadImg() {
        LayoutInflater mLayoutInflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);

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
        });
    }

    /**
     * 选择头像
     */
    public void setUpPhoto() {
        GalleryFinal.openGallerySingle(10000, new GalleryFinal.OnHanlderResultCallback() {
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
        });
    }

    /**
     * 打开相机
     */
    public void openCarmer() {
        GalleryFinal.openCamera(10001, new GalleryFinal.OnHanlderResultCallback() {
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
        });
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

    //修改姓名
    @OnClick(R.id.iv_into_name)
    public void mNameClick(){
        showActivityForResult(mContext,new Intent(mContext, UpdateNameActivity.class),100);
    }
    //修改电话号码
    @OnClick(R.id.iv_into_tel)
    public void mTelClick(){
        showActivityForResult(mContext,new Intent(mContext, UpdateTelActivity.class),100);
    }
    //修改公司名称
    @OnClick(R.id.iv_into_caddr)
    public void mCompanyClick(){
        showActivityForResult(mContext,new Intent(mContext, UpdateCompanyActivity.class),100);
    }
    //修改地址
    @OnClick(R.id.iv_into_addr)
    public void mAddrClick(){
        showActivityForResult(mContext,new Intent(mContext, UpdateAddrActivity.class),100);
    }
    //修改经营内容
    @OnClick(R.id.iv_into_jynr)
    public void mManageClick(){
        showActivityForResult(mContext,new Intent(mContext, UpdateManageActivity.class),100);
    }
    //修改求购
    @OnClick(R.id.iv_into_qg)
    public void mBuyClick(){
        showActivityForResult(mContext,new Intent(mContext, UpdateBuyActivity.class),100);
    }

    //修改求购
    @OnClick(R.id.iv_into_upload)
    public void mUploadPic(){
        showActivityForResult(mContext,new Intent(mContext, UploadPicturesActivity.class),100);
    }

}
