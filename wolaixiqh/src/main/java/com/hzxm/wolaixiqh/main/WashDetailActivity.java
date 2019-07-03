package com.hzxm.wolaixiqh.main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;


import com.hzxm.wolaixiqh.R;
import com.hzxm.wolaixiqh.base.BaseActivity;
import com.hzxm.wolaixiqh.main.present.WashDetailPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import hzxmkuar.com.applibrary.domain.wash.WashDetailTo;
import rx.Observable;

/**
 * Created by 1ONE on 2019/5/28.
 */

public class WashDetailActivity extends BaseActivity {

    @BindView(R.id.wash_layout)
    GridLayout washLayout;
    @BindView(R.id.money)
    TextView money;
    @BindView(R.id.order_num)
    TextView orderNum;
    @BindView(R.id.create_time)
    TextView createTime;
    @BindView(R.id.save_wardrobe)
    TextView saveWardrobe;
    @BindView(R.id.save_address)
    TextView saveAddress;
    @BindView(R.id.pick_time)
    TextView pickTime;
    @BindView(R.id.pickup_wardrobe)
    TextView pickupWardrobe;
    @BindView(R.id.pick_address)
    TextView pickAddress;
    @BindView(R.id.contact_name)
    TextView contactName;
    @BindView(R.id.contact_phone)
    TextView contactPhone;
    private WashDetailTo mode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wash_detail);
        ButterKnife.bind(this);
        setTitleName("订单详情");
        WashDetailPresenter presenter = new WashDetailPresenter(this);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void loadDataSuccess(Object data) {
        mode = (WashDetailTo) data;
        money.setText("￥" + mode.getOrder_info().getOrder_amount());
        orderNum.setText("订单编号：" + mode.getOrder_info().getOrder_sn());
        createTime.setText("下单时间： " + mode.getOrder_info().getOrder_time());
        saveWardrobe.setText("预约衣柜： " + mode.getOrder_info().getDeposit_wardrobe_name());
        saveAddress.setText("存柜地点：" + mode.getOrder_info().getDeposit_address());
        pickTime.setText("预约取回时间：" + mode.getOrder_info().getPickup_time());
        pickupWardrobe.setText("预约取柜：" + mode.getOrder_info().getDelivery_wardrobe_name());
        pickAddress.setText("取柜地点：" + mode.getOrder_info().getDelivery_address());
        contactPhone.setText("联系电话：" + mode.getCustomer_info().getTelphone());
        contactName.setText("联系人：" + mode.getCustomer_info().getCustomer());

        Observable.from(mode.getGoods_list()).subscribe(goods_listEntity -> {
            View mView=View.inflate(appContext,R.layout.wash_detail_item,null);
            ((TextView)mView.findViewById(R.id.name)).setText(goods_listEntity.getService_name());
            ((TextView)mView.findViewById(R.id.num)).setText("x"+goods_listEntity.getService_num());
            ((TextView)mView.findViewById(R.id.money)).setText("小计：¥"+goods_listEntity.getTotal_price());
            washLayout.addView(mView);
        });
    }
}
