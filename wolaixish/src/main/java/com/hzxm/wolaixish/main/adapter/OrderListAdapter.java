package com.hzxm.wolaixish.main.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hzxm.wolaixish.R;
import com.hzxm.wolaixish.base.adapter.BaseAdapter;
import com.hzxm.wolaixish.base.adapter.BindingHolder;
import com.hzxm.wolaixish.databinding.OrderDeliveryItemBinding;

import hzxmkuar.com.applibrary.domain.order.OrderInfoTo;

/**
 *  Created by Administrator on 2018/12/16.
 */

public class OrderListAdapter extends BaseAdapter<OrderInfoTo,OrderDeliveryItemBinding> {
    public OrderListAdapter(Activity context) {
        super(context);
    }


    @NonNull
    @Override
    public BindingHolder<OrderDeliveryItemBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        OrderDeliveryItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.order_delivery_item, parent, false);

        BindingHolder<OrderDeliveryItemBinding> holder = new BindingHolder<>(binding.getRoot());
        holder.setBinding(binding);

        return holder;
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(BindingHolder<OrderDeliveryItemBinding> holder, int position) {
        super.onBindViewHolder(holder, position);
        OrderDeliveryItemBinding binding = holder.getBinding();

    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
