
package com.xmkj.washmall.mall.adapter;

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
import com.xmkj.washmall.databinding.MallGoodsItemBinding;

import hzxmkuar.com.applibrary.domain.main.ContactTo;
import hzxmkuar.com.applibrary.domain.main.MallGoodsTo;
import hzxmkuar.com.applibrary.domain.mall.AddressTo;

/**
 * Created by Administrator on 2018/8/28.
 **/

public class AddressAdapter extends BaseAdapter<ContactTo, AddressItemBinding> {
   public AddressAdapter(Activity context) {
        super(context);
    }


    @NonNull
    @Override
    public BindingHolder<AddressItemBinding> onCreateViewHolder(ViewGroup parent, int viewType) {

        AddressItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.address_item, parent, false);

        BindingHolder<AddressItemBinding> holder = new BindingHolder<>(binding.getRoot());
        holder.setBinding(binding);

        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(BindingHolder<AddressItemBinding> holder, int position) {
        super.onBindViewHolder(holder, position);
        AddressItemBinding binding = holder.getBinding();
        ContactTo mode=mList.get(position);
        binding.contactName.setText(mode.getContactName());
        binding.phoneNum.setText(mode.getPhone());
        binding.detailAddress.setText(mode.getDetailAddress());

    }


}
