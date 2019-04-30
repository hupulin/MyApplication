package com.xmkj.washmall.myself.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseFragment;
import com.xmkj.washmall.base.WashAlertDialog;
import com.xmkj.washmall.myself.OrderDetailActivity;
import com.xmkj.washmall.myself.adapter.OrderMallAdapter;
import com.xmkj.washmall.myself.adapter.OrderWashAdapter;
import com.xmkj.washmall.myself.presenter.MyOrderPresenter;
import com.xmkj.washmall.myself.presenter.MyWashOrderPresenter;
import com.xmkj.washmall.wash.ScanActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import hzxmkuar.com.applibrary.domain.order.WashOrderTo;

/**
 * Created by Administrator on 2018/12/25.
 **/

@SuppressLint("ValidFragment")
public class OrderWashFragment extends BaseFragment {
    @BindView(R.id.recycler_view)
    LRecyclerView recyclerView;
    Unbinder unbinder;
    private boolean isViewCreate;
    private boolean isUiVisible;
    private int type;
    private OrderWashAdapter adapter;
    private MyWashOrderPresenter presenter;

    public OrderWashFragment(int type){
        this.type=type;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = View.inflate(appContext, R.layout.common_fragment_recyclerview, null);

        unbinder = ButterKnife.bind(this, rootView);
        isViewCreate = true;

        loadData();
        return rootView;
    }



    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            isUiVisible = true;
            loadData();
        } else {
            isUiVisible = false;
        }
        super.setUserVisibleHint(isVisibleToUser);

    }

    public void loadData() {
        if (isViewCreate && isUiVisible) {

            isUiVisible = false;
            isViewCreate = false;
            adapter = new OrderWashAdapter(getActivity());
            presenter = new MyWashOrderPresenter(this);
            presenter.getOrderList(type);
            setRecycleView(adapter,recyclerView, presenter);
            setAdapterListener();
        }


    }

    private void setAdapterListener() {
        adapter.setOrderWashAdapterListener(new OrderWashAdapter.OrderWashAdapterListener() {
            @Override
            public void scan(WashOrderTo mode) {
                Intent intent=new Intent(appContext, ScanActivity.class);
                intent.putExtra("OrderId",mode.getOrder_id()+"");
                startActivity(intent);
                goToAnimation(1);
            }

            @Override
            public void cancelOrder(WashOrderTo mode) {
                WashAlertDialog.show(getActivity(),"提示","确定取消订单").setOnClickListener(view -> {
                    WashAlertDialog.dismiss();
                    presenter.cancelOrder(mode.getOrder_id());
                });
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
