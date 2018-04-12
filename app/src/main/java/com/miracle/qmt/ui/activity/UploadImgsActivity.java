package com.miracle.qmt.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.compress.Luban;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.miracle.qmt.R;
import com.miracle.qmt.base.BaseActivity;
import com.miracle.qmt.ui.model.adapter.GridImageAdapter;
import com.miracle.qmt.ui.presenter.UploadPicturesPresenter;
import com.miracle.qmt.util.ConstantValue;
import com.miracle.qmt.util.PreferencesUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;

/**
 * Created by ZhouChao on 2017/9/12.
 */

public class UploadImgsActivity extends BaseActivity<UploadPicturesPresenter> {
    @Bind(R.id.iv_title_left)
    ImageView ivTitleLeft;
    @Bind(R.id.title_txt)
    TextView titleTxt;
    @Bind(R.id.tv_title_right)
    TextView tvTitleRight;
    @Bind(R.id.rv_images)
    RecyclerView rvImages;
    @Bind(R.id.iv_selectimg)
    ImageView ivSelectimg;
    @Bind(R.id.progress)
    ProgressBar progressBar;

    List<LocalMedia> selectListPic = new ArrayList<>();
    private GridImageAdapter adapter;
    private int maxSelectNum = 20;
    private File[] allFile;
    private String imgsobj;
    private android.os.Handler handler=new android.os.Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_uploadimgs;
    }

    @Override
    public void initView() {
        super.initView();
        mTvTitle.setText("上传图片");
        tvTitleRight.setText("上传");
        tvTitleRight.setClickable(true);

        /* final ArrayList<String> imgs = new ArrayList<>();

        String photos = AppController.getInstance().getUser().getPic();
        String[] strs = photos.split(",");

            for (int i = 0, len = strs.length; i < len; i++) {
                imgs.add(ConstantValue.BASE_URL + strs[i].toString());

            }
        new Thread(new Runnable() {
            @Override
            public void run() {
                *//**
                 耗时操作
                 *//*
                for (int i = 0; i < imgs.size(); i++) {


                    Bitmap bitmap=returnBitmap(imgs.get(i));
                    String file= saveBitMapToFile(mContext,i+"",bitmap,true);
                    Log.d("111111111111",file);
                    LocalMedia localMedia=new LocalMedia();
                    localMedia.setCompressPath(file);
                    selectListPic.add(localMedia);


                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        *//**
                         更新UI
                         *//*
                        ivSelectimg.setVisibility(View.GONE);
                        rvImages.setVisibility(View.VISIBLE);
                        adapter = new GridImageAdapter(UploadImgsActivity.this, onAddPicClickListener);
                        GridLayoutManager manager=new GridLayoutManager(UploadImgsActivity.this,5);
                        // FullyGridLayoutManager manager = new FullyGridLayoutManager(ZxjsReleaseActivity.this, 9, GridLayoutManager.VERTICAL, false);
                        rvImages.setLayoutManager(manager);
                        rvImages.setAdapter(adapter);
                        adapter.setSelectMax(maxSelectNum);
                        adapter.setList(selectListPic);
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();*/
        /*new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();*/
       /* runOnUiThread(new Runnable() {
            @Override
            public void run() {


            }
        });*/

       /* for (int i = 0; i < selectListPic.size(); i++) {
            fileList.add(new File(selectListPic.get(i).getPath()));

        }
        
        allFile = new File[fileList.size()];
        for (int i = 0; i < fileList.size(); i++) {
            allFile[i] = fileList.get(i);
        }*/



        tvTitleRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UploadImgsActivity.this, "图片开始上传", Toast.LENGTH_SHORT).show();

                if (selectListPic.size()>0){
                    upLoadPic(selectListPic);

                }else {
                    Toast.makeText(UploadImgsActivity.this, "最少选择一张图片", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
    public static String saveBitMapToFile(Context context, String fileName, Bitmap bitmap, boolean isCover) {
        if(null == context || null == bitmap) {
            return null;
        }
        if(TextUtils.isEmpty(fileName)) {
            return null;
        }
        FileOutputStream fOut = null;
        try {
            File file = null;
            String fileDstPath = "";
            if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
                // 保存到sd卡
                fileDstPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                        + File.separator + "MyFile" + File.separator + fileName;

                File homeDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                        + File.separator + "MyFile" + File.separator);
                if (!homeDir.exists()) {
                    homeDir.mkdirs();
                }
            } else {
                // 保存到file目录
                fileDstPath = context.getFilesDir().getAbsolutePath()
                        + File.separator + "MyFile" + File.separator + fileName;

                File homeDir = new File(context.getFilesDir().getAbsolutePath()
                        + File.separator + "MyFile" + File.separator);
                if (!homeDir.exists()) {
                    homeDir.mkdir();
                }
            }

            file = new File(fileDstPath);

            if (!file.exists() || isCover) {
                // 简单起见，先删除老文件，不管它是否存在。
                file.delete();

                fOut = new FileOutputStream(file);
                if (fileName.endsWith(".jpg")) {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 75, fOut);
                } else {
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
                }
                fOut.flush();
                bitmap.recycle();
            }

            Log.i("FileSave", "saveDrawableToFile " + fileName
                    + " success, save path is " + fileDstPath);
            return fileDstPath;
        } catch (Exception e) {
            Log.e("FileSave", "saveDrawableToFile: " + fileName + " , error", e);
            return null;
        } finally {
            if(null != fOut) {
                try {
                    fOut.close();
                } catch (Exception e) {
                    Log.e("FileSave", "saveDrawableToFile, close error", e);
                }
            }
        }
    }


    /**
     * 根据图片的url路径获得Bitmap对象
     * @param url
     * @return
     */
    private Bitmap returnBitmap(String url) {
        URL fileUrl = null;
        Bitmap bitmap = null;

        try {
            fileUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            HttpURLConnection conn = (HttpURLConnection) fileUrl
                    .openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;

    }


    @OnClick(R.id.iv_selectimg)
    public void onViewClicked() {
        selectPic();

    }

    /**
     * 相册
     */
    private void selectPic() {
        PictureSelector.create(UploadImgsActivity.this)
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
                .selectionMedia(selectListPic)// 是否传入已选图片 List<LocalMedia> list
                // .hideBottomControls()// 是否显示uCrop工具栏，默认不显示 true or false
                //  .isGif()// 是否显示gif图片 true or false
                //.freeStyleCropEnabled()// 裁剪框是否可拖拽 true or false
                // .circleDimmedLayer()// 是否圆形裁剪 true or false
               /* .showCropFrame()// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
                .showCropGrid()// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                .openClickSound()// 是否开启点击声音 true or false

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
                .forResult(PictureConfig.REQUEST_CAMERA);//结果回调onActivityResult code


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {

                case PictureConfig.REQUEST_CAMERA:
                    selectListPic = PictureSelector.obtainMultipleResult(data);
                    if(selectListPic.size()>0){

                        ivSelectimg.setVisibility(View.GONE);
                        rvImages.setVisibility(View.VISIBLE);
                        adapter = new GridImageAdapter(UploadImgsActivity.this, onAddPicClickListener);
                        GridLayoutManager manager=new GridLayoutManager(UploadImgsActivity.this,5);
                        // FullyGridLayoutManager manager = new FullyGridLayoutManager(ZxjsReleaseActivity.this, 9, GridLayoutManager.VERTICAL, false);
                        rvImages.setLayoutManager(manager);
                        rvImages.setAdapter(adapter);
                        adapter.setSelectMax(maxSelectNum);
                        adapter.setList(selectListPic);
                        adapter.notifyDataSetChanged();
                    }

                    break;

            }
        }
    }
    /**
     * 多图片上传
     */
    private void upLoadPic(List<LocalMedia> selectList) {
        progressBar.setVisibility(View.VISIBLE);
        Toast.makeText(UploadImgsActivity.this,"图片保存中",Toast.LENGTH_LONG).show();
        List<File> fileList=new ArrayList<>();

        for (int i = 0; i < selectList.size(); i++) {
            Log.d("1111111111",selectList.get(i).getCompressPath());
            fileList.add(new File(selectList.get(i).getCompressPath()));

        }
        allFile = new File[fileList.size()];
        for (int i = 0; i < fileList.size(); i++) {
            allFile[i] = fileList.get(i);
        }
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        try {
            params.put("img[]", allFile);
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

     //   String url = "http://182.92.201.208:8080/home/index/tutu";

        client.post(ConstantValue.UPIMGS, params, new AsyncHttpResponseHandler() {

            @Override
            public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
                Toast.makeText(UploadImgsActivity.this,arg3.toString(),Toast.LENGTH_LONG).show();

            }

            @Override
            public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
                String data = new String(arg2);
                progressBar.setVisibility(View.GONE);
                Toast.makeText(UploadImgsActivity.this,"保存成功,请上传",Toast.LENGTH_LONG).show();
                try {
                    JSONObject object=new JSONObject(data);
                    imgsobj=object.getString("data");
                    Log.d("111111",imgsobj);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                PreferencesUtils.setPreferences(UploadImgsActivity.this,PreferencesUtils.USER_PIC,imgsobj);
                finish();

            }
            @Override
            public void onProgress(long bytesWritten, long totalSize) {
                // TODO Auto-generated method stub
                super.onProgress(bytesWritten, totalSize);
                progressBar.setMax((int) totalSize);
                progressBar.setProgress((int) bytesWritten);
            }
        });

    }

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {

            PictureSelector.create(UploadImgsActivity.this)
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
                    .videoQuality(0)// 视频录制质量 0 or 1 int*/
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
                    .forResult(PictureConfig.REQUEST_CAMERA);//结果回调onActivityResult code

        }

    };

    @Override
    protected void onNetErrorClick() {

    }


}
