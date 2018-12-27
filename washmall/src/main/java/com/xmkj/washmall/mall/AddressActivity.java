package com.xmkj.washmall.mall;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.mall.adapter.AddressAdapter;
import com.xmkj.washmall.mall.presenter.AddressPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/12/27.
 */

public class AddressActivity extends BaseActivity {
    @BindView(R.id.recycler_view)
    LRecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_recycler_view);
        ButterKnife.bind(this);
        setTitleName("收货地址管理");
        setRecycleView(new AddressAdapter(this), recyclerView,new AddressPresenter(this));

    }

    @Override
    public void recycleItemClick(View view, int position, Object data) {
        Intent intent=new Intent(appContext,AddAddressActivity.class);
        startActivity(intent);
        goToAnimation(1);
    }
}
