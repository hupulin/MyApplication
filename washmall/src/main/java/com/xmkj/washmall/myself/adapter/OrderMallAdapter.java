
package com.xmkj.washmall.myself.adapter;

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
import com.xmkj.washmall.databinding.MallOrderGoodsItemBinding;
import com.xmkj.washmall.databinding.MyOrderMallItemBinding;

import java.util.List;

import hzxmkuar.com.applibrary.domain.order.MallOrderTo;

/**
 * Created by Administrator on 2018/8/28.
 **/

public class OrderMallAdapter extends BaseAdapter<MallOrderTo, MyOrderMallItemBinding> {
   public OrderMallAdapter(Activity context) {
        super(context);
    }


    @NonNull
    @Override
    public BindingHolder<MyOrderMallItemBinding> onCreateViewHolder(ViewGroup parent, int viewType) {

        MyOrderMallItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.my_order_mall_item, parent, false);

        BindingHolder<MyOrderMallItemBinding> holder = new BindingHolder<>(binding.getRoot());
        holder.setBinding(binding);

        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(BindingHolder<MyOrderMallItemBinding> holder, int position) {
        super.onBindViewHolder(holder, position);
        MyOrderMallItemBinding binding = holder.getBinding();
        MallOrderTo mode=mList.get(position);

        binding.orderMoney.setText("￥ "+mode.getTotal_amount());

        binding.remark.setText(mode.getRemarks());
        binding.orderNumber.setText(mode.getOrder_sn());
        binding.statue.setText(mode.getStatus_txt());
        setGoodsLayout(binding.goodsLayout,mode.getGoods_list());
        binding.pay.setText(mode.getPayStr());
        binding.pay.setVisibility(mode.getNew_status()==4? View.GONE:View.VISIBLE);



    }
      private void setGoodsLayout(GridLayout goodsLayout, List<MallOrderTo.GoodsListBean>goodsList){
          goodsLayout.removeAllViews();
          for (int i=0;i<goodsList.size();i++) {
              MallOrderTo.GoodsListBean goodsTo = goodsList.get(i);
              View mView = View.inflate(mContext, R.layout.mall_order_goods_item, null);
              MallOrderGoodsItemBinding bind=DataBindingUtil.bind(mView);
              bind.goodsName.setText(goodsTo.getGoods_name());
              bind.goodsNum.setText("x"+goodsTo.getGoods_num());
              bind.specification.setText(goodsTo.getSpec_name());
//              disPlayRoundImage(bind.orderImage,goodsTo.);

              goodsLayout.addView(mView);
          }
      }

}
