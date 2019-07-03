package com.hzxm.wolaixiqh.main.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hzxm.wolaixiqh.R;
import com.hzxm.wolaixiqh.base.adapter.BaseAdapter;
import com.hzxm.wolaixiqh.base.adapter.BindingHolder;
import com.hzxm.wolaixiqh.databinding.OrderDeliveryItemBinding;
import com.hzxm.wolaixiqh.databinding.OrderRecordItemBinding;
import com.hzxm.wolaixiqh.main.ScanDecodeActivity;

import hzxmkuar.com.applibrary.domain.delivery.main.DeLiveryOrderListTo;

/**
 * Created by Administrator on 2018/12/15. 首页adapter
 */

public class OrderListAdapter extends BaseAdapter<DeLiveryOrderListTo.ListsEntity, OrderRecordItemBinding> {
    public OrderListAdapter(Activity context) {
        super(context);
    }


    @NonNull
    @Override
    public BindingHolder<OrderRecordItemBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        OrderRecordItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.order_record_item, parent, false);
        BindingHolder<OrderRecordItemBinding> holder = new BindingHolder<>(binding.getRoot());
        holder.setBinding(binding);

        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(BindingHolder<OrderRecordItemBinding> holder, int position) {
        super.onBindViewHolder(holder, position);
        OrderRecordItemBinding binding = holder.getBinding();
        DeLiveryOrderListTo.ListsEntity mode = mList.get(position);
        binding.orderNumText.setText(mode.getOrder_sn());
        binding.orderStatusText.setText(mode.getStatus_txt());
        binding.orderTime.setText(mode.getOrder_time());
        binding.pickupTime.setText(mode.getPickup_time());
        binding.remarks.setText(mode.getRemarks());
        binding.deliveryWardrobe.setText(mode.getDelivery_wardrobe_name());
        binding.depositWardrobe.setText(mode.getDeposit_wardrobe_name());
        binding.depositAddress.setText(mode.getDeposit_address());
        binding.deliveryAddress.setText(mode.getDelivery_address());
        binding.orderAmount.setText("￥"+mode.getOrder_amount());

//        binding.orderLayout
//        binding.addressLayout.setText(mode.getAddress());
//
//        binding.orderNumText.setText(mode.getOrder_sn());
//        binding.orderNumLayout.setText(mode.getWardrobe_title());


        binding.sweepAndUnpack.setVisibility(mode.getButton_list().getSmqh_btn()==1?View.VISIBLE:View.GONE);
        binding.sweepAndUnpack.setOnClickListener(view -> listen.onScanDecode(mode.getOrder_id()));
        binding.pickUpGoods.setVisibility(mode.getButton_list().getQrqh_btn()==1?View.VISIBLE:View.GONE);
        binding.pickUpGoods.setOnClickListener(view -> listen.onPickUpTheGoods(mode.getOrder_id()));
        binding.feedbackInform.setOnClickListener(v -> listen.feedbackInform(mode.getOrder_id()));
        binding.feedbackBack.setOnClickListener(v -> listen.feedbackBack(mode.getOrder_id()));
        binding.print.setOnClickListener(v -> listen.print(mode));


    }
    public  interface PickUpTheGoodsListener {
        void onPickUpTheGoods(int id); //
        void onScanDecode(int id); //
        void feedbackInform(int id); //
        void feedbackBack(int id); //
        void print(DeLiveryOrderListTo.ListsEntity mode); //
    }

    PickUpTheGoodsListener  listen;

    public void setOnAddSelectListener(PickUpTheGoodsListener pickUpTheGoodsListener) {
        this.listen = pickUpTheGoodsListener;

    }
}
