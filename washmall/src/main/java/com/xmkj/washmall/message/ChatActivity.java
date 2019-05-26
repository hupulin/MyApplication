package com.xmkj.washmall.message;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseChatFragment;

import com.tbruyelle.rxpermissions.RxPermissions;
import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.util.StatueBarUtil;

import hzxmkuar.com.applibrary.impl.PermissionListener;

/**
 * Created by Administrator on 2018/9/28.
 **/

public class ChatActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatueBarUtil.setStatueBarColor(getWindow(), "#3FB9FF");
        setContentView(R.layout.activity_chat);
        EMClient.getInstance().login("15168234205", "123456", new EMCallBack() {
            @Override
            public void onSuccess() {
              runOnUiThread(() -> {
                  EaseChatFragment fragment = new EaseChatFragment();
                            Bundle args = new Bundle();

                            args.putInt(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE);
                            args.putString(EaseConstant.EXTRA_USER_ID, getIntent().getStringExtra("Phone"));
                            args.putString("Name", "在线客服");
                            fragment.setArguments(args);
                            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commitAllowingStateLoss();

              });
            }

            @Override
            public void onError(int i, String s) {

            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
//        init();
//        new RxPermissions(this).requestEach(
//                Manifest.permission.RECORD_AUDIO
//        )
//                .subscribe(permission -> {
//                    if (permission.granted) {
//                        // 用户已经同意该权限
//                        init();
//
//                    } else if (permission.shouldShowRequestPermissionRationale) {
//                        init();
//                    } else {
//                        showMessage("请手动打开录音权限");
//
//                    }
//                });


    }

    public void init() {
        getPermission(Manifest.permission.RECORD_AUDIO, new PermissionListener() {
            @Override
            public void accept(String permission) {

                EMClient.getInstance().login("15168234205", "123456", new EMCallBack() {
                    @Override
                    public void onSuccess() {

                        runOnUiThread(() -> {
                           showMessage("登录成功");

//                            EaseChatFragment fragment = new EaseChatFragment();
//                            Bundle args = new Bundle();
//
//                            args.putInt(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE);
//                            args.putString(EaseConstant.EXTRA_USER_ID, getIntent().getStringExtra("UserId"));
//                            args.putString("Name", getIntent().getStringExtra("Name"));
//                            fragment.setArguments(args);
//                            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commitAllowingStateLoss();

                        });
                    }

                    @Override
                    public void onError(int i, String s) {

                    }

                    @Override
                    public void onProgress(int i, String s) {

                    }
                });
            }

            @Override
            public void refuse(String permission) {
                finish();
            }
        });
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        new RxPermissions(this).requestEach(
                Manifest.permission.RECORD_AUDIO
        )
                .subscribe(permission -> {
                    if (permission.granted) {
                        // 用户已经同意该权限
                        init();

                    } else if (permission.shouldShowRequestPermissionRationale) {
                        init();
                    } else {
                        showMessage("请手动打开录音权限");

                    }
                });
    }


}
