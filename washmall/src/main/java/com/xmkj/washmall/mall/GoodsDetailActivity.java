package com.xmkj.washmall.mall;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.google.gson.Gson;
import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.util.PingFangTextView;
import com.xmkj.washmall.mall.presenter.GoodsDetailPresenter;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hzxmkuar.com.applibrary.domain.main.MallGoodsTo;
import hzxmkuar.com.applibrary.domain.mall.GoodsDetailTo;
import hzxmkuar.com.applibrary.domain.mall.SettlementIdTo;
import hzxmkuar.com.applibrary.domain.mall.SpecificationTo;
import rx.Observable;

/**
 * Created by Administrator on 2018/12/27.
 */

public class GoodsDetailActivity extends BaseActivity {
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
    private GoodsDetailTo mode;
    private GoodsDetailPresenter presenter;
    private int selectPosition;
    private NiftyDialogBuilder dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        ButterKnife.bind(this);
        setTitleName(((MallGoodsTo) getIntent().getSerializableExtra("GoodsTo")).getGoods_name());

        presenter = new GoodsDetailPresenter(this);
    }

    @SuppressLint("SetTextI18n")
    private void setView() {

        displayImage(goodsImage, mode.getGoods_image());
        goodsPrice.setText("￥" + mode.getGoods_price());
        saleNum.setText("销量：" + 100);
        goodsName.setText(mode.getGoods_name());
        goodsDes.setText(mode.getGoods_desc());
        setImageLayout(mode.getGoods_content());

    }

    public void setImageLayout(List<GoodsDetailTo.GoodsContentBean>imageList){
        for (int i=0;i<imageList.size();i++) {
            ImageView imageView = new ImageView(appContext);
            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
            layoutParams.width = getScreenWidth();
            imageView.setLayoutParams(layoutParams);
            displayImage(imageView,imageList.get(i).getImg_url());
            goodsImageLayout.addView(imageView);
        }
    }


    @OnClick({R.id.select_type, R.id.custom_service, R.id.car, R.id.add_car, R.id.purchase})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.select_type:
               presenter.getGoodsSpecification();
                break;
            case R.id.custom_service:
                break;
            case R.id.car:
                break;
            case R.id.add_car:
                break;
            case R.id.purchase:
                break;
        }
    }

    @SuppressLint("SetTextI18n")
    public void specificationDialog(List<SpecificationTo> specificationList) {

        if (specificationList==null||specificationList.size()==0)
            return;
        dialog = NiftyDialogBuilder.getInstance(this);
        dialog.setContentView(R.layout.dialog_select_specification);
        TagFlowLayout specificationLayout = dialog.findViewById(R.id.specification_layout);
        List<TextView> tagList = new ArrayList<>();
        specificationLayout.setMaxSelectCount(1);
        TextView purchaseNum = dialog.findViewById(R.id.purchase_num);
        specificationLayout.setTag(specificationList.get(0).getSpec_id()+"");
        ((TextView) dialog.findViewById(R.id.specification_price)).setText("￥188.00");
        displayImage(dialog.findViewById(R.id.goods_image), specificationList.get(0).getSpec_image());
        specificationLayout.setAdapter(new TagAdapter<SpecificationTo>(specificationList) {
            @Override
            public View getView(FlowLayout parent, int position, SpecificationTo specificationTo) {
                View mView = View.inflate(appContext, R.layout.specification_tag_item, null);
                TextView tagName = mView.findViewById(R.id.name);
                tagName.setText(specificationTo.getSpec_name());
                tagList.add(tagName);
                return mView;
            }
        });
        specificationLayout.setOnTagClickListener((view, position, parent) -> {
            Observable.from(tagList).subscribe(textView -> {
                specificationLayout.setTag(specificationList.get(position).getSpec_id()+"");
                textView.setTextColor(Color.parseColor("#6868FF"));
                textView.setBackgroundResource(R.drawable.specification_un_select);
                selectPosition=position;
            });
            view.findViewById(R.id.name).setBackgroundResource(R.drawable.specification_select);
            ((TextView) view.findViewById(R.id.name)).setTextColor(Color.parseColor("#ffffff"));
            return false;
        });
        tagList.get(0).setTextColor(Color.parseColor("#ffffff"));
        tagList.get(0).setBackgroundResource(R.drawable.specification_select);
        dialog.show();
        dialog.findViewById(R.id.purchase).setOnClickListener(v -> {
            presenter.purchase(mode.getGoods_id()+"",(String) specificationLayout.getTag(),purchaseNum.getText().toString());
        });
        dialog.findViewById(R.id.add_car).setOnClickListener(v -> {
            presenter.addCar(mode.getGoods_id()+"",(String) specificationLayout.getTag(),purchaseNum.getText().toString());
        });
        dialog.findViewById(R.id.add).setOnClickListener(v -> {
            if (Integer.valueOf(purchaseNum.getText().toString())>=specificationList.get(selectPosition).getSpec_stock()){
                showMessage("库存不足");
                return;
            }
            purchaseNum.setText(Integer.valueOf(purchaseNum.getText().toString())+1+"");
        });
        dialog.findViewById(R.id.reduce).setOnClickListener(v -> {
         if (Integer.valueOf(purchaseNum.getText().toString())<=1){
             showMessage("不能少于一件");
             return;
         }
         purchaseNum.setText(Integer.valueOf(purchaseNum.getText().toString())-1+"");
        });
    }

    @Override
    public void loadDataSuccess(Object data) {
        mode = new Gson().fromJson(JSON.toJSONString(data), GoodsDetailTo.class);
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

    public void addCarSuccess(){
        showMessage("添加购物车成功");
        dialog.dismiss();
    }
}
