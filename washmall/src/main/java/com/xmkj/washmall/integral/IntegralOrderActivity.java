package com.xmkj.washmall.integral;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.WashAlertDialog;
import com.xmkj.washmall.databinding.ConfirmOrderGoodsItemBinding;
import com.xmkj.washmall.integral.presenter.IntegralOrderPresenter;
import com.xmkj.washmall.mall.AddressActivity;
import com.xmkj.washmall.mall.SelectPayActivity;
import com.xmkj.washmall.mall.presenter.ConfirmOrderPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hzxmkuar.com.applibrary.domain.integral.IntegralOrderInfoTo;
import hzxmkuar.com.applibrary.domain.order.ConfirmOrderInfoTo;
import hzxmkuar.com.applibrary.domain.order.OrderResultTo;

/**
 * Created by Administrator on 2018/12/27.
 */

public class IntegralOrderActivity extends BaseActivity {
    @BindView(R.id.contact_name)
    TextView contactName;
    @BindView(R.id.phone_number)
    TextView phoneNumber;
    @BindView(R.id.detail_address)
    TextView detailAddress;

    @BindView(R.id.express)
    TextView express;
    @BindView(R.id.remark)
    EditText remark;
    @BindView(R.id.goods_money)
    TextView goodsMoney;
    @BindView(R.id.express_money)
    TextView expressMoney;
    @BindView(R.id.all_money)
    TextView allMoney;
    @BindView(R.id.goods_layout)
    GridLayout goodsLayout;
    private IntegralOrderInfoTo mode;
    private IntegralOrderPresenter presenter;
    private int addressId;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integral_order);
        ButterKnife.bind(this);
        setTitleName("确定订单");
        presenter = new IntegralOrderPresenter(this);

    }

    @SuppressLint("SetTextI18n")
    private void setView() {
         setGoodsLayout();
        setAddressLayout();
        setSettlement();

    }

    @SuppressLint("SetTextI18n")
    public void setGoodsLayout() {

            IntegralOrderInfoTo.GoodsListBean goodsTo = mode.getGoods_list();
            View mView=View.inflate(appContext,R.layout.confirm_order_goods_item,null);
            ConfirmOrderGoodsItemBinding bind= DataBindingUtil.bind(mView);
            displayImage( bind.goodsImage, goodsTo.getGoods_image());
            bind.goodsName.setText(goodsTo.getGoods_name());
            bind. specification.setText(goodsTo.getSpec_name());
            bind.goodsNum.setText("x"+goodsTo.getGoods_num());
            bind. price.setText( "积分"+goodsTo.getGoods_price());

            goodsLayout.addView(mView);

    }

    @SuppressLint("SetTextI18n")
    private void setAddressLayout(){
        IntegralOrderInfoTo.AddressInfoBean addressInfo = mode.getAddress_info();
        if (addressInfo!=null&&addressInfo.getAddress_id()!=0){
          detailAddress.setText("收货地址："+addressInfo.getFinal_address());
            phoneNumber.setText(addressInfo.getTelephone());
            contactName.setText(addressInfo.getConsignee());
            addressId=addressInfo.getAddress_id();
      }
    }

    @SuppressLint("SetTextI18n")
    private void setSettlement(){
        IntegralOrderInfoTo.SettlementInfoBean settlementInfo = mode.getSettlement_info();
        if (settlementInfo!=null){
              express.setText(settlementInfo.getDistribution());

            allMoney.setText(settlementInfo.getOrder_amount()+"分");
        }
    }

    @OnClick({R.id.contact_layout, R.id.confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.contact_layout:
                Intent intent = new Intent(appContext, AddressActivity.class);
                startActivityForResult(intent,10);
                goToAnimation(1);
                break;
            case R.id.confirm:
                if (addressId==0){
                    showMessage("请选择地址");
                    return;
                }
                presenter.addOrder(remark.getText().toString(),addressId);
                break;
        }
    }

    @Override
    public void loadDataSuccess(Object data) {
        mode = new Gson().fromJson(JSON.toJSONString(data), IntegralOrderInfoTo.class);

        setView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==10){
           addressId=data.getIntExtra("AddressId",0);
        }
    }

    @Override
    protected void submitDataSuccess(Object data) {
        OrderResultTo resultTo=new Gson().fromJson(JSON.toJSONString(data),OrderResultTo.class);

        WashAlertDialog.show(this,"提示","兑换成功！ 请耐心等待商品的到来！","立即前往").setOnClickListener(view -> {
            WashAlertDialog.dismiss();
            Intent intent=new Intent(appContext, IntegralDetailActivity.class);
//            intent.putExtra("OrderId",resultTo.getOrder_id()+"");
            startActivity(intent);
            goToAnimation(1);
        });

    }

    private void showSuccessDialog(){


    }
}
