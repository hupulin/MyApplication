package com.hzxm.wolaixiqh.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.hzxm.wolaixiqh.R;
import com.hzxm.wolaixiqh.base.BaseActivity;
import com.hzxm.wolaixiqh.login.present.LoginPresenter;
import com.hzxm.wolaixiqh.main.EvaluateActivity;
import com.hzxm.wolaixiqh.main.MainActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hzxmkuar.com.applibrary.domain.delivery.main.UserInfoTo;

/**
 *  Created by Administrator on 2018/12/14.
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.account_number)
    EditText account;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.select_icon)
    View selectIcon;
   private LoginPresenter presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setView();
        presenter = new LoginPresenter(this);
    }
    private void setView() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @OnClick({R.id.login,R.id.select_icon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login:

                if (!checkPhone(account.getText().toString()))
                    return;
                if (TextUtils.isEmpty(password.getText().toString())){
                    showMessage("请填写密码");
                    return;
                }
                presenter.login(account.getText().toString(),password.getText().toString());
//                startActivity(new Intent(this,EvaluateActivity.class));

                break;
            case R.id.select_icon:
                selectIcon.setSelected(!selectIcon.isSelected());
                selectIcon.setBackgroundResource(selectIcon.isSelected()?R.mipmap.login_select_agreement:R.mipmap.address_un_select);
                break;
        }
    }

    @Override
    protected void submitDataSuccess(Object data) {
        UserInfoTo userInfoTo= new Gson().fromJson(JSON.toJSONString(data), UserInfoTo.class);
        Intent intent = new Intent(appContext, MainActivity.class);
        intent.putExtra("IsSplash", true);
        userInfoHelp.saveUserLogin(true);
//        userInfoTo.setUid(loginTo.getUid());
//        userInfoTo.setHashid(loginTo.getHashid());
        userInfoHelp.saveUserInfo(userInfoTo);
        startActivity(intent);
        finish();
        goToAnimation(1);
    }
}
