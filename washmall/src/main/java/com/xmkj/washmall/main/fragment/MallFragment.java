package com.xmkj.washmall.main.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseFragment;
import com.xmkj.washmall.databinding.MallHeadViewBinding;
import com.xmkj.washmall.main.adapter.MallGoodsAdapter;
import com.xmkj.washmall.main.presenter.MallPresenter;
import com.xmkj.washmall.main.view.MallBannerView;
import com.xmkj.washmall.mall.GoodsDetailActivity;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import hzxmkuar.com.applibrary.domain.main.MallGoodsTo;
import hzxmkuar.com.applibrary.domain.mall.MallTypeTo;

/**
 * Created by Administrator on 2018/12/26.
 */

public class MallFragment extends BaseFragment {


    Unbinder unbinder;
    @BindView(R.id.recycler_view)
    LRecyclerView recyclerView;
    @BindView(R.id.type_layout)
    AutoLinearLayout typeLayout;
    @BindView(R.id.price_icon)
    View priceIcon;
    @BindView(R.id.sale_icon)
    View saleIcon;
    private MallHeadViewBinding binding;
    private MallPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = View.inflate(appContext, R.layout.fragment_mall, null);
        unbinder = ButterKnife.bind(this, rootView);


        headView = View.inflate(appContext, R.layout.mall_head_view, null);
        binding = DataBindingUtil.bind(headView);
        presenter = new MallPresenter(this);
        setRecycleView(new MallGoodsAdapter(getActivity()), recyclerView, presenter, 2, true, true);
        setScreen();
        setRecycleSmooth();
        return rootView;
    }


    private void setBanner(List<MallTypeTo> typeList) {
        MallTypeTo typeTo = new MallTypeTo();
        typeTo.setCate_id(1000000);
        typeTo.setCate_name("积分商城");
        typeList.add(7, typeTo);
        List<List<MallTypeTo>> bannerList = new ArrayList<>();
        for (int i = 0; i < (typeList.size() / 8 + (typeList.size() % 8 == 0 ? 0 : 1)); i++) {
            List<MallTypeTo> bannerChildList = new ArrayList<>();
            for (int j = 0; j < 8 && (i * 8 + j) < typeList.size(); j++) {
                bannerChildList.add(typeList.get(i * 8 + j));
            }
            bannerList.add(bannerChildList);
        }
        binding.banner.setPages(MallBannerView::new, bannerList).setPageIndicator(new int[]{R.drawable.page_indicate_un_focus, R.drawable.page_indicate_focus});

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
    public void recycleItemClick(View view, int position, Object data) {
        MallGoodsTo goodsTo = (MallGoodsTo) data;
        Intent intent = new Intent(appContext, GoodsDetailActivity.class);
        intent.putExtra("GoodsTo", goodsTo);
        startActivity(intent);
        goToAnimation(1);
    }

    private void setRecycleSmooth() {
        recyclerView.setLScrollListener(new LRecyclerView.LScrollListener() {
            @Override
            public void onScrollUp() {

            }

            @Override
            public void onScrollDown() {

            }

            @Override
            public void onScrolled(int distanceX, int distanceY) {
                if (distanceY * 750 / getScreenWidth() > 530)
                    typeLayout.setVisibility(View.VISIBLE);
                else
                    typeLayout.setVisibility(View.GONE);
            }

            @Override
            public void onScrollStateChanged(int state) {

            }
        });
    }

    @OnClick({R.id.price_layout, R.id.sale_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.price_layout:
                priceIcon.setBackgroundResource(priceIcon.isSelected() ? R.drawable.sort_up_icon : R.drawable.sort_down_icon);
                binding.priceIcon.setBackgroundResource(priceIcon.isSelected() ? R.drawable.sort_up_icon : R.drawable.sort_down_icon);

                presenter.getGoodsList(1, priceIcon.isSelected() ? 2 : 1);
                priceIcon.setSelected(!priceIcon.isSelected());
                binding.priceIcon.setSelected(!priceIcon.isSelected());
                break;
            case R.id.sale_layout:
                saleIcon.setBackgroundResource(saleIcon.isSelected() ? R.drawable.sort_up_icon : R.drawable.sort_down_icon);
                binding.saleIcon.setBackgroundResource(saleIcon.isSelected() ? R.drawable.sort_up_icon : R.drawable.sort_down_icon);
                presenter.getGoodsList(2, saleIcon.isSelected() ? 2 : 1);
                saleIcon.setSelected(!saleIcon.isSelected());
                binding.saleIcon.setSelected(!saleIcon.isSelected());

                break;
        }
    }

    public void setScreen() {
        binding.priceLayout.setOnClickListener(view -> {
            priceIcon.setBackgroundResource(priceIcon.isSelected() ? R.drawable.sort_up_icon : R.drawable.sort_down_icon);
            binding.priceIcon.setBackgroundResource(priceIcon.isSelected() ? R.drawable.sort_up_icon : R.drawable.sort_down_icon);

            presenter.getGoodsList(1, priceIcon.isSelected() ? 2 : 1);
            priceIcon.setSelected(!priceIcon.isSelected());
            binding.priceIcon.setSelected(!priceIcon.isSelected());
        });

        binding.saleLayout.setOnClickListener(view -> {
            saleIcon.setBackgroundResource(saleIcon.isSelected() ? R.drawable.sort_up_icon : R.drawable.sort_down_icon);
            binding.saleIcon.setBackgroundResource(saleIcon.isSelected() ? R.drawable.sort_up_icon : R.drawable.sort_down_icon);
            presenter.getGoodsList(2, saleIcon.isSelected() ? 2 : 1);
            saleIcon.setSelected(!saleIcon.isSelected());
            binding.saleIcon.setSelected(!saleIcon.isSelected());
        });
    }
}
