
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
import hzxmkuar.com.applibrary.domain.wardrobe.WardrobeDetailTo;

/**
 * Created by Administrator on 2018/8/28.
 **/

public class WardrobelAdapter extends BaseAdapter<WardrobeDetailTo.GridListBean, WardrobeItemBinding> {
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
        WardrobeDetailTo.GridListBean mode=mList.get(position);
        binding.floor.setText(mode.getFloor_no()+"层");
        binding.statue.setText(mode.getIs_full()==0?"空闲":"使用中");
        binding.position.setText(mode.getGrid_title());




    }


}
