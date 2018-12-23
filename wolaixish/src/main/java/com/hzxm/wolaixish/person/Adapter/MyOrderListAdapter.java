package com.hzxm.wolaixish.person.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hzxm.wolaixish.R;
import com.hzxm.wolaixish.base.adapter.BaseAdapter;
import com.hzxm.wolaixish.base.adapter.BindingHolder;
import com.hzxm.wolaixish.databinding.MyOrderDeliveryItemBinding;
import com.hzxm.wolaixish.databinding.OrderDeliveryItemBinding;

import hzxmkuar.com.applibrary.domain.order.OrderInfoTo;

/**
 *  Created by Administrator on 2018/12/17.
 */

public class MyOrderListAdapter extends BaseAdapter<OrderInfoTo,MyOrderDeliveryItemBinding> {
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

    }

    @Override
    public int getItemCount() {
        return 20;
    }
}
