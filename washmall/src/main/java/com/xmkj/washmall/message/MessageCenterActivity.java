package com.xmkj.washmall.message;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.message.adapter.MessageCenterAdapter;
import com.xmkj.washmall.message.presenter.MessageCenterPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import hzxmkuar.com.applibrary.domain.message.SystemMessageTo;

/**
 * Created by Administrator on 2018/12/26.
 */

public class MessageCenterActivity extends BaseActivity {
    @BindView(R.id.recycler_view)
    LRecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);
        setTitleName("消息中心");
        setRecycleView(new MessageCenterAdapter(this),recyclerView,new MessageCenterPresenter(this));
    }

    @Override
    public void recycleItemClick(View view, int position, Object data) {
        SystemMessageTo mode= (SystemMessageTo) data;
        Intent intent=new Intent(appContext,MessageDetailActivity.class);
        intent.putExtra("MessageId",mode.getId());
        startActivity(intent);
        goToAnimation(1);
    }
}
