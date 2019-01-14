
package com.xmkj.washmall.integral.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.xmkj.washmall.R;
import com.xmkj.washmall.base.adapter.BaseAdapter;
import com.xmkj.washmall.base.adapter.BindingHolder;
import com.xmkj.washmall.databinding.IntegralOrderItemBinding;
import com.xmkj.washmall.databinding.IntegralRecordItemBinding;
import com.xmkj.washmall.databinding.MallOrderGoodsItemBinding;

import java.util.ArrayList;
import java.util.List;

import hzxmkuar.com.applibrary.domain.integral.IntegralOrderListTo;
import hzxmkuar.com.applibrary.domain.integral.IntegralRecordTo;

/**
 * Created by Administrator on 2018/8/28.
 **/

public class IntegralRecordAdapter extends BaseAdapter<IntegralRecordTo, IntegralRecordItemBinding> {

   public IntegralRecordAdapter(Activity context) {
        super(context);

    }


    @NonNull
    @Override
    public BindingHolder<IntegralRecordItemBinding> onCreateViewHolder(ViewGroup parent, int viewType) {

        IntegralRecordItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.integral_record_item, parent, false);

        BindingHolder<IntegralRecordItemBinding> holder = new BindingHolder<>(binding.getRoot());
        holder.setBinding(binding);

        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(BindingHolder<IntegralRecordItemBinding> holder, int position) {
        super.onBindViewHolder(holder, position);
        IntegralRecordItemBinding binding = holder.getBinding();
        IntegralRecordTo mode=mList.get(position);
        binding.recordName.setText(mode.getRfrom_text());
        binding.recordTime.setText(mode.getDateline());
        binding.recordScore.setText(mode.getScore());



    }


}
