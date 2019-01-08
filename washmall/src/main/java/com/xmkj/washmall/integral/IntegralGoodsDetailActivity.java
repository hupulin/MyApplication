package com.xmkj.washmall.integral;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.google.gson.Gson;
import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.util.PingFangTextView;
import com.xmkj.washmall.integral.presenter.IntegralGoodsDetailPresenter;
import com.xmkj.washmall.mall.ConfirmIntegralOrderActivity;
import com.xmkj.washmall.mall.ConfirmOrderActivity;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hzxmkuar.com.applibrary.domain.integral.IntegralGoodsDetailTo;
import hzxmkuar.com.applibrary.domain.mall.GoodsDetailTo;
import hzxmkuar.com.applibrary.domain.mall.SettlementIdTo;
import hzxmkuar.com.applibrary.domain.mall.SpecificationTo;
import rx.Observable;

/**
 * Created by Administrator on 2018/12/27.
 */

public class IntegralGoodsDetailActivity extends BaseActivity {
    @BindView(R.id.goods_image)
    ImageView goodsImage;
    @BindView(R.id.goods_price)
    PingFangTextView goodsPrice;
    @BindView(R.id.sale_num)
    TextView saleNum;
    @BindView(R.id.goods_name)
    PingFangTextView goodsName;
    @BindView(R.id.goods_des)
    TextView goodsDes;
    @BindView(R.id.goods_image_layout)
    GridLayout goodsImageLayout;
    private IntegralGoodsDetailTo mode;
    private NiftyDialogBuilder dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integral_goods_detail);
        ButterKnife.bind(this);
        setTitleName("蓝月亮洗衣液家庭装");
        IntegralGoodsDetailPresenter presenter=new IntegralGoodsDetailPresenter(this);
    }

    @SuppressLint("SetTextI18n")
    private void setView() {

        displayImage(goodsImage, mode.getGoods_image());
        goodsPrice.setText( mode.getGoods_integral()+"积分");
        saleNum.setText("销量：" + 100);
        goodsName.setText(mode.getGoods_name());
        goodsDes.setText(mode.getDesc());
        setImageLayout(mode.getGoods_content());

    }

    public void setImageLayout(List<GoodsDetailTo.GoodsContentBean> imageList){
        for (int i=0;i<imageList.size();i++) {
            ImageView imageView = new ImageView(appContext);
            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
            layoutParams.width = getScreenWidth();
            imageView.setLayoutParams(layoutParams);
            displayImage(imageView,imageList.get(i).getImg_url());
            goodsImageLayout.addView(imageView);
        }
    }


    @OnClick({R.id.confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.confirm:
                Intent intent=new Intent(appContext,IntegralOrderActivity.class);
                intent.putExtra("GoodsId",mode.getGoods_id());
                startActivity(intent);
                goToAnimation(1);
                break;

        }
    }



    @Override
    public void loadDataSuccess(Object data) {
        mode = new Gson().fromJson(JSON.toJSONString(data), IntegralGoodsDetailTo.class);
        setView();
    }


    @Override
    protected void submitDataSuccess(Object data) {
        dialog.dismiss();
        SettlementIdTo settlementIdTo=new Gson().fromJson(JSON.toJSONString(data),SettlementIdTo.class);
        Intent intent=new Intent(appContext,ConfirmOrderActivity.class);
        intent.putExtra("SettlementId",settlementIdTo.getSettlement_ids());
        startActivity(intent);
        goToAnimation(1);
    }



}
