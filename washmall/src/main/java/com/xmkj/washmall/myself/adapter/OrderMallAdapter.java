
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
        MallOrderTo mode = mList.get(position);

        binding.orderMoney.setText("￥ " + mode.getTotal_amount());

        binding.remark.setText(mode.getRemarks());
        binding.orderNumber.setText(mode.getOrder_sn());
        binding.statue.setText(mode.getStatus_txt());
        setGoodsLayout(binding.goodsLayout, mode.getGoods_list());
        binding.pay.setVisibility(mode.getButton_list().getFk_btn() == 1 ? View.VISIBLE : View.GONE);
        binding.cancel.setVisibility(mode.getButton_list().getQxdd_btn() == 1 ? View.VISIBLE : View.GONE);
        binding.send.setVisibility(mode.getButton_list().getCfh_btn() == 1 ? View.VISIBLE : View.GONE);
        binding.confirmReceiver.setVisibility(mode.getButton_list().getQrsh_btn() == 1 ? View.VISIBLE : View.GONE);
        binding.evaluate.setVisibility(mode.getButton_list().getQpj_btn() == 1 ? View.VISIBLE : View.GONE);

        binding.pay.setOnClickListener(view -> {
            if (listener!=null)
                listener.pay(mode);
        });
        binding.send.setOnClickListener(view -> {
            if (listener!=null)
                listener.send(mode);
        });

        binding.confirmReceiver.setOnClickListener(view -> {
            if (listener!=null)
                listener.confirmReceiver(mode);
        });

        binding.cancel.setOnClickListener(view -> {
            if (listener!=null)
                listener.cancelOrder(mode);
        });
        binding.evaluate.setOnClickListener(view -> {
            if (listener!=null)
                listener.evaluate(mode);
        });
    }

    private void setGoodsLayout(GridLayout goodsLayout, List<MallOrderTo.GoodsListBean> goodsList) {
        goodsLayout.removeAllViews();
        for (int i = 0; i < goodsList.size(); i++) {
            MallOrderTo.GoodsListBean goodsTo = goodsList.get(i);
            View mView = View.inflate(mContext, R.layout.mall_order_goods_item, null);
            MallOrderGoodsItemBinding bind = DataBindingUtil.bind(mView);
            bind.goodsName.setText(goodsTo.getGoods_name());
            bind.goodsNum.setText("x" + goodsTo.getGoods_num());
            bind.specification.setText(goodsTo.getSpec_name());
            disPlayImage(bind.orderImage, goodsTo.getSpec_image());

            goodsLayout.addView(mView);
        }
    }

    public interface  OrderMallAdapterListener{

        void pay(MallOrderTo mode);

        void send(MallOrderTo mode);

        void confirmReceiver(MallOrderTo mode);

        void cancelOrder(MallOrderTo mode);

        void evaluate(MallOrderTo mode);


    }

    private OrderMallAdapterListener listener;

    public void setOrderMallAdapterListener(OrderMallAdapterListener listener){
        this.listener=listener;
    }

}
