package com.hzxm.wolaixiqh.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;


import com.hzxm.wolaixiqh.R;
import com.hzxm.wolaixiqh.base.BaseActivity;
import com.hzxm.wolaixiqh.main.MainActivity;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2018/12/15.
 */

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        JPushInterface.init(this);
        new Handler().postDelayed(() -> {
            if (userInfoHelp.getUserLogin()){

                    Intent intent = new Intent(appContext, MainActivity.class);
//                Intent intent=new Intent(appContext,LoginActivity.class);


                intent.putExtra("IsSplash",true);
                    startActivity(intent);


            }else{
                Intent intent=new Intent(appContext,LoginActivity.class);
                startActivity(intent);
            }
            goToAnimation(1);
            finish();

        },3000);

    }
}


