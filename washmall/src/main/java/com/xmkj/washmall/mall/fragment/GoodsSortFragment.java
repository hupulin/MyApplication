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


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       rootView = View.inflate(appContext, R.layout.common_fragment_recyclerview, null);

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
            List<MallChildTypeTo> categoryList = ((GoodsSortActivity) getActivity()).categoryList;
            GoodsSortPresenter presenter = new GoodsSortPresenter(this);
            presenter.getGoodsList(categoryList.get(FragmentPagerItem.getPosition(getArguments())).getCate_id());
            setRecycleView(new GoodsSortAdapter(getActivity()),recyclerView,presenter ,2);


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
}

