package com.xmkj.washmall.mall.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseFragment;
import com.xmkj.washmall.mall.GoodsDetailActivity;
import com.xmkj.washmall.mall.GoodsSortActivity;
import com.xmkj.washmall.mall.adapter.GoodsSortAdapter;
import com.xmkj.washmall.mall.presenter.GoodsSortPresenter;
import com.xmkj.washmall.wash.adapter.WardrobelAdapter;
import com.xmkj.washmall.wash.presenter.WardrobePresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import hzxmkuar.com.applibrary.domain.main.MallGoodsTo;
import hzxmkuar.com.applibrary.domain.mall.MallChildTypeTo;


/**
 * Created by Administrator on 2018/12/26.
 **/

public class GoodsSortFragment extends BaseFragment {
    Unbinder unbinder;
    @BindView(R.id.recycler_view)
    LRecyclerView recyclerView;


    private boolean isViewCreate;
    private boolean isUiVisible;
    @BindView(R.id.price_icon)
    View priceIcon;
    @BindView(R.id.sale_icon)
    View saleIcon;
    private GoodsSortPresenter presenter;
    private List<MallChildTypeTo> categoryList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       rootView = View.inflate(appContext, R.layout.fragment_goods_sort, null);

        unbinder = ButterKnife.bind(this, rootView);
        isViewCreate = true;

        loadData();

        return rootView;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            isUiVisible = true;
            loadData();
        } else {
            isUiVisible = false;
        }
        super.setUserVisibleHint(isVisibleToUser);

    }

    public void loadData() {
        if (isViewCreate && isUiVisible) {

            isUiVisible = false;
            isViewCreate = false;
            categoryList = ((GoodsSortActivity) getActivity()).categoryList;
            presenter = new GoodsSortPresenter(this);
            presenter.getGoodsList(categoryList.get(FragmentPagerItem.getPosition(getArguments())).getCate_id(),0,2
            );
            setRecycleView(new GoodsSortAdapter(getActivity()),recyclerView, presenter,2,true,true);


        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void recycleItemClick(View view, int position, Object data) {
        MallGoodsTo goodsTo = (MallGoodsTo) data;
        Intent intent = new Intent(appContext, GoodsDetailActivity.class);
        intent.putExtra("GoodsTo", goodsTo);
        startActivity(intent);
        goToAnimation(1);
    }

    @OnClick({R.id.price_layout, R.id.sale_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.price_layout:
                priceIcon.setBackgroundResource(priceIcon.isSelected() ? R.drawable.sort_up_icon : R.drawable.sort_down_icon);

                presenter.getGoodsList(categoryList.get(FragmentPagerItem.getPosition(getArguments())).getCate_id(),1, priceIcon.isSelected() ? 2 : 1);
                priceIcon.setSelected(!priceIcon.isSelected());
                break;
            case R.id.sale_layout:
                saleIcon.setBackgroundResource(saleIcon.isSelected() ? R.drawable.sort_up_icon : R.drawable.sort_down_icon);
                presenter.getGoodsList(categoryList.get(FragmentPagerItem.getPosition(getArguments())).getCate_id(),2, saleIcon.isSelected() ? 2 : 1);
                saleIcon.setSelected(!saleIcon.isSelected());

                break;
        }
    }
}

