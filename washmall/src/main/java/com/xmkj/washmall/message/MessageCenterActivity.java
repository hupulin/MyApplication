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
    private MessageCenterPresenter presenter;
    private MessageCenterAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);
        setTitleName("消息中心");
        presenter = new MessageCenterPresenter(this);
        adapter = new MessageCenterAdapter(this);
        setRecycleView(adapter,recyclerView,presenter);
    }

    @Override
    public void recycleItemClick(View view, int position, Object data) {
        SystemMessageTo mode= (SystemMessageTo) data;
        Intent intent=new Intent(appContext,MessageDetailActivity.class);
        mode.setIs_read(1);
        adapter.notifyDataSetChanged();
        intent.putExtra("MessageId",mode.getId());
        startActivity(intent);
        goToAnimation(1);
    }


}
