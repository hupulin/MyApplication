
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
import hzxmkuar.com.applibrary.domain.order.AddressTo;

/**
 * Created by Administrator on 2018/8/28.
 **/

public class AddressAdapter extends BaseAdapter<AddressTo, AddressItemBinding> {
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
        AddressTo mode=mList.get(position);
        binding.contactName.setText(mode.getConsignee());
        binding.phoneNum.setText(mode.getTelephone());
        binding.detailAddress.setText(mode.getFinaladdress());
        binding.defaultIcon.setBackgroundResource(mode.getIs_default()==1?R.drawable.address_select:R.drawable.address_un_select);

        binding.defaultLayout.setOnClickListener(v -> {
            if (listener!=null)
                listener.setDefaultAddress(mode);
        });

        binding.deleteLayout.setOnClickListener(v -> {
            if (listener!=null)
                listener.deleteAddress(mode);
        });

        binding.editLayout.setOnClickListener(v -> {
            if (listener!=null)
                listener.editAddress(mode);
        });
    }

    public interface AddressClickListener{
        void deleteAddress(AddressTo mode);

        void editAddress(AddressTo mode);

        void setDefaultAddress(AddressTo mode);
    }

    public AddressClickListener listener;

    public void setAddressListener(AddressClickListener listener){
        this.listener=listener;
    }

}
