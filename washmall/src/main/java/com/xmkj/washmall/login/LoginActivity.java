package com.xmkj.washmall.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.util.StatueBarUtil;
import com.xmkj.washmall.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/12/24.
 **/

public class LoginActivity extends BaseActivity {
    @BindView(R.id.account)
    EditText account;
    @BindView(R.id.password)
    EditText password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        StatueBarUtil.setStatueBarTextWhite(getWindow());
        ButterKnife.bind(this);
    }

    @OnClick({R.id.register, R.id.verification_login, R.id.forget_password, R.id.login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register:
                Intent intent=new Intent(appContext,RegisterActivity.class);
                startActivity(intent);
                goToAnimation(1);

                break;
            case R.id.verification_login:
                intent=new Intent(appContext,VerificationLoginActivity.class);
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.forget_password:
                intent=new Intent(appContext,ForgetPasswordActivity.class);
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.login:
                intent=new Intent(appContext, MainActivity.class);
                startActivity(intent);
                goToAnimation(1);
                break;
        }
    }
}
