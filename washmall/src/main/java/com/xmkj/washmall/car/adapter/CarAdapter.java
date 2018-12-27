
package com.xmkj.washmall.car.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.xmkj.washmall.R;
import com.xmkj.washmall.base.adapter.BaseAdapter;
import com.xmkj.washmall.base.adapter.BindingHolder;
import com.xmkj.washmall.databinding.AddressItemBinding;
import com.xmkj.washmall.databinding.CarItemBinding;

import hzxmkuar.com.applibrary.domain.car.GoodsCarTo;
import hzxmkuar.com.applibrary.domain.main.ContactTo;
import hzxmkuar.com.applibrary.domain.main.MallGoodsTo;
import hzxmkuar.com.applibrary.domain.mall.GoodsDetailTo;

/**
 * Created by Administrator on 2018/8/28.
 **/

public class CarAdapter extends BaseAdapter<GoodsCarTo, CarItemBinding> {
   public CarAdapter(Activity context) {
        super(context);
    }


    @NonNull
    @Override
    public BindingHolder<CarItemBinding> onCreateViewHolder(ViewGroup parent, int viewType) {

        CarItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.car_item, parent, false);

        BindingHolder<CarItemBinding> holder = new BindingHolder<>(binding.getRoot());
        holder.setBinding(binding);

        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(BindingHolder<CarItemBinding> holder, int position) {
        super.onBindViewHolder(holder, position);
        CarItemBinding binding = holder.getBinding();
        GoodsCarTo mode=mList.get(position);
        binding.goodsName.setText(mode.getGoodsName());
        disPlayImage(binding.goodsImage,mode.getImageUrl());
        binding.price.setText(mode.getPrice());
        binding.specification.setText(mode.getSpecification());

    }


}
