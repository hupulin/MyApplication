
package com.xmkj.washmall.integral.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.xmkj.washmall.R;
import com.xmkj.washmall.base.adapter.BaseAdapter;
import com.xmkj.washmall.base.adapter.BindingHolder;
import com.xmkj.washmall.databinding.IntegralOrderItemBinding;
import com.xmkj.washmall.databinding.MallOrderGoodsItemBinding;
import com.xmkj.washmall.databinding.MyOrderMallItemBinding;

import java.util.ArrayList;
import java.util.List;

import hzxmkuar.com.applibrary.domain.integral.IntegralOrderListTo;
import hzxmkuar.com.applibrary.domain.order.MallOrderTo;

/**
 * Created by Administrator on 2018/8/28.
 **/

public class IntegralOrderAdapter extends BaseAdapter<IntegralOrderListTo, IntegralOrderItemBinding> {
  private int type;
   public IntegralOrderAdapter(Activity context,int type) {
        super(context);
       this.type=type;
    }


    @NonNull
    @Override
    public BindingHolder<IntegralOrderItemBinding> onCreateViewHolder(ViewGroup parent, int viewType) {

        IntegralOrderItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.integral_order_item, parent, false);

        BindingHolder<IntegralOrderItemBinding> holder = new BindingHolder<>(binding.getRoot());
        holder.setBinding(binding);

        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(BindingHolder<IntegralOrderItemBinding> holder, int position) {
        super.onBindViewHolder(holder, position);
        IntegralOrderItemBinding binding = holder.getBinding();
        IntegralOrderListTo mode=mList.get(position);

        binding.orderMoney.setText("￥ "+mode.getTotal_amount());

        binding.remark.setText(mode.getRemarks());
        binding.orderNumber.setText(mode.getOrder_sn());
        binding.statue.setText(mode.getStatus_txt());
        setGoodsLayout(binding.goodsLayout,mode);
//        binding.pay.setText(mode.getPayStr());
        binding.pay.setVisibility(type!=2? View.GONE:View.VISIBLE);



    }
      private void setGoodsLayout(GridLayout goodsLayout, IntegralOrderListTo goods){
          goodsLayout.removeAllViews();
          List<IntegralOrderListTo>goodsList=new ArrayList<>();
          goodsList.add(goods);
          for (int i=0;i<goodsList.size();i++) {
              IntegralOrderListTo goodsTo = goodsList.get(i);
              View mView = View.inflate(mContext, R.layout.mall_order_goods_item, null);
              MallOrderGoodsItemBinding bind=DataBindingUtil.bind(mView);
              bind.goodsName.setText(goodsTo.getGoods_name());
              bind.goodsNum.setText("x1");
//              bind.specification.setText(goodsTo.get());
              disPlayRoundImage(bind.orderImage,goodsTo.getGoods_image());

              goodsLayout.addView(mView);
          }
      }

}
