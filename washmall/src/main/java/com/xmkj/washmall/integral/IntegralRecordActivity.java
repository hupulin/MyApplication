package com.xmkj.washmall.integral;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.integral.adapter.IntegralRecordAdapter;
import com.xmkj.washmall.integral.presenter.IntegralRecordPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2019/1/12.
 */

public class IntegralRecordActivity extends BaseActivity {

    @BindView(R.id.recycler_view)
    LRecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_recycler_view);
        ButterKnife.bind(this);
        setTitleName("积分记录");
        setRecycleView(new IntegralRecordAdapter(this),recyclerView,new IntegralRecordPresenter(this));
    }
}
