package com.xmkj.washmall;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;


import com.baidu.mapapi.SDKInitializer;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.EaseUI;
import com.xmkj.washmall.base.util.FileUtil;

import hzxmkuar.com.applibrary.api.ApiClient;


/**
 * Created by xzz on 2018/8/14.
 **/

public class MainApp extends Application {
public static Context appContext;
    public static String webBaseUrl="http://xmap18100040.php.hzxmnet.com/h5/page/";
    public static String shareUrl="http://m.wumeihui.net/download.html?tdsourcetag=s_pctim_aiomsg#";
    @Override
    public void onCreate() {
        super.onCreate();
       appContext=this;
        ApiClient.getInstance().init(this);

        initIM();

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


    public static String getCacheImagePath() {
        return FileUtil.getSdCardPath();
    }


    private void initIM(){
        EMOptions options = new EMOptions();
// 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false);
        options.setAutoTransferMessageAttachments(true);
        options.setAutoDownloadThumbnail(true);
        EMClient.getInstance().init(this,options);
        EMClient.getInstance().setDebugMode(true);
        EMOptions options2 = new EMOptions();

        options2.setAcceptInvitationAlways(false);

        EaseUI.getInstance().init(appContext, options2);


        SDKInitializer.initialize(this);
    }
}
