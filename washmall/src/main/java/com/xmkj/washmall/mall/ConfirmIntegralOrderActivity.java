package com.xmkj.washmall.mall;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.makeramen.roundedimageview.RoundedImageView;
import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.util.PingFangTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hzxmkuar.com.applibrary.domain.mall.OrderInfoTo;

/**
 * Created by Administrator on 2018/12/27.
 */

public class ConfirmIntegralOrderActivity extends BaseActivity {
    @BindView(R.id.contact_name)
    TextView contactName;
    @BindView(R.id.phone_number)
    TextView phoneNumber;
    @BindView(R.id.detail_address)
    TextView detailAddress;
    @BindView(R.id.goods_image)
    RoundedImageView goodsImage;
    @BindView(R.id.goods_name)
    PingFangTextView goodsName;
    @BindView(R.id.specification)
    TextView specification;
    @BindView(R.id.goods_num)
    TextView goodsNum;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.express)
    TextView express;
    @BindView(R.id.remark)
    EditText remark;
    @BindView(R.id.all_money)
    TextView allMoney;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        ButterKnife.bind(this);
        setTitleName("确定订单");
        setView();
    }

    @SuppressLint("SetTextI18n")
    private void setView() {
        OrderInfoTo mode = new OrderInfoTo();
        displayImage(goodsImage, mode.getImageUrl());
        goodsName.setText(mode.getGoodsName());
        specification.setText(mode.getSpecification());
        goodsNum.setText(mode.getNum());
        express.setText(mode.getExpress());
        price.setText("￥" + mode.getMoney());

        allMoney.setText(mode.getIntegral());
        contactName.setText(mode.getContactTo().getContactName());
        phoneNumber.setText(mode.getContactTo().getPhone());
        detailAddress.setText("收货地址：" + mode.getContactTo().getDetailAddress());
    }

    @OnClick({R.id.contact_layout, R.id.confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.contact_layout:
                Intent intent=new Intent(appContext,AddressActivity.class);
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.confirm:
                exchangeDialog();
                break;
        }
    }

    private void exchangeDialog(){
        NiftyDialogBuilder dialog=NiftyDialogBuilder.getInstance(this);
        dialog.setContentView(R.layout.dialog_integral_exchange);
        dialog.findViewById(R.id.cancel).setOnClickListener(v -> dialog.dismiss());
        dialog.findViewById(R.id.parent).setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }
}
