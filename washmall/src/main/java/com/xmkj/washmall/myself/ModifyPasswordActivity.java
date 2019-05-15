package com.xmkj.washmall.myself;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.EditText;

import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.myself.presenter.ModifyPasswordPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/12/28.
 */

public class ModifyPasswordActivity extends BaseActivity {

    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.new_password)
    EditText newPassword;
    @BindView(R.id.confirm_password)
    EditText confirmPassword;
    private ModifyPasswordPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_password);
        ButterKnife.bind(this);
        setTitleName("修改密码");
        presenter = new ModifyPasswordPresenter(this);
    }

    @OnClick(R.id.save)
    public void onViewClicked() {
        if (TextUtils.isEmpty(password.getText().toString())){
            showMessage("请填写旧密码");
            return;
        }
        if (TextUtils.isEmpty(newPassword.getText().toString())){
            showMessage("请填写新密码");
            return;
        }
        if (TextUtils.isEmpty(confirmPassword.getText().toString())){
            showMessage("请确认密码");
            return;
        }
        presenter.modifyPassword(password.getText().toString(),newPassword.getText().toString(),confirmPassword.getText().toString());
    }

    @Override
    protected void submitDataSuccess(Object data) {
      showMessage("修改密码成功");
        new Handler().postDelayed(() ->{
            finish();
            goToAnimation(2);
        } ,2500);
    }
}
