package com.xmkj.washmall.message;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.GridLayout;
import android.widget.TextView;

import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.message.presenter.MessageDetailPresenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import hzxmkuar.com.applibrary.domain.message.SystemMessageTo;

/**
 * Created by Administrator on 2018/12/26.
 */

public class MessageDetailActivity extends BaseActivity {
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.image_layout)
    GridLayout imageLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_detail);
        ButterKnife.bind(this);
        setTitleName("消息详情");
        MessageDetailPresenter presenter = new MessageDetailPresenter(this);
    }

    @Override
    public void loadDataSuccess(Object data) {
        SystemMessageTo mode = (SystemMessageTo) data;
        content.setText(mode.getMsg_desc());
        if (!TextUtils.isEmpty(mode.getMsg_pic()))
        setImageLayout(imageLayout,new ArrayList<>(Collections.singletonList(mode.getMsg_pic())),180);
    }
}
