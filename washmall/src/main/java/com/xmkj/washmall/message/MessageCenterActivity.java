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
        Intent intent=new Intent(appContext,MessageDetailActivity.class);
        startActivity(intent);
        goToAnimation(1);
    }
}
