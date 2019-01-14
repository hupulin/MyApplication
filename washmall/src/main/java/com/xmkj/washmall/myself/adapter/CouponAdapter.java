
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
import com.xmkj.washmall.databinding.MyCollectItemBinding;

import hzxmkuar.com.applibrary.domain.car.GoodsCarTo;
import hzxmkuar.com.applibrary.domain.myself.CouponTo;

/**
 * Created by Administrator on 2018/8/28.
 **/

public class CouponAdapter extends BaseAdapter<CouponTo, CouponItemBinding> {
    private int type;
   public CouponAdapter(Activity context,int type) {
        super(context);
       this.type=type;
    }


    @NonNull
    @Override
    public BindingHolder<CouponItemBinding> onCreateViewHolder(ViewGroup parent, int viewType) {

        CouponItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.coupon_item, parent, false);

        BindingHolder<CouponItemBinding> holder = new BindingHolder<>(binding.getRoot());
        holder.setBinding(binding);

        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(BindingHolder<CouponItemBinding> holder, int position) {
        super.onBindViewHolder(holder, position);
        CouponItemBinding binding = holder.getBinding();
        CouponTo mode=mList.get(position);
        binding.time.setText(mode.getEnd_time()+"");
        binding.name.setText(mode.getCate_name());
        binding.money.setText(mode.getAmount());
        binding.couponIcon.setBackgroundResource(type==2?R.drawable.already_use_icon:R.drawable.out_time_icon);
        binding.couponIcon.setVisibility(type==1? View.GONE:View.VISIBLE);
        setTextViewStyles(binding.name);

    }
    private void setTextViewStyles(TextView text){
        LinearGradient mLinearGradient =new LinearGradient(0, 0, 0, text.getPaint().getTextSize(), Color.parseColor("#CF67FF"), Color.parseColor("#6B69FE"), Shader.TileMode.CLAMP);
        text.getPaint().setShader(mLinearGradient);
        text.invalidate();
    }




}
