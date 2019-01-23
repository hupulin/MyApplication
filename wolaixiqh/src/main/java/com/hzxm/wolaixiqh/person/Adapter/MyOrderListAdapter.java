package com.hzxm.wolaixiqh.person.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hzxm.wolaixiqh.R;
import com.hzxm.wolaixiqh.base.adapter.BaseAdapter;
import com.hzxm.wolaixiqh.base.adapter.BindingHolder;
import com.hzxm.wolaixiqh.databinding.MyOrderDeliveryItemBinding;

import hzxmkuar.com.applibrary.domain.delivery.main.DeLiveryOrderListTo;

/**
 *  Created by Administrator on 2018/12/17. 我的订单adapter
 */

public class MyOrderListAdapter extends BaseAdapter<DeLiveryOrderListTo.ListsEntity,MyOrderDeliveryItemBinding> {
    public MyOrderListAdapter(Activity context) {
        super(context);
    }


    @NonNull
    @Override
    public BindingHolder<MyOrderDeliveryItemBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        MyOrderDeliveryItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.my_order_delivery_item, parent, false);

        BindingHolder<MyOrderDeliveryItemBinding> holder = new BindingHolder<>(binding.getRoot());
        holder.setBinding(binding);

        return holder;
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(BindingHolder<MyOrderDeliveryItemBinding> holder, int position) {
        super.onBindViewHolder(holder, position);
        MyOrderDeliveryItemBinding binding = holder.getBinding();
        DeLiveryOrderListTo.ListsEntity mode = mList.get(position);
//        binding.addressLayout.setText(mode.getAddress());
        binding.orderStatusText.setText(mode.getStatus_txt());
        binding.orderNumText.setText(mode.getOrder_sn());
        binding.orderPayLayout.setText("￥"+mode.getOrder_amount());
//        binding.orderTimeLayout.setText(mode.getExpect_delivery_time());
        binding.orderNumLayout.setText(mode.getWardrobe_title());
    }


}
