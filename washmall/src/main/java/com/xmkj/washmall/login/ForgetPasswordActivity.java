package com.xmkj.washmall.login;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.ruffian.library.RTextView;
import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.login.presenter.ForgetPasswordPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/12/25.
 **/

public class ForgetPasswordActivity extends BaseActivity {
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.verification)
    EditText verification;
    @BindView(R.id.get_verification)
    RTextView getVerification;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.confirm_password)
    EditText confirmPassword;
    private int countTime;
    private ForgetPasswordPresenter presenter;
    private Handler handler=new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        ButterKnife.bind(this);
        presenter = new ForgetPasswordPresenter(this);
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
                if (TextUtils.isEmpty(password.getText().toString())){
                    showMessage("请填写密码");
                    return;
                }
                if (TextUtils.isEmpty(confirmPassword.getText().toString())){
                    showMessage("请填写确认密码");
                    return;
                }
                if (!password.getText().toString().equals(confirmPassword.getText().toString())){
                    showMessage("两次密码不相同");
                    return;
                }
                presenter.modifyPassword(phone.getText().toString(),verification.getText().toString(),password.getText().toString(),confirmPassword.getText().toString());
                break;
        }
    }

    @Override
    protected void submitDataSuccess(Object data) {
        showMessage("修改密码成功");
        handler.postDelayed(this::finish,2500);
    }
}
