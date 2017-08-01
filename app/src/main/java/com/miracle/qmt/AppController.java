package com.miracle.qmt;

import android.app.Application;
import android.graphics.Color;

import com.miracle.qmt.ui.view.GlideImageLoader;
import com.pgyersdk.crash.PgyCrashManager;
import com.pgyersdk.update.PgyUpdateManager;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.ThemeConfig;

/**
 * Created by Administrator on 2017/7/31.
 */

public class AppController extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initCamer();
//        PgyCrashManager.register(getApplicationContext());
    }

    private void initCamer() {
        //配置功能
        FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(true)
                .setEnableEdit(true)
                .setEnableCrop(true)
                .setCropSquare(true)
                .setEnableRotate(true)
                .setEnablePreview(true)
                .setForceCrop(true)
                .build();
        //ThemeConfig.CYAN
        ThemeConfig theme = new ThemeConfig.Builder()
                .setTitleBarBgColor(Color.rgb(29, 207, 155))
                .build();
        //配置imageloader
        ImageLoader imageloader = new GlideImageLoader();
        CoreConfig coreConfig = new CoreConfig.Builder(getApplicationContext(), imageloader, theme)
//                .setDebug(BuildConfig.DEBUG)
                .setFunctionConfig(functionConfig)
                .build();
        GalleryFinal.init(coreConfig);
    }
}
