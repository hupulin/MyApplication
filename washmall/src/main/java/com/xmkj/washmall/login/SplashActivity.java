package com.xmkj.washmall.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.main.MainActivity;
import com.xmkj.washmall.mall.AbortOrderActivity;
import com.xmkj.washmall.mall.IntegralActivity;
import com.xmkj.washmall.mall.PayOrderActivity;

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
            if (userInfoHelp.getUserLogin()){
                Intent intent=new Intent(appContext,MainActivity.class);
                startActivity(intent);
                finish();
                goToAnimation(1);
            }else {
                Intent intent=new Intent(appContext,LoginActivity.class);
                startActivity(intent);
                finish();
                goToAnimation(1);
            }

        } ,2500);
    }
}
