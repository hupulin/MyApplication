package com.hzxm.wolaixish.main.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hzxm.wolaixish.R;
import com.hzxm.wolaixish.base.adapter.BaseAdapter;
import com.hzxm.wolaixish.base.adapter.BindingHolder;
import com.hzxm.wolaixish.databinding.OrderDeliveryItemBinding;
import com.hzxm.wolaixish.main.ScanDecodeActivity;

import hzxmkuar.com.applibrary.domain.delivery.main.DeLiveryOrderListTo;

/**
 * Created by Administrator on 2018/12/16.
 */

public class OrderListAdapter extends BaseAdapter<DeLiveryOrderListTo.ListsEntity, OrderDeliveryItemBinding> {
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
        DeLiveryOrderListTo.ListsEntity mode = mList.get(position);
        binding.addressLayout.setText(mode.getAddress());
        binding.orderStatusText.setText(mode.getStatus_txt());
        binding.orderNumText.setText(mode.getOrder_sn());
        binding.orderPayLayout.setText("￥"+mode.getOrder_amount());
        binding.orderTimeLayout.setText(mode.getExpect_delivery_time());
        binding.orderNumLayout.setText(mode.getWardrobe_title());
        binding.sweepAndUnpack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, ScanDecodeActivity.class));

            }
        });

    }

}
