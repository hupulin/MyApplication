
package com.xmkj.washmall.message.adapter;

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
import com.xmkj.washmall.databinding.SystemMessageItemBinding;

import hzxmkuar.com.applibrary.domain.message.SystemMessageTo;
import hzxmkuar.com.applibrary.domain.order.MallOrderTo;

/**
 * Created by Administrator on 2018/8/28.
 **/

public class MessageCenterAdapter extends BaseAdapter<SystemMessageTo, SystemMessageItemBinding> {
    public MessageCenterAdapter(Activity context) {
        super(context);
    }


    @NonNull
    @Override
    public BindingHolder<SystemMessageItemBinding> onCreateViewHolder(ViewGroup parent, int viewType) {

        SystemMessageItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.system_message_item, parent, false);

        BindingHolder<SystemMessageItemBinding> holder = new BindingHolder<>(binding.getRoot());
        holder.setBinding(binding);

        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(BindingHolder<SystemMessageItemBinding> holder, int position) {
        super.onBindViewHolder(holder, position);
        SystemMessageItemBinding binding = holder.getBinding();
        SystemMessageTo mode = mList.get(position);
        binding.title.setText(mode.getMsg_title());
        binding.content.setText(mode.getMsg_desc());
        binding.time.setText(mode.getDateline());
        binding.read.setVisibility(mode.getIs_read()==1?View.GONE:View.VISIBLE);

    }


}
