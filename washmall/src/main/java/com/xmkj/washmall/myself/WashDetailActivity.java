package com.xmkj.washmall.myself;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import com.ruffian.library.RTextView;
import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.WashAlertDialog;
import com.xmkj.washmall.mall.SelectPayActivity;
import com.xmkj.washmall.myself.presenter.WashDetailPresenter;
import com.xmkj.washmall.wash.EvaluateWashActivity;
import com.xmkj.washmall.wash.ScanActivity;

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
    @BindView(R.id.cancel)
    RTextView cancel;
    @BindView(R.id.pay)
    RTextView pay;
    @BindView(R.id.evaluate)
    RTextView evaluate;
    @BindView(R.id.scan)
    RTextView scan;
    @BindView(R.id.save)
    RTextView save;
    private WashDetailTo mode;
    private WashDetailPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wash_detail);
        ButterKnife.bind(this);
        setTitleName("订单详情");
        presenter = new WashDetailPresenter(this);
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
            View mView = View.inflate(appContext, R.layout.wash_detail_item, null);
            ((TextView) mView.findViewById(R.id.name)).setText(goods_listEntity.getService_name());
            ((TextView) mView.findViewById(R.id.num)).setText("x" + goods_listEntity.getService_num());
            ((TextView) mView.findViewById(R.id.money)).setText("小计：¥" + goods_listEntity.getTotal_price());
            washLayout.addView(mView);
        });

        cancel.setVisibility(mode.getOrder_info().getButton_list().getQxdd_btn()==1?View.VISIBLE:View.GONE);
        pay.setVisibility(mode.getOrder_info().getButton_list().getPay_btn()==1?View.VISIBLE:View.GONE);
        scan.setVisibility(mode.getOrder_info().getButton_list().getSmqh_btn()==1?View.VISIBLE:View.GONE);
        save.setVisibility(mode.getOrder_info().getButton_list().getSmch_btn()==1?View.VISIBLE:View.GONE);
        evaluate.setVisibility(mode.getOrder_info().getButton_list().getQpj_btn()==1?View.VISIBLE:View.GONE);

        cancel.setOnClickListener(v -> WashAlertDialog.show(this,"提示","确定取消订单").setOnClickListener(view -> {
            WashAlertDialog.dismiss();
            presenter.cancelOrder(mode.getOrder_info().getOrder_id());
        }));

        pay.setOnClickListener(v -> {
            Intent intent=new Intent(appContext, SelectPayActivity.class);
            intent.putExtra("OrderId",mode.getOrder_info().getOrder_id());
            intent.putExtra("Money",mode.getOrder_info().getOrder_amount());
            intent.putExtra("IsWash",true);
            startActivity(intent);
            goToAnimation(1);
        });

        scan.setOnClickListener(v -> {
            Intent intent=new Intent(appContext, ScanActivity.class);
            intent.putExtra("OrderId",mode.getOrder_info().getOrder_id()+"");

            startActivity(intent);
            goToAnimation(1);
        });

        evaluate.setOnClickListener(v -> {
            Intent intent=new Intent(appContext, EvaluateWashActivity.class);
            intent.putExtra("OrderId",mode.getOrder_info().getOrder_id()+"");
            startActivity(intent);
            goToAnimation(1);
        });
        save.setOnClickListener(v -> {
            Intent intent=new Intent(appContext, ScanActivity.class);
            intent.putExtra("OrderId",mode.getOrder_info().getOrder_id()+"");
            startActivity(intent);
            goToAnimation(1);
        });

    }


}
