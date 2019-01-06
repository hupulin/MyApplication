package com.xmkj.washmall.main.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.alibaba.fastjson.JSON;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseFragment;
import com.xmkj.washmall.databinding.MallGoodsItemBinding;
import com.xmkj.washmall.main.presenter.MallPresenter;
import com.xmkj.washmall.main.view.MallBannerView;
import com.xmkj.washmall.mall.GoodsDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import hzxmkuar.com.applibrary.domain.main.MallBannerTo;
import hzxmkuar.com.applibrary.domain.main.MallGoodsTo;
import hzxmkuar.com.applibrary.domain.mall.MallTypeTo;

/**
 * Created by Administrator on 2018/12/26.
 */

public class MallFragment extends BaseFragment {
    @BindView(R.id.banner)
    ConvenientBanner banner;
    @BindView(R.id.goods_layout)
    GridLayout goodsLayout;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = View.inflate(appContext, R.layout.fragment_mall, null);
        unbinder = ButterKnife.bind(this, rootView);

        MallPresenter presenter = new MallPresenter(this);
        return rootView;
    }

    private void setBanner(List<MallTypeTo> typeList) {

        List<List<MallTypeTo>> bannerList = new ArrayList<>();
        for (int i = 0; i < (typeList.size() / 8 + (typeList.size() % 8 == 0 ? 0 : 1)); i++) {
            List<MallTypeTo> bannerChildList = new ArrayList<>();
            for (int j = 0; j < 8 && (i * 8 + j) < typeList.size(); j++) {
                bannerChildList.add(typeList.get(i * 8 + j));
            }
            bannerList.add(bannerChildList);
        }
        banner.setPages(MallBannerView::new, bannerList).setPageIndicator(new int[]{R.drawable.page_indicate_un_focus, R.drawable.page_indicate_focus});

    }

    private void setGoodsLayout(List<MallGoodsTo> goodsList) {

        for (int i = 0; i < goodsList.size(); i++) {
            MallGoodsTo goodsTo = goodsList.get(i);
            View mView = View.inflate(appContext, R.layout.mall_goods_item, null);
            MallGoodsItemBinding bind = DataBindingUtil.bind(mView);
            displayImage(bind.goodsImage, goodsTo.getGoods_image());
            bind.price.setText("￥ "+goodsTo.getGoods_price());
            bind.goodsName.setText(goodsTo.getGoods_name());
            goodsLayout.addView(mView);
            mView.setOnClickListener(v -> {
                Intent intent=new Intent(appContext, GoodsDetailActivity.class);
                intent.putExtra("GoodsTo",goodsTo);
                startActivity(intent);
                goToAnimation(1);
            });
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void loadDataSuccess(Object data) {
        List<MallTypeTo> typeList = new Gson().fromJson(JSON.toJSONString(data), new TypeToken<List<MallTypeTo>>() {
        }.getType());
        setBanner(typeList);
    }

    @Override
    protected void submitDataSuccess(Object data) {
        List<MallGoodsTo> goodsList = new Gson().fromJson(JSON.toJSONString(data), new TypeToken<List<MallGoodsTo>>() {
        }.getType());
        setGoodsLayout(goodsList);
    }
}
