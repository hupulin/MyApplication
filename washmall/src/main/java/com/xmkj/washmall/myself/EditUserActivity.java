package com.xmkj.washmall.myself;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.makeramen.roundedimageview.RoundedImageView;
import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hzxmkuar.com.applibrary.domain.login.UserInfoTo;

/**
 * Created by Administrator on 2018/12/28.
 **/

public class EditUserActivity extends BaseActivity {
    @BindView(R.id.head_img)
    RoundedImageView headImg;
    @BindView(R.id.nick_name)
    EditText nickName;
    @BindView(R.id.verification)
    EditText verification;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        ButterKnife.bind(this);
        setTitleName("编辑个人资料");
        initView();

    }

    private void initView() {
        UserInfoTo userInfoTo=new UserInfoTo();
        displayImage(headImg,userInfoTo.getUserImg());
    }

    @OnClick({R.id.save, R.id.modify_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.save:
                break;
            case R.id.modify_password:
                Intent intent=new Intent(appContext,ModifyPasswordActivity.class);
                startActivity(intent);
                goToAnimation(1);
                break;
        }
    }
}
