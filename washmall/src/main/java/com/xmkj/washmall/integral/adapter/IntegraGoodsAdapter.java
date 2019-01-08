
package com.xmkj.washmall.integral.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.xmkj.washmall.R;
import com.xmkj.washmall.base.adapter.BaseAdapter;
import com.xmkj.washmall.base.adapter.BindingHolder;
import com.xmkj.washmall.databinding.MallGoodsItemBinding;

import hzxmkuar.com.applibrary.domain.integral.IntegralGoodsTo;
import hzxmkuar.com.applibrary.domain.main.MallGoodsTo;

/**
 * Created by Administrator on 2018/8/28.
 **/

public class IntegraGoodsAdapter extends BaseAdapter<IntegralGoodsTo, MallGoodsItemBinding> {
   public IntegraGoodsAdapter(Activity context) {
        super(context);
    }


    @NonNull
    @Override
    public BindingHolder<MallGoodsItemBinding> onCreateViewHolder(ViewGroup parent, int viewType) {

        MallGoodsItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.mall_goods_item, parent, false);

        BindingHolder<MallGoodsItemBinding> holder = new BindingHolder<>(binding.getRoot());
        holder.setBinding(binding);

        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(BindingHolder<MallGoodsItemBinding> holder, int position) {
        super.onBindViewHolder(holder, position);
        MallGoodsItemBinding binding = holder.getBinding();
        IntegralGoodsTo mode=mList.get(position);
        binding.goodsName.setText(mode.getGoods_name());
        binding.price.setText(mode.getGoods_integral()+"积分");
        disPlayImage(binding.goodsImage,mode.getGoods_image());

    }


}
