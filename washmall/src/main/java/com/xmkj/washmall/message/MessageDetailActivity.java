package com.xmkj.washmall.message;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseActivity;

/**
 * Created by Administrator on 2018/12/26.
 */

public class MessageDetailActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_detail);
        setTitleName("消息详情");
    }
}
