
package com.xmkj.washmall.myself.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.xmkj.washmall.R;
import com.xmkj.washmall.base.adapter.BaseAdapter;
import com.xmkj.washmall.base.adapter.BindingHolder;
import com.xmkj.washmall.databinding.CarItemBinding;
import com.xmkj.washmall.databinding.MyCollectItemBinding;

import hzxmkuar.com.applibrary.domain.car.GoodsCarTo;

/**
 * Created by Administrator on 2018/8/28.
 **/

public class MyCollectAdapter extends BaseAdapter<GoodsCarTo, MyCollectItemBinding> {
   public MyCollectAdapter(Activity context) {
        super(context);
    }


    @NonNull
    @Override
    public BindingHolder<MyCollectItemBinding> onCreateViewHolder(ViewGroup parent, int viewType) {

        MyCollectItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.my_collect_item, parent, false);

        BindingHolder<MyCollectItemBinding> holder = new BindingHolder<>(binding.getRoot());
        holder.setBinding(binding);

        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(BindingHolder<MyCollectItemBinding> holder, int position) {
        super.onBindViewHolder(holder, position);
        MyCollectItemBinding binding = holder.getBinding();
        GoodsCarTo mode=mList.get(position);
        binding.goodsName.setText(mode.getGoods_name());
        disPlayImage(binding.goodsImage,mode.getSpec_image());
        binding.price.setText(mode.getGoods_price());
        binding.collectNum.setText(mode.getGoods_num()+"人收藏");


    }


}
