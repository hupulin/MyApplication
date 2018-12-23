package com.hzxm.wolaixish.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.hzxm.wolaixish.main.MainActivity;
import com.hzxm.wolaixish.R;
import com.hzxm.wolaixish.base.BaseActivity;
import com.hzxm.wolaixish.base.Event;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hzxmkuar.com.applibrary.domain.login.WechatUserInfoTo;

/**
 *  Created by Administrator on 2018/12/14.
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.account_number)
    EditText accountNumber;
    @BindView(R.id.password)
    EditText password;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setView();
//        presenter = new LoginPresenter(this);
        EventBus.getDefault().register(this);
    }
    private void setView() {
    }
    @Subscribe
    public void wechatLoginData(Event<WechatUserInfoTo> event) {
        if ("WechatLoginSuccess".equals(event.getType())) {
//            wechatBg.setVisibility(View.VISIBLE);
//            presenter.wechatLogin(event.getData());
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @OnClick({R.id.login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login:
                Intent intent = new Intent(appContext, MainActivity.class);
                intent.putExtra("IsSplash", true);
                startActivity(intent);
                finish();
                goToAnimation(1);
                break;
        }
    }
}
