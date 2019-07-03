package com.xmkj.washmall.myself;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.ruffian.library.RTextView;
import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.WashAlertDialog;
import com.xmkj.washmall.databinding.ConfirmOrderGoodsItemBinding;
import com.xmkj.washmall.mall.AddressActivity;
import com.xmkj.washmall.mall.SelectPayActivity;
import com.xmkj.washmall.myself.presenter.OrderDetailPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hzxmkuar.com.applibrary.domain.mall.OrderDetailTo;
import hzxmkuar.com.applibrary.domain.order.OrderResultTo;
import rx.Observable;

/**
 * Created by Administrator on 2018/12/27.
 */

public class OrderDetailActivity extends BaseActivity {
    @BindView(R.id.contact_name)
    TextView contactName;
    @BindView(R.id.phone_number)
    TextView phoneNumber;
    @BindView(R.id.detail_address)
    TextView detailAddress;

    @BindView(R.id.express)
    TextView express;
    @BindView(R.id.remark)
    TextView remark;


    @BindView(R.id.goods_layout)
    GridLayout goodsLayout;
    @BindView(R.id.goods_money)
    TextView goodsMoney;
    @BindView(R.id.express_money)
    TextView expressMoney;
    @BindView(R.id.order_num)
    TextView orderNum;
    @BindView(R.id.create_time)
    TextView createTime;
    @BindView(R.id.pay_time)
    TextView payTime;
    @BindView(R.id.send_time)
    TextView sendTime;
    @BindView(R.id.pick_time)
    TextView pickTime;
    @BindView(R.id.cancel)
    RTextView cancel;
    @BindView(R.id.pay)
    RTextView pay;
    @BindView(R.id.send)
    RTextView send;
    @BindView(R.id.confirm_receiver)
    RTextView confirmReceiver;
    private OrderDetailTo mode;
    private OrderDetailPresenter presenter;
    private int addressId;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wash_order_detail);
        ButterKnife.bind(this);
        setTitleName("订单详情");
        presenter = new OrderDetailPresenter(this);

    }

    @SuppressLint("SetTextI18n")
    private void setView() {


        remark.setText(mode.getRemarks());

        setGoodsLayout();
        setAddressLayout();
        setSettlement();
        setInfoView();
        setButtonLayout();

    }

    private void setButtonLayout() {
        pay.setVisibility(mode.getButton_list().getFk_btn() == 1 ? View.VISIBLE : View.GONE);
        cancel.setVisibility(mode.getButton_list().getQxdd_btn() == 1 ? View.VISIBLE : View.GONE);
        send.setVisibility(mode.getButton_list().getCfh_btn() == 1 ? View.VISIBLE : View.GONE);
        confirmReceiver.setVisibility(mode.getButton_list().getQrsh_btn() == 1 ? View.VISIBLE : View.GONE);
    }


    @SuppressLint("SetTextI18n")
    public void setGoodsLayout() {
        Observable.from(mode.getGoods_list()).subscribe(goodsTo -> {
            View mView = View.inflate(appContext, R.layout.confirm_order_goods_item, null);
            ConfirmOrderGoodsItemBinding bind = DataBindingUtil.bind(mView);
            displayImage(bind.goodsImage, goodsTo.getSpec_image());
            bind.goodsName.setText(goodsTo.getGoods_name());
            bind.specification.setText(goodsTo.getSpec_name());
            bind.goodsNum.setText("x" + goodsTo.getGoods_num());
            bind.price.setText("￥" + goodsTo.getGoods_price());

            goodsLayout.addView(mView);
        });


    }

    @SuppressLint("SetTextI18n")
    private void setAddressLayout() {
        detailAddress.setText("收货地址：" + mode.getAddress());
        phoneNumber.setText(mode.getTelephone());
        contactName.setText(mode.getConsignee());

    }

    @SuppressLint("SetTextI18n")
    private void setSettlement() {
        OrderDetailTo.SettlementInfoBean settlementInfo = mode.getSettlement_info();
        if (settlementInfo != null) {
            express.setText(settlementInfo.getDistribution());
            expressMoney.setText("￥" + settlementInfo.getExpress_amount());

            goodsMoney.setText("￥" + settlementInfo.getGoods_amount());
        }
    }


    @Override
    public void loadDataSuccess(Object data) {
        mode = new Gson().fromJson(JSON.toJSONString(data), OrderDetailTo.class);

        setView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 10) {
            addressId = data.getIntExtra("AddressId", 0);
        }
    }

    @Override
    protected void submitDataSuccess(Object data) {
        OrderResultTo resultTo = new Gson().fromJson(JSON.toJSONString(data), OrderResultTo.class);

        Intent intent = new Intent(appContext, SelectPayActivity.class);
        intent.putExtra("OrderResultTo", resultTo);
        startActivity(intent);
        goToAnimation(1);
    }

    @SuppressLint("SetTextI18n")
    private void setInfoView() {
        orderNum.setText("订单编号:        " + mode.getOrder_sn());
        createTime.setText("创建时间:        " + mode.getAdd_time());
        payTime.setText("付款时间:        " + mode.getPay_time());
        sendTime.setText("发货时间:        " + mode.getSend_time());
        pickTime.setText("取货时间:        " + mode.getFinish_time());
        payTime.setVisibility(TextUtils.isEmpty(mode.getPay_time()) ? View.GONE : View.VISIBLE);
        sendTime.setVisibility(TextUtils.isEmpty(mode.getSend_time()) ? View.GONE : View.VISIBLE);
        pickTime.setVisibility(TextUtils.isEmpty(mode.getFinish_time()) ? View.GONE : View.VISIBLE);
    }

    @OnClick({R.id.cancel, R.id.pay, R.id.send, R.id.confirm_receiver})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cancel:
                WashAlertDialog.show(this,"提示","确认取消订单").setOnClickListener(view2 -> {
                    WashAlertDialog.dismiss();
                    presenter.cancel(mode.getOrder_id()+"");
                });
                break;
            case R.id.pay:
                Intent intent=new Intent(appContext, SelectPayActivity.class);
                intent.putExtra("OrderId",mode.getOrder_id());
                intent.putExtra("Money",mode.getSettlement_info().getOrder_amount());
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.send:
                WashAlertDialog.show(this,"提示","催发货").setOnClickListener(view2 -> {
                    WashAlertDialog.dismiss();
                    presenter.quickSend(mode.getOrder_id()+"");
                });
                break;
            case R.id.confirm_receiver:
                WashAlertDialog.show(this,"提示","确认收货").setOnClickListener(view2 -> {
                    WashAlertDialog.dismiss();
                    presenter.confirmReceiver(mode.getOrder_id()+"");
                });
                break;
        }
    }
}
