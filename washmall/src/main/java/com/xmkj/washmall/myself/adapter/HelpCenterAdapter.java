
package com.xmkj.washmall.myself.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xmkj.washmall.R;
import com.xmkj.washmall.base.adapter.BaseAdapter;
import com.xmkj.washmall.base.adapter.BindingHolder;
import com.xmkj.washmall.databinding.CouponItemBinding;
import com.xmkj.washmall.databinding.HelpCenterItemBinding;

import hzxmkuar.com.applibrary.domain.myself.CouponTo;
import hzxmkuar.com.applibrary.domain.myself.HelpCenterTo;

/**
 * Created by Administrator on 2018/8/28.
 **/

public class HelpCenterAdapter extends BaseAdapter<HelpCenterTo, HelpCenterItemBinding> {
   public HelpCenterAdapter(Activity context) {
        super(context);
    }


    @NonNull
    @Override
    public BindingHolder<HelpCenterItemBinding> onCreateViewHolder(ViewGroup parent, int viewType) {

        HelpCenterItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.help_center_item, parent, false);

        BindingHolder<HelpCenterItemBinding> holder = new BindingHolder<>(binding.getRoot());
        holder.setBinding(binding);

        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(BindingHolder<HelpCenterItemBinding> holder, int position) {
        super.onBindViewHolder(holder, position);
        HelpCenterItemBinding binding = holder.getBinding();
        HelpCenterTo mode = mList.get(position);
        binding.name.setText(mode.getCate_name());
        disPlayImage(binding.image,mode.getCate_img());
        if (mode.getList2()!=null&&mode.getList2().size()>0)
        binding.titleName.setText(mode.getList2().get(0).getDoc_title());
        if (mode.getList2()!=null&&mode.getList2().size()>1)
            binding.titleDes.setText(mode.getList2().get(1).getDoc_title());
    }




}
