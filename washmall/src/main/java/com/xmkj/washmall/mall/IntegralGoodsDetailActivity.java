package com.xmkj.washmall.mall;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.util.PingFangTextView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hzxmkuar.com.applibrary.domain.mall.GoodsDetailTo;
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
    @BindView(R.id.web_view)
    WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integral_goods_detail);
        ButterKnife.bind(this);
        setTitleName("蓝月亮洗衣液家庭装");
        setView();
    }

    private void setView() {
        GoodsDetailTo mode = new GoodsDetailTo();
        displayImage(goodsImage, mode.getGoods_image());
        goodsPrice.setText(mode.getGoods_price());
        saleNum.setText("销量：" + 100);
        goodsName.setText(mode.getGoods_name());
        goodsDes.setText(mode.getGoods_desc());
        webView.loadUrl("http://118.190.201.28:8080/img/index.html");

    }


    @OnClick({R.id.select_type,R.id.confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.select_type:
                specificationDialog();
                break;
            case R.id.confirm:
                Intent intent=new Intent(appContext,ConfirmIntegralOrderActivity.class);
                startActivity(intent);
                goToAnimation(1);
                break;

        }
    }

    private void specificationDialog() {
//        NiftyDialogBuilder dialog =  NiftyDialogBuilder.getInstance(this);
//        dialog.setContentView(R.layout.dialog_select_specification);
//        TagFlowLayout specificationLayout = dialog.findViewById(R.id.specification_layout);
//        List<SpecificationTo> specificationList = new ArrayList<>();
//        List<TextView> tagList = new ArrayList<>();
//        specificationList.add(new SpecificationTo("薰衣草香", "￥99.00"));
//        specificationList.add(new SpecificationTo("茉莉花香", "￥199.00"));
//        specificationList.add(new SpecificationTo("茉莉花香", "￥299.00"));
//         specificationLayout.setMaxSelectCount(1);
//        ((TextView)dialog.findViewById(R.id.specification_price)).setText("￥188.00");
//        displayImage(dialog.findViewById(R.id.goods_image),"http://118.190.201.28:8080/img/goods_image.png");
//        specificationLayout.setAdapter(new TagAdapter<SpecificationTo>(specificationList) {
//            @Override
//            public View getView(FlowLayout parent, int position, SpecificationTo specificationTo) {
//                View mView = View.inflate(appContext, R.layout.specification_tag_item, null);
//                TextView tagName = mView.findViewById(R.id.name);
//                tagName.setText(specificationTo.getName());
//                tagList.add(tagName);
//                return mView;
//            }
//        });
//        specificationLayout.setOnTagClickListener((view, position, parent) ->{
//            Observable.from(tagList).subscribe(textView -> {
//                textView.setTextColor(Color.parseColor("#6868FF"));
//                textView.setBackgroundResource(R.drawable.specification_un_select);
//            });
//            view.findViewById(R.id.name).setBackgroundResource(R.drawable.specification_select);
//            ((TextView)view.findViewById(R.id.name)).setTextColor(Color.parseColor("#ffffff"));
//           return false ;
//        } );
//        dialog.show();
    }
}
