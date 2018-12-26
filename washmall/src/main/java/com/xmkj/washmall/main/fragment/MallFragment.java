package com.xmkj.washmall.main.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseFragment;
import com.xmkj.washmall.databinding.MallGoodsItemBinding;
import com.xmkj.washmall.main.view.MallBannerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import hzxmkuar.com.applibrary.domain.main.MallBannerTo;
import hzxmkuar.com.applibrary.domain.main.MallGoodsTo;

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
        setBanner();
        setGoodsLayout();
        return rootView;
    }

    private void setBanner(){
        List<List<MallBannerTo>>bannerList=new ArrayList<>();
        List<MallBannerTo>bannerChildeList1=new ArrayList<>();
        List<MallBannerTo>bannerChildeList2=new ArrayList<>();
        List<MallBannerTo>bannerChildeList3=new ArrayList<>();
        for (int i=0;i<8;i++){
           bannerChildeList1.add(new MallBannerTo());
           bannerChildeList2.add(new MallBannerTo());
           bannerChildeList3.add(new MallBannerTo());
        }
        bannerList.add(bannerChildeList1);
        bannerList.add(bannerChildeList2);
        bannerList.add(bannerChildeList3);

        banner.setPages(MallBannerView::new,bannerList).setPageIndicator(new int[]{R.drawable.page_indicate_un_focus, R.drawable.page_indicate_focus});

    }

    private void setGoodsLayout(){
        for (int i=0;i<4;i++){
            MallGoodsTo goodsTo=new MallGoodsTo();
            View mView=View.inflate(appContext,R.layout.mall_goods_item,null);
            MallGoodsItemBinding bind= DataBindingUtil.bind(mView);
            displayImage(bind.goodsImage,goodsTo.getImageUrl());
            bind.price.setText(goodsTo.getPrice());
            bind.goodsName.setText(goodsTo.getGoodsName());
            goodsLayout.addView(mView);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
