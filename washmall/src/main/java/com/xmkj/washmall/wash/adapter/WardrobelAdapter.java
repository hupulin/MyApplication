
package com.xmkj.washmall.wash.adapter;

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
import com.xmkj.washmall.databinding.WardrobeItemBinding;

import hzxmkuar.com.applibrary.domain.main.MainWardrobeTo;
import hzxmkuar.com.applibrary.domain.order.MallOrderTo;

/**
 * Created by Administrator on 2018/8/28.
 **/

public class WardrobelAdapter extends BaseAdapter<MainWardrobeTo, WardrobeItemBinding> {
   public WardrobelAdapter(Activity context) {
        super(context);
    }


    @NonNull
    @Override
    public BindingHolder<WardrobeItemBinding> onCreateViewHolder(ViewGroup parent, int viewType) {

        WardrobeItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.wardrobe_item, parent, false);

        BindingHolder<WardrobeItemBinding> holder = new BindingHolder<>(binding.getRoot());
        holder.setBinding(binding);

        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(BindingHolder<WardrobeItemBinding> holder, int position) {
        super.onBindViewHolder(holder, position);
        WardrobeItemBinding binding = holder.getBinding();
        MainWardrobeTo mode=mList.get(position);
//        binding.floor.setText(mode.getFloor());
//        binding.statue.setText(mode.getStatue());
//        binding.position.setText(mode.getFloorName());




    }


}
