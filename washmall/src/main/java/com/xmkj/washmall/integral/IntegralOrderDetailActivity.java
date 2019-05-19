package com.xmkj.washmall.integral;

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
import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.databinding.ConfirmOrderGoodsItemBinding;
import com.xmkj.washmall.integral.presenter.IntegralOrderDetailPresenter;
import com.xmkj.washmall.mall.AddressActivity;
import com.xmkj.washmall.mall.SelectPayActivity;
import com.xmkj.washmall.myself.presenter.OrderDetailPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hzxmkuar.com.applibrary.domain.integral.IntegralOrderDetailTo;
import hzxmkuar.com.applibrary.domain.mall.OrderDetailTo;
import hzxmkuar.com.applibrary.domain.order.OrderResultTo;

/**
 * Created by Administrator on 2018/12/27.
 */

public class IntegralOrderDetailActivity extends BaseActivity {
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

    @BindView(R.id.order_num)
    TextView orderNum;
    @BindView(R.id.create_time)
    TextView createTime;
    @BindView(R.id.pay_time)
    TextView payTime;
    @BindView(R.id.send_time)
    TextView sendTime;

    private IntegralOrderDetailTo mode;
    private IntegralOrderDetailPresenter presenter;
    private int addressId;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integral_order_detail);
        ButterKnife.bind(this);
        setTitleName("订单详情");
        presenter = new IntegralOrderDetailPresenter(this);

    }

    @SuppressLint("SetTextI18n")
    private void setView() {


  remark.setText(mode.getRemarks());

        setGoodsLayout();
        setAddressLayout();
        setSettlement();
        setInfoView();

    }



    @SuppressLint("SetTextI18n")
    public void setGoodsLayout() {


        View mView = View.inflate(appContext, R.layout.confirm_order_goods_item, null);
        ConfirmOrderGoodsItemBinding bind = DataBindingUtil.bind(mView);
        displayImage(bind.goodsImage, mode.getGoods_image());
        bind.goodsName.setText(mode.getGoods_name());
        bind.specification.setText(mode.getGoods_name());
        bind.goodsNum.setText("x1" );
        bind.price.setText("积分" + mode.getTotal_amount());
       goodsMoney.setText( mode.getTotal_amount()+"");

        goodsLayout.addView(mView);

    }

    @SuppressLint("SetTextI18n")
    private void setAddressLayout() {
        detailAddress.setText("收货地址：" + mode.getAddress());
        phoneNumber.setText(mode.getTelephone());
        contactName.setText(mode.getConsignee());

    }

    @SuppressLint("SetTextI18n")
    private void setSettlement() {

            express.setText(mode.getDistribution());


    }

    @OnClick({R.id.contact_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.contact_layout:
                Intent intent = new Intent(appContext, AddressActivity.class);
                startActivityForResult(intent, 10);
                goToAnimation(1);
                break;
            case R.id.confirm:
                if (addressId == 0) {
                    showMessage("请选择地址");
                    return;
                }
//                presenter.addOrder(remark.getText().toString(),addressId);
                break;
        }
    }

    @Override
    public void loadDataSuccess(Object data) {
        mode = new Gson().fromJson(JSON.toJSONString(data), IntegralOrderDetailTo.class);

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
       orderNum.setText("订单编号:        "+mode.getOrder_sn());
        createTime.setText("创建时间:        "+mode.getAdd_time());
        payTime.setText("付款时间:        "+mode.getPay_time());
        sendTime.setText("发货时间:        "+mode.getSend_time());

       payTime.setVisibility(TextUtils.isEmpty(mode.getPay_time())?View.GONE:View.VISIBLE);
       sendTime.setVisibility(TextUtils.isEmpty(mode.getSend_time())?View.GONE:View.VISIBLE);
    }
}
