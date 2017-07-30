package com.miracle.qmt.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.miracle.qmt.R;
import com.miracle.qmt.base.BaseFragment;
import com.miracle.qmt.ui.activity.UpdateAddrActivity;
import com.miracle.qmt.ui.activity.UpdateBuyActivity;
import com.miracle.qmt.ui.activity.UpdateCompanyActivity;
import com.miracle.qmt.ui.activity.UpdateManageActivity;
import com.miracle.qmt.ui.activity.UpdateNameActivity;
import com.miracle.qmt.ui.activity.UpdateTelActivity;
import com.miracle.qmt.ui.contract.UpLoadContract;
import com.miracle.qmt.ui.presenter.UpLoadPresenter;
import com.miracle.qmt.util.CommonFunction;
import com.miracle.qmt.util.PreferencesUtils;
import com.miracle.qmt.util.UserManager;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

}
