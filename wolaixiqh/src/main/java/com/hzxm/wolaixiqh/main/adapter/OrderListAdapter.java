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
        binding.addressLayout.setText(mode.getAddress());
        binding.orderStatusText.setText(mode.getStatus_txt());
        binding.orderNumText.setText(mode.getOrder_sn());
        binding.orderNumLayout.setText(mode.getWardrobe_title());


        binding.sweepAndUnpack.setVisibility(mode.getButton_list().getSmkx_btn()==1?View.VISIBLE:View.GONE);
        binding.sweepAndUnpack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, ScanDecodeActivity.class));

            }
        });
//        binding.pickUpTheGoods.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                pickUpTheGoodsListener .onPickUpTheGoods(mode.getOrder_id());
//            }
//        });

    }
    public  interface PickUpTheGoodsListener {
        public void onPickUpTheGoods(int id);
    }

    // add click callback
    PickUpTheGoodsListener  pickUpTheGoodsListener;

    public void setOnAddSelectListener(PickUpTheGoodsListener pickUpTheGoodsListener) {
        this.pickUpTheGoodsListener = pickUpTheGoodsListener;

    }
}
