package com.xmkj.washmall.integral.fragment;

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
import com.xmkj.washmall.integral.IntegralOrderDetailActivity;
import com.xmkj.washmall.integral.adapter.IntegralOrderAdapter;
import com.xmkj.washmall.integral.presenter.IntegralFragmentPresenter;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import hzxmkuar.com.applibrary.domain.integral.IntegralOrderListTo;

/**
 * Created by Administrator on 2019/1/12.
 */

@SuppressLint("ValidFragment")
public class IntegralFragment extends BaseFragment {
    private int type;
    private IntegralFragmentPresenter presenter;

    public   IntegralFragment(int type){
        this.type=type;
    }


    Unbinder unbinder;
    @BindView(R.id.recycler_view)
    LRecyclerView recyclerView;


    private boolean isViewCreate;
    private boolean isUiVisible;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = View.inflate(appContext, R.layout.common_fragment_recyclerview, null);

        unbinder = ButterKnife.bind(this, rootView);
        isViewCreate = true;
        presenter = new IntegralFragmentPresenter(this);
        setAdapterListener();
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
    IntegralOrderAdapter adapter;
    public void loadData() {
        if (isViewCreate && isUiVisible) {
            isUiVisible = false;
            isViewCreate = false;
            presenter.getOrderList(type);
            setRecycleView(adapter,recyclerView, presenter);
        }
    }
        private void setAdapterListener() {

            adapter=   new IntegralOrderAdapter(getActivity(),type);

            adapter.setOrderMallAdapterListener(new IntegralOrderAdapter.OrderMallAdapterListener() {


                @Override
                public void confirmReceiver(IntegralOrderListTo mode) {
                    WashAlertDialog.show(getActivity(),"提示","确认收货").setOnClickListener(view -> {
                        WashAlertDialog.dismiss();
                        presenter.confirmReceiver(mode.getOrder_id()+"");
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
    public void recycleItemClick(View view, int position, Object data) {
        IntegralOrderListTo mode= (IntegralOrderListTo) data;
        Intent intent=new Intent(appContext, IntegralOrderDetailActivity.class);
        intent.putExtra("OrderId",mode.getOrder_id()+"");
        startActivity(intent);
        goToAnimation(1);
    }
}
