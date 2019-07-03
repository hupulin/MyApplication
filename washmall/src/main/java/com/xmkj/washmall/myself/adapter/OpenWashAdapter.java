
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
import com.xmkj.washmall.databinding.OrderWashItemBinding;

import hzxmkuar.com.applibrary.domain.order.WashOrderTo;

/**
 * Created by Administrator on 2018/8/28.
 **/

public class OpenWashAdapter extends BaseAdapter<WashOrderTo, OrderWashItemBinding> {
   public OpenWashAdapter(Activity context) {
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
        binding.wardrobe.setText(mode.getDelivery_wardrobe_name());
        binding.orderTime.setText(mode.getOrder_time());
        binding.saveAddress.setText(mode.getDelivery_address());
        binding.remark.setText(mode.getRemarks());
        binding.takeTime.setText(mode.getPickup_time());
        binding.takeWardrobe.setText(mode.getDeposit_wardrobe_name());
        binding.takeAddress.setText(mode.getDeposit_address());
        binding.orderNumber.setText(mode.getOrder_sn());
        binding.statue.setText(mode.getStatus_txt());
        binding.money.setText("￥"+mode.getOrder_amount());
        binding.cancel.setVisibility(View.GONE);
        binding.cancelLeft.setVisibility(View.GONE);
        binding.scan.setVisibility(View.VISIBLE);
        binding.save.setVisibility(View.GONE);
        binding.pay.setVisibility(View.GONE);
        binding.evaluate.setVisibility(View.GONE);



    }

    public interface  OrderWashAdapterListener{

        void scan(WashOrderTo mode);

        void cancelOrder(WashOrderTo mode);

        void pay(WashOrderTo mode);

        void evaluate(WashOrderTo mode);

        void takeWash(WashOrderTo mode);

    }

    private OrderWashAdapterListener listener;

    public void setOrderWashAdapterListener(OrderWashAdapterListener listener){
        this.listener=listener;
    }

}
