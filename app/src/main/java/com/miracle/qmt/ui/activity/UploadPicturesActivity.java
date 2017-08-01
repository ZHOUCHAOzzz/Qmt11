package com.miracle.qmt.ui.activity;


import android.Manifest;
import android.content.Intent;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import com.miracle.qmt.R;
import com.miracle.qmt.base.BaseActivity;
import com.miracle.qmt.ui.contract.UploadPicturesContract;
import com.miracle.qmt.ui.presenter.UploadPicturesPresenter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerActivity;
import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerPreviewActivity;
import cn.bingoogolapple.photopicker.widget.BGASortableNinePhotoLayout;
import cn.finalteam.galleryfinal.permission.AfterPermissionGranted;
import cn.finalteam.galleryfinal.permission.EasyPermissions;

public class UploadPicturesActivity extends BaseActivity<UploadPicturesPresenter> implements UploadPicturesContract.View, BGASortableNinePhotoLayout.Delegate, EasyPermissions.PermissionCallbacks {

    private static final int REQUEST_CODE_PERMISSION_PHOTO_PICKER = 1;
    private static final int REQUEST_CODE_CHOOSE_PHOTO = 1;
    private static final int REQUEST_CODE_PHOTO_PREVIEW = 2;

    @Bind(R.id.snpl_moment_add_photos)
    BGASortableNinePhotoLayout mPhotosSnpl;

    @Override
    public int getLayoutId() {
        return R.layout.activity_upload_pictures;
    }

    @Override
    public void onNetErrorClick() {

    }

    @Override
    public void initView() {
        super.initView();
        mTvTitle.setText("上传图片");
        mPhotosSnpl.init(this);
        mPhotosSnpl.setDelegate(this);
    }


    //选择图片
    @Override
    public void onClickAddNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position,
                                        ArrayList<String> models) {
        choicePhotoWrapper();
    }

    @Override
    public void onClickDeleteNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int
            position, String model, ArrayList<String> models) {
        mPhotosSnpl.removeItem(position);
    }

    @Override
    public void onClickNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position,
                                     String model, ArrayList<String> models) {
        startActivityForResult(BGAPhotoPickerPreviewActivity.newIntent(mContext, mPhotosSnpl.getMaxItemCount(),
                models,
                models, position, false), REQUEST_CODE_PHOTO_PREVIEW);
    }

    @AfterPermissionGranted(REQUEST_CODE_PERMISSION_PHOTO_PICKER)
    private void choicePhotoWrapper() {
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(mContext, perms)) {
            // 拍照后照片的存放目录，改成你自己拍照后要存放照片的目录。如果不传递该参数的话就没有拍照功能
            File takePhotoDir = new File(Environment.getExternalStorageDirectory(), "BGAPhotoPickerTakePhoto");

            startActivityForResult(BGAPhotoPickerActivity.newIntent(mContext, takePhotoDir, mPhotosSnpl.getMaxItemCount()
                    , mPhotosSnpl.getData(), false), REQUEST_CODE_CHOOSE_PHOTO);
        } else {
            EasyPermissions.requestPermissions(this, "图片选择需要以下权限:\n\n1.访问设备上的照片",
                    REQUEST_CODE_PERMISSION_PHOTO_PICKER, perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[]
            grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_CHOOSE_PHOTO) {
            mPhotosSnpl.setData(BGAPhotoPickerActivity.getSelectedImages(data));
        } else if (requestCode == REQUEST_CODE_PHOTO_PREVIEW) {
            mPhotosSnpl.setData(BGAPhotoPickerPreviewActivity.getSelectedImages(data));
        }
    }

    @Override
    public void onPermissionsGranted(List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(List<String> perms) {
//        if (perms.get(0).g == REQUEST_CODE_PERMISSION_PHOTO_PICKER) {
//            Toast.makeText(mContext, "您拒绝了「图片选择」所需要的相关权限!", Toast.LENGTH_SHORT).show();
//        }
    }
}
