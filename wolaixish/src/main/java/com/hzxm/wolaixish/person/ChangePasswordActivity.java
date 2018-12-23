package com.hzxm.wolaixish.person;

import android.os.Bundle;

import com.hzxm.wolaixish.R;
import com.hzxm.wolaixish.base.BaseActivity;

import butterknife.ButterKnife;

/**
 *  Created by Administrator on 2018/12/16.
 */

public class ChangePasswordActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        setTitleName("修改密码");
        ButterKnife.bind(this);
    }
}
