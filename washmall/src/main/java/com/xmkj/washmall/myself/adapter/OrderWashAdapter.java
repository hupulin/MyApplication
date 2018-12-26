
package com.xmkj.washmall.myself.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xmkj.washmall.R;
import com.xmkj.washmall.base.adapter.BaseAdapter;
import com.xmkj.washmall.base.adapter.BindingHolder;
import com.xmkj.washmall.databinding.MyOrderMallItemBinding;
import com.xmkj.washmall.databinding.OrderWashItemBinding;

import hzxmkuar.com.applibrary.domain.order.MallOrderTo;
import hzxmkuar.com.applibrary.domain.order.WashOrderTo;

/**
 * Created by Administrator on 2018/8/28.
 **/

public class OrderWashAdapter extends BaseAdapter<WashOrderTo, OrderWashItemBinding> {
   public OrderWashAdapter(Activity context) {
        super(context);
    }


    @NonNull
    @Override
    public BindingHolder<OrderWashItemBinding> onCreateViewHolder(ViewGroup parent, int viewType) {

        OrderWashItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.order_wash_item, parent, false);

        BindingHolder<OrderWashItemBinding> holder = new BindingHolder<>(binding.getRoot());
        holder.setBinding(binding);

        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(BindingHolder<OrderWashItemBinding> holder, int position) {
        super.onBindViewHolder(holder, position);
        OrderWashItemBinding binding = holder.getBinding();
        WashOrderTo mode=mList.get(position);
        binding.wardrobe.setText(mode.getWardrobe());
        binding.address.setText(mode.getAddress());
        binding.orderNumber.setText(mode.getOrderNo());
        binding.statue.setText(mode.getStatueStr());
        binding.pay.setText(mode.getPayStr());
        binding.pay.setVisibility((mode.getType()==3||mode.getType()==2)? View.GONE:View.VISIBLE);
        binding.money.setText(mode.getMoney());
        binding.moneyLayout.setVisibility(mode.getType()==1?View.INVISIBLE:View.VISIBLE);

    }


}
