package com.xmkj.washmall.wash;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.myself.adapter.OpenWashAdapter;
import com.xmkj.washmall.myself.adapter.OrderWashAdapter;
import com.xmkj.washmall.wash.presenter.CanOpenPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hzxmkuar.com.applibrary.domain.order.WashOrderTo;

/**
 * Created by xzz on 2019/5/16.
 */

public class CanOpenActivity extends BaseActivity {
    @BindView(R.id.recycler_view)
    LRecyclerView recyclerView;
    private CanOpenPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_recycler_view);
        ButterKnife.bind(this);
        setTitleName("开柜订单");
        presenter = new CanOpenPresenter(this);
        setRecycleView(new OpenWashAdapter(this),recyclerView,presenter);

    }

    @Override
    public void loadDataSuccess(Object data) {

        List<WashOrderTo> orderList = (List<WashOrderTo>) data;
        if (orderList==null||orderList.size()==0){
            showMessage("暂无可开柜订单");
            new Handler().postDelayed(() -> {finish();
            goToAnimation(2);
            },2500);
            return;
        }
        if (orderList.size()==1)
            presenter.openWardrobe(orderList.get(0).getOrder_id()+"");


    }

    @Override
    public void recycleItemClick(View view, int position, Object data) {
        WashOrderTo mode= (WashOrderTo) data;
        presenter.openWardrobe(mode.getOrder_id()+"");
    }

    @Override
    protected void submitDataSuccess(Object data) {
        new Handler().postDelayed(() -> {finish();
            goToAnimation(2);
        },2500);
    }
}
