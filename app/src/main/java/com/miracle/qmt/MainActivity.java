package com.miracle.qmt;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.miracle.qmt.base.BaseActivity;
import com.miracle.qmt.ui.fragment.ContractFragment;
import com.miracle.qmt.ui.fragment.HomeFragment;
import com.miracle.qmt.ui.fragment.MeFragment;
import com.miracle.qmt.ui.fragment.UploadFragment;

import org.jetbrains.annotations.Contract;

import butterknife.Bind;
import me.majiajie.pagerbottomtabstrip.Controller;
import me.majiajie.pagerbottomtabstrip.PagerBottomTabLayout;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectListener;

/**
 * 主界面
 */
public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    private Controller controler;

    HomeFragment mHomeFragment;
    ContractFragment mContractFragment;
    UploadFragment mUploadFragment;
    MeFragment mMeFragment;

    @Bind(R.id.rg)
    RadioGroup mRadioGroup;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onNetErrorClick() {

    }

    @Override
    public void initView() {
        super.initView();
        mRadioGroup.setOnCheckedChangeListener(this);
        if (mHomeFragment == null){
            mHomeFragment = HomeFragment.newInstance();
        }
        showOrHideFragment(R.id.fl, mHomeFragment);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.tab_home:
                if (mHomeFragment == null){
                    mHomeFragment = HomeFragment.newInstance();

                }
                showOrHideFragment(R.id.fl, mHomeFragment);
                break;
            case R.id.tab_contract:
                if (mContractFragment == null)
                    mContractFragment = ContractFragment.newInstance();
                showOrHideFragment(R.id.fl, mContractFragment);
                break;
            case R.id.tab_upload:
                if (mUploadFragment == null)
                    mUploadFragment = UploadFragment.newInstance();
                showOrHideFragment(R.id.fl, mUploadFragment);

                break;
            case R.id.tab_me:
                if (mMeFragment == null)
                    mMeFragment = MeFragment.newInstance();
                showOrHideFragment(R.id.fl, mMeFragment);
                break;
        }
    }
}
