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
import com.xmkj.washmall.mall.SelectPayActivity;
import com.xmkj.washmall.myself.OrderDetailActivity;
import com.xmkj.washmall.myself.adapter.OrderMallAdapter;
import com.xmkj.washmall.myself.presenter.MyOrderPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import hzxmkuar.com.applibrary.domain.order.MallOrderTo;

/**
 * Created by Administrator on 2018/12/25.
 **/

@SuppressLint("ValidFragment")
public class OrderFragment extends BaseFragment {
    @BindView(R.id.recycler_view)
    LRecyclerView recyclerView;
    Unbinder unbinder;
    private boolean isViewCreate;
    private boolean isUiVisible;
    private int type;
    private OrderMallAdapter adapter;
    private MyOrderPresenter presenter;

    public OrderFragment(int type){
        this.type=type;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         rootView = View.inflate(appContext, R.layout.common_fragment_recyclerview, null);

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
            adapter = new OrderMallAdapter(getActivity());
            presenter = new MyOrderPresenter(this);
            presenter.getOrderList(type);
            setRecycleView(adapter,recyclerView, presenter,false);
            setAdapterListener();
        }


    }

    private void setAdapterListener() {
        adapter.setOrderMallAdapterListener(new OrderMallAdapter.OrderMallAdapterListener() {
            @Override
            public void pay(MallOrderTo mode) {
                Intent intent=new Intent(appContext, SelectPayActivity.class);
                intent.putExtra("OrderId",mode.getId());
                intent.putExtra("Money",mode.getTotal_amount());
                startActivity(intent);
                goToAnimation(1);
            }

            @Override
            public void send(MallOrderTo mode) {
                WashAlertDialog.show(getActivity(),"提示","催发货").setOnClickListener(view -> {
                    WashAlertDialog.dismiss();
                });
            }

            @Override
            public void confirmReceiver(MallOrderTo mode) {
                WashAlertDialog.show(getActivity(),"提示","确认收货").setOnClickListener(view -> {
                    WashAlertDialog.dismiss();
                    presenter.confirmReceiver(mode.getId()+"");
                });
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void loadDataSuccess(Object data) {

    }

    @Override
    public void recycleItemClick(View view, int position, Object data) {
        MallOrderTo mode= (MallOrderTo) data;
        Intent intent=new Intent(appContext, OrderDetailActivity.class);
        intent.putExtra("OrderId",mode.getId()+"");
        startActivity(intent);
        goToAnimation(1);
    }


}
