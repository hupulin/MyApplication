package com.xmkj.washmall.mall;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.mall.adapter.GoodsSortAdapter;
import com.xmkj.washmall.mall.presenter.GoodsSortPresenter;
import com.xmkj.washmall.mall.presenter.IntegralPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/12/27.
 */

public class IntegralActivity extends BaseActivity {


    @BindView(R.id.recycler_view)
    LRecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integral);
        ButterKnife.bind(this);
        setTitleName("积分商城");
        setRecycleView(new GoodsSortAdapter(this),recyclerView, new IntegralPresenter(this),2);

    }

    @Override
    public void recycleItemClick(View view, int position, Object data) {
        Intent intent=new Intent(appContext,IntegralGoodsDetailActivity.class);
        startActivity(intent);
        goToAnimation(1);
    }
}
