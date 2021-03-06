
package com.xmkj.washmall.mall.adapter;

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
import com.xmkj.washmall.base.util.DateUtil;
import com.xmkj.washmall.databinding.CouponItemBinding;

import java.math.BigDecimal;

import hzxmkuar.com.applibrary.domain.myself.CouponTo;

/**
 * Created by Administrator on 2018/8/28.
 **/

public class SelectCouponAdapter extends BaseAdapter<CouponTo, CouponItemBinding> {
    private int type;
   public SelectCouponAdapter(Activity context, int type) {
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
//        binding.time.setText(DateUtil.formatDateString2(DateUtil.mFormatDateString,new BigDecimal(mode.getStart_time()).toPlainString())+"-"+DateUtil.formatDateString2(DateUtil.mFormatDateString,new BigDecimal(mode.getEnd_time()).toPlainString()));
      binding.time.setText(DateUtil.longToString(Long.valueOf(new BigDecimal(mode.getStart_time()).toPlainString())*1000,DateUtil.mFormatDateString)+"-"+DateUtil.longToString(Long.valueOf(new BigDecimal(mode.getEnd_time()).toPlainString())*1000,DateUtil.mFormatDateString));
        binding.name.setText(mode.getCate_name());
        binding.money.setText(mode.getAmount());
        if (mode.getAmount()!=null&&mode.getAmount().length()>0&&Double.valueOf(mode.getAmount()).intValue()>=1)
            binding.money.setText(Double.valueOf(mode.getAmount()).intValue()+"");
        else
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
