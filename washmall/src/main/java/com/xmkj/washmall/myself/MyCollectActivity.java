package com.xmkj.washmall.myself;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseActivity;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_recycler_view);
        ButterKnife.bind(this);
        setTitleName("我的收藏");
        setRecycleView(new MyCollectAdapter(this),recyclerView,new MyCollectPresenter(this));
    }
}
