package com.miracle.qmt.ui.activity;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.miracle.qmt.R;
import com.miracle.qmt.base.BaseActivity;
import com.miracle.qmt.ui.contract.RegisterContract;
import com.miracle.qmt.ui.presenter.RegisterPresenter;
import com.miracle.qmt.util.T;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 注册
 */
public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterContract.View, TextWatcher {

    @Bind(R.id.tv_login)
    TextView tvLogin;
    @Bind(R.id.et_tel)
    EditText etTel;
    @Bind(R.id.tv_getcode)
    TextView tvGetcode;
    @Bind(R.id.et_pwd)
    EditText etPwd;
    @Bind(R.id.et_code)
    EditText etCode;
    @Bind(R.id.tv_reigister)
    TextView tvReigister;
    @Bind(R.id.iv_delete)
    ImageView mIvDelete;
    @Bind(R.id.iv_visiable)
    ImageView mIvVisiable;

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void onNetErrorClick() {

    }

    @Override
    public void initView() {
        super.initView();
        mIvDelete.setOnClickListener(this);
        mIvVisiable.setOnClickListener(this);
        etTel.addTextChangedListener(this);
        tvGetcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCodeClick();
            }
        });
    }


    public void getCodeClick() {
        String tel = etTel.getText().toString().trim();
        //手机号不能为空
        if (TextUtils.isEmpty(tel)) {
            T.showShort(mContext, "手机号不能为空");
            return;
        }
        showProgressDialog("处理中");
        mPresenter.getCode(tel);
    }

    //注册
    @OnClick(R.id.tv_reigister)
    public void mRegisterClick() {
        String tel = etTel.getText().toString().trim();
        String code = etCode.getText().toString().trim();
        String pwd = etPwd.getText().toString().trim();

        //手机号不能为空
        if (TextUtils.isEmpty(tel)) {
            T.showShort(mContext, "手机号不能为空");
            return;
        }
        //验证码不能为空
        if (TextUtils.isEmpty(code)) {
            T.showShort(mContext, "验证码不能为空");
            return;
        }
        //密码不能为空
        if (TextUtils.isEmpty(pwd)) {
            T.showShort(mContext, "密码不能为空");
            return;
        }
        RegisterContract.RegisterPostModel model = new RegisterContract.RegisterPostModel();
        model.tel = tel;
        model.code = code;
        model.pwd = pwd;
//        showProgressDialog("处理中");
        mPresenter.register(model);
    }

    @Override
    public void registerSucc() {
        T.showShort(mContext, "注册成功");
        finish();
    }

    @Override
    public void getCodeSucc() {
        T.showShort(mContext, "获取验证码成功");
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
