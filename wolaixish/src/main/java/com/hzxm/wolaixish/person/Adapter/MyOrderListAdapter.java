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

import hzxmkuar.com.applibrary.domain.delivery.main.DeLiveryOrderListTo;
import hzxmkuar.com.applibrary.domain.order.OrderInfoTo;

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
        binding.orderStatusText.setText(mode.getStatus_txt());
        binding.orderNumText.setText(mode.getOrder_sn());
        binding.orderTime.setText(mode.getOrder_time());
        binding.pickupTime.setText(mode.getPickup_time());
        binding.remarks.setText(mode.getRemarks());
        binding.deliveryWardrobe.setText(mode.getDelivery_wardrobe_name());
        binding.depositWardrobe.setText(mode.getDeposit_wardrobe_name());
        binding.depositAddress.setText(mode.getDeposit_address());
        binding.deliveryAddress.setText(mode.getDelivery_address());
        binding.orderAmount.setText("￥"+mode.getOrder_amount());
//        binding.addressLayout.setText(mode.getAddress());
//
//        binding.orderPayLayout.setText("￥"+mode.getOrder_amount());
//        binding.orderTimeLayout.setText(mode.getExpect_delivery_time());
//        binding.orderNumLayout.setText(mode.getWardrobe_title());
    }


}
