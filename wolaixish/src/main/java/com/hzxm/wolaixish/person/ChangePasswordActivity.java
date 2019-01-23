package com.hzxm.wolaixish.person;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.hzxm.wolaixish.R;
import com.hzxm.wolaixish.base.BaseActivity;
import com.hzxm.wolaixish.person.present.ChangePasswordPresent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *  Created by Administrator on 2018/12/16.
 */

public class ChangePasswordActivity extends BaseActivity {
    @BindView(R.id.original_password)
    TextView passwd;
    @BindView(R.id.new_password_one)
    TextView newPassword;
    @BindView(R.id.new_password_two)
    TextView reNewPassword;
private ChangePasswordPresent present;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        setTitleName("修改密码");
        present=new ChangePasswordPresent(this);
        ButterKnife.bind(this);
    }
    @OnClick({R.id.confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.confirm:
                if (!checkPasswd(passwd.getText().toString(),newPassword.getText().toString(),reNewPassword.getText().toString()))
                    return;

                present.updateUserPasswd(passwd.getText().toString(),newPassword.getText().toString(),reNewPassword.getText().toString());
                break;
        }
    }
    protected boolean checkPasswd(String passwd,String newPasswd,String reNewPasswd){
        if (TextUtils.isEmpty(passwd)){
            showMessage("请输入原密码");
            return false;
        } if (TextUtils.isEmpty(newPasswd)){
            showMessage("请输入新密码");
            return false;
        }if (TextUtils.isEmpty(reNewPasswd)){
            showMessage("请确定新密码");
            return false;
        }

        return true;
    }

    @Override
    protected void submitDataSuccess(Object data) {
        finish();
    }
}
