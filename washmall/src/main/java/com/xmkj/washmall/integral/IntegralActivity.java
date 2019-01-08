package com.xmkj.washmall.integral;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.integral.adapter.IntegraGoodsAdapter;
import com.xmkj.washmall.mall.adapter.GoodsSortAdapter;
import com.xmkj.washmall.integral.presenter.IntegralPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import hzxmkuar.com.applibrary.domain.integral.IntegralGoodsTo;

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
        setRecycleView(new IntegraGoodsAdapter(this),recyclerView, new IntegralPresenter(this),2);

    }

    @Override
    public void recycleItemClick(View view, int position, Object data) {
        Intent intent=new Intent(appContext,IntegralGoodsDetailActivity.class);

        intent.putExtra("GoodsTo",(IntegralGoodsTo)data);
        startActivity(intent);
        goToAnimation(1);
    }
}
