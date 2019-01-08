
package com.xmkj.washmall.car.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.xmkj.washmall.R;
import com.xmkj.washmall.base.adapter.BaseAdapter;
import com.xmkj.washmall.base.adapter.BindingHolder;
import com.xmkj.washmall.databinding.CarItemBinding;

import hzxmkuar.com.applibrary.domain.car.GoodsCarTo;



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
        binding.goodsName.setText(mode.getGoods_name());
        disPlayImage(binding.goodsImage,mode.getSpec_image());
        binding.price.setText(mode.getGoods_price());
        binding.specification.setText(mode.getSpec_name());
        binding.purchaseNum.setText(mode.getGoods_num()+"");
        binding.select.setBackgroundResource(mode.isSelect()?R.drawable.address_select:R.drawable.address_un_select);
        binding.reduce.setOnClickListener(v -> {
            if (listener!=null) {
                if (mode.getGoods_num()<=1){
                    Toast.makeText(mContext,"数量不能少于1",Toast.LENGTH_LONG).show();
                    return;
                }
                listener.modifyNum(mode, mode.getGoods_num() - 1);
            }
        });

        binding.add.setOnClickListener(v -> {
            if (listener!=null) {

                listener.modifyNum(mode, mode.getGoods_num() + 1);
            }
        });

        binding.select.setOnClickListener(v -> {
            mode.setSelect(!mode.isSelect());
            binding.select.setBackgroundResource(mode.isSelect()?R.drawable.address_select:R.drawable.address_un_select);
            if (listener!=null)
                listener.selectClick(mode);
        });
    }

    public interface CarModifyListener{
        void modifyNum(GoodsCarTo mode,int goodsNum);

        void selectClick(GoodsCarTo mode);
    }

    public CarModifyListener listener;

    public void setCarModifyListener(CarModifyListener listener){
        this.listener=listener;
    }

}
