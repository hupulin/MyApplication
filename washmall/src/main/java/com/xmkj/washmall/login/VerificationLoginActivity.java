package com.xmkj.washmall.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.ruffian.library.RTextView;
import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.login.presenter.VerificationLoginPresenter;
import com.xmkj.washmall.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hzxmkuar.com.applibrary.domain.login.UserInfoTo;
import hzxmkuar.com.applibrary.domain.login.WechatLoginTo;

/**
 * Created by Administrator on 2018/12/24.
 */

public class VerificationLoginActivity extends BaseActivity {
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.verification)
    EditText verification;
    @BindView(R.id.get_verification)
    RTextView getVerification;
    private int countTime;
    private VerificationLoginPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_login);
        ButterKnife.bind(this);
        presenter = new VerificationLoginPresenter(this);
    }


    @Override
    public void loadDataSuccess(Object data) {
        showMessage("发送验证码成功" + data);
        getVerification.setEnabled(false);
        new Thread(() -> {
            for (int i = 60; i >= 0; i--) {
                countTime = i;
                SystemClock.sleep(1000);
                runOnUiThread(() -> getVerification.setText(countTime == 0 ? "重发验证码" : (countTime + "秒后重发")));
                if (countTime == 0)
                    runOnUiThread(() -> getVerification.setEnabled(true));
            }
        }).start();
    }

    @OnClick({R.id.get_verification, R.id.login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.get_verification:
                if (checkPhone(phone.getText().toString()))
                    presenter.getVerificationCode(phone.getText().toString());
                break;
            case R.id.login:
                if (!checkPhone(phone.getText().toString()))
                    return;
                if (TextUtils.isEmpty(verification.getText().toString())){
                    showMessage("请填写验证码");
                    return;
                }
                presenter.login(phone.getText().toString(),verification.getText().toString());
                break;
        }
    }

    @Override
    protected void submitDataSuccess(Object data) {
        WechatLoginTo loginTo= new Gson().fromJson(JSON.toJSONString(data), WechatLoginTo.class);
        Intent intent = new Intent(appContext, MainActivity.class);
        intent.putExtra("IsSplash", true);
        userInfoHelp.saveUserLogin(true);
        UserInfoTo userInfoTo = new UserInfoTo();
        userInfoTo.setUid(loginTo.getUid());
        userInfoTo.setHashid(loginTo.getHashid());
        userInfoHelp.saveUserInfo(userInfoTo);
        startActivity(intent);
        finish();
        goToAnimation(1);
    }
}
