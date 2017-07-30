package com.miracle.qmt.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.miracle.qmt.MainActivity;
import com.miracle.qmt.R;
import com.miracle.qmt.base.BaseActivity;
import com.miracle.qmt.ui.contract.LoginContract;
import com.miracle.qmt.ui.contract.RegisterContract;
import com.miracle.qmt.ui.model.LoginSuccModel;
import com.miracle.qmt.ui.presenter.LoginPresenter;
import com.miracle.qmt.util.PreferencesUtils;
import com.miracle.qmt.util.T;
import com.miracle.qmt.util.UserManager;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/7/29.
 * 登录
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View, TextWatcher {
    @Bind(R.id.iv_delete)
    ImageView mIvDelete;
    @Bind(R.id.iv_visiable)
    ImageView mIvVisiable;
    @Bind(R.id.et_tel)
    EditText etTel;
    @Bind(R.id.et_pwd)
    EditText etPwd;

    private String mPwd;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onNetErrorClick() {

    }

    @Override
    public void initView() {
        super.initView();

        mIvDelete.setOnClickListener(this);
        mIvVisiable.setOnClickListener(this);
        etTel.addTextChangedListener(this);

        String tel = PreferencesUtils.getPreferences(mContext,PreferencesUtils.USER_TEL);
        String pwd = PreferencesUtils.getPreferences(mContext,PreferencesUtils.USER_PWD);

        if(!TextUtils.isEmpty(tel))
            etTel.setText(tel);
        if(!TextUtils.isEmpty(pwd))
            etPwd.setText(pwd);
    }

    //登录
    @OnClick(R.id.tv_login)
    public void mLoginClick() {
//
        String tel = etTel.getText().toString().trim();
        mPwd = etPwd.getText().toString().trim();

        //手机号不能为空
        if (TextUtils.isEmpty(tel)) {
            T.showShort(mContext, "手机号不能为空");
            return;
        }

        //密码不能为空
        if (TextUtils.isEmpty(mPwd)) {
            T.showShort(mContext, "密码不能为空");
            return;
        }
        showProgressDialog("处理中");
        mPresenter.login(tel, mPwd);
    }

    //注册
    @OnClick(R.id.tv_register)
    public void mRegisterClick() {
        showActivity(new Intent(mContext, RegisterActivity.class));
    }

    //忘记密码
    @OnClick(R.id.tv_forget)
    public void mForgetClick() {
        showActivity(new Intent(mContext, ForgePwdActivity.class));
    }

    @Override
    public void loginSucc(LoginSuccModel model) {
        UserManager.saveLoginSuccInfo(mContext,model.getUser_id(),mPwd,model.getPhone());
        showActivity(new Intent(mContext, MainActivity.class));
        finish();
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.iv_delete://清除
                etTel.setText("");

                break;
            case R.id.iv_visiable://可见
                if (etPwd.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                    etPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    etPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }

                break;
        }
    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!TextUtils.isEmpty(s)) {
            mIvDelete.setVisibility(View.VISIBLE);
        } else {
            mIvDelete.setVisibility(View.GONE);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
