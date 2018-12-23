package com.hzxm.wolaixiqh;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;


import com.hzxm.wolaixiqh.base.util.FileUtil;

import hzxmkuar.com.applibrary.api.ApiClient;


/**
 * Created by xzz on 2018/8/14.
 **/

public class MainApp extends Application {
public static Context appContext;
    public static String webBaseUrl="http://m.wumeihui.net/h5/page/";
    public static String shareUrl="http://m.wumeihui.net/download.html?tdsourcetag=s_pctim_aiomsg#";
    @Override
    public void onCreate() {
        super.onCreate();
       appContext=this;
        ApiClient.getInstance().init(this);

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


    public static String getCacheImagePath() {
        return FileUtil.getSdCardPath();

    }
}
