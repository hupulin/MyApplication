
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

import hzxmkuar.com.applibrary.domain.order.MallOrderTo;

/**
 * Created by Administrator on 2018/8/28.
 **/

public class OrderMallAdapter extends BaseAdapter<MallOrderTo, MyOrderMallItemBinding> {
   public OrderMallAdapter(Activity context) {
        super(context);
    }


    @NonNull
    @Override
    public BindingHolder<MyOrderMallItemBinding> onCreateViewHolder(ViewGroup parent, int viewType) {

        MyOrderMallItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.my_order_mall_item, parent, false);

        BindingHolder<MyOrderMallItemBinding> holder = new BindingHolder<>(binding.getRoot());
        holder.setBinding(binding);

        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(BindingHolder<MyOrderMallItemBinding> holder, int position) {
        super.onBindViewHolder(holder, position);
        MyOrderMallItemBinding binding = holder.getBinding();
        MallOrderTo mode=mList.get(position);
        binding.goodsName.setText(mode.getName());
        disPlayImage(binding.orderImage,mode.getImageUrl());
        binding.goodsNum.setText("x"+mode.getNum());
        binding.orderMoney.setText("￥ "+mode.getMoney());
        binding.specification.setText(mode.getSpecification());
        binding.remark.setText(mode.getRemark());
        binding.orderNumber.setText(mode.getOrderNo());
        binding.statue.setText(mode.getStatueStr());
        binding.pay.setText(mode.getPayStr());
        binding.pay.setVisibility(mode.getType()==4? View.GONE:View.VISIBLE);



    }


}
