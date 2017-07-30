package com.miracle.qmt.ui.fragment;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.miracle.qmt.R;
import com.miracle.qmt.base.BaseFragment;
import com.miracle.qmt.ui.contract.MeContract;
import com.miracle.qmt.ui.presenter.MePresenter;
import com.miracle.qmt.util.PreferencesUtils;
import com.miracle.qmt.util.UserManager;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2017/7/25.
 * 我的
 */
public class MeFragment extends BaseFragment<MePresenter> implements MeContract.View {
    @Bind(R.id.iv_pserson)
    CircleImageView mCircleImageView;

    PopupWindow mImgPopu;//修改头像Popup

    public static MeFragment mFragment;
    @Bind(R.id.tv_name)
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

    public static MeFragment newInstance() {
        mFragment = new MeFragment();
        return mFragment;
    }

    @Override
    public void initView() {
        super.initView();
        mTvTitle.setText("我的");
        tvName.setText(PreferencesUtils.getPreferences(mContext, PreferencesUtils.USER_NAME));
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    public void onNetErrorClick() {

    }

    @OnClick(R.id.iv_pserson)
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
//                    mFileUrl = resultList.get(0).getPhotoPath();
//                    mPresenter.setUserHead(mFileUrl);
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
//                    mFileUrl = resultList.get(0).getPhotoPath();
//                    mPresenter.setUserHead(mFileUrl);
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

    @Override
    public void getInfoSucc(UserManager.User user) {
        UserManager.saveUserInfo(mContext, user);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
