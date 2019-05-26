package com.xmkj.washmall.mall;

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
import com.xmkj.washmall.databinding.ConfirmOrderGoodsItemBinding;
import com.xmkj.washmall.mall.presenter.ConfirmOrderPresenter;
import com.xmkj.washmall.myself.MallOrderActivity;
import com.xmkj.washmall.myself.MyOrderActivity;
import com.xmkj.washmall.wash.SelectWashActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hzxmkuar.com.applibrary.domain.mall.OrderInfoTo;
import hzxmkuar.com.applibrary.domain.order.AddressTo;
import hzxmkuar.com.applibrary.domain.order.ConfirmOrderInfoTo;
import hzxmkuar.com.applibrary.domain.order.OrderResultTo;

/**
 * Created by Administrator on 2018/12/27.
 */

public class ConfirmOrderActivity extends BaseActivity {
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
    private ConfirmOrderInfoTo mode;
    private ConfirmOrderPresenter presenter;
    private int addressId;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        ButterKnife.bind(this);
        setTitleName("确定订单");
        presenter = new ConfirmOrderPresenter(this);

    }

    @SuppressLint("SetTextI18n")
    private void setView() {
         setGoodsLayout();
        setAddressLayout();
        setSettlement();

    }

    @SuppressLint("SetTextI18n")
    public void setGoodsLayout() {
        for (int i = 0; i < mode.getGoods_list().size(); i++) {
            ConfirmOrderInfoTo.GoodsListBean goodsTo = mode.getGoods_list().get(i);
            View mView=View.inflate(appContext,R.layout.confirm_order_goods_item,null);
            ConfirmOrderGoodsItemBinding bind= DataBindingUtil.bind(mView);
                    displayImage( bind.goodsImage, goodsTo.getSpec_image());
            bind.goodsName.setText(goodsTo.getGoods_name());
            bind. specification.setText(goodsTo.getSpec_name());
            bind.goodsNum.setText("x"+goodsTo.getGoods_num());
            bind. price.setText("￥" + goodsTo.getGoods_price());

            goodsLayout.addView(mView);
        }
    }

    @SuppressLint("SetTextI18n")
    private void setAddressLayout(){
        ConfirmOrderInfoTo.AddressInfoBean addressInfo = mode.getAddress_info();
        if (addressInfo!=null&&addressInfo.getAddress_id()!=0){
          detailAddress.setText("收货地址："+addressInfo.getFinal_address());
            phoneNumber.setText(addressInfo.getTelephone());
            contactName.setText(addressInfo.getConsignee());
            addressId=addressInfo.getAddress_id();
      }
    }

    @SuppressLint("SetTextI18n")
    private void setSettlement(){
        ConfirmOrderInfoTo.SettlementInfoBean settlementInfo = mode.getSettlement_info();
        if (settlementInfo!=null){
              express.setText(settlementInfo.getDistribution());
            expressMoney.setText("+"+settlementInfo.getExpress_amount());
            goodsMoney.setText("￥"+settlementInfo.getGoods_amount());
            allMoney.setText("￥"+settlementInfo.getOrder_amount());
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
                presenter.addOrder(addressId,remark.getText().toString());
                break;
        }
    }

    @Override
    public void loadDataSuccess(Object data) {
        mode = new Gson().fromJson(JSON.toJSONString(data), ConfirmOrderInfoTo.class);

        setView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==10){
            AddressTo addressTo= (AddressTo) data.getSerializableExtra("AddressTo");
            addressId=addressTo.getId();
            detailAddress.setText("收货地址："+addressTo.getAddress());
            phoneNumber.setText(addressTo.getTelephone());
            contactName.setText(addressTo.getConsignee());
        }
    }

    @Override
    protected void submitDataSuccess(Object data) {
        OrderResultTo resultTo=new Gson().fromJson(JSON.toJSONString(data),OrderResultTo.class);

        Intent intent=new Intent(appContext, SelectPayActivity.class);
        intent.putExtra("OrderResultTo",resultTo);
        startActivity(intent);
        goToAnimation(1);
    }
}
