package com.xmkj.washmall.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.main.MainActivity;
import com.xmkj.washmall.message.MessageCenterActivity;
import com.xmkj.washmall.wash.WardrobeActivity;

/**
 * Created by Administrator on 2018/12/24.
 **/

public class SplashActivity extends BaseActivity {
    private Handler handler=new Handler();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        handler.postDelayed(() ->{
            Intent intent=new Intent(appContext,MainActivity.class);
            startActivity(intent);
            goToAnimation(1);
        } ,2500);
    }
}
