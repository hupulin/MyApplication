package com.xmkj.washmall.myself;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.WashAlertDialog;
import com.xmkj.washmall.myself.adapter.MyCollectAdapter;
import com.xmkj.washmall.myself.presenter.MyCollectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/12/28.
 */

public class MyCollectActivity extends BaseActivity {
    @BindView(R.id.recycler_view)
    LRecyclerView recyclerView;
    private MyCollectPresenter presenter;
    private MyCollectAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_recycler_view);
        ButterKnife.bind(this);
        setTitleName("我的收藏");
        presenter = new MyCollectPresenter(this);
        adapter = new MyCollectAdapter(this);
        setRecycleView(adapter,recyclerView,presenter);
        setAdapterListener();
    }

    private void setAdapterListener() {
        adapter.setMyCollectAdapterListener(mode -> {
            WashAlertDialog.show(this,"提示","确定删除收藏").setOnClickListener(view -> {
                WashAlertDialog.dismiss();
                presenter.deleteCollect(mode.getCollect_id());
            });
        });
    }
}
