package com.xmkj.washmall.myself.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseFragment;
import com.xmkj.washmall.base.BasePresenter;
import com.xmkj.washmall.base.adapter.BaseAdapter;
import com.xmkj.washmall.base.view.RecycleViewHeadView;
import com.xmkj.washmall.myself.adapter.CouponAdapter;
import com.xmkj.washmall.myself.presenter.CouponPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import hzxmkuar.com.applibrary.domain.myself.CouponTo;

/**
 * Created by Administrator on 2018/12/25.
 **/

@SuppressLint("ValidFragment")
public class CouponFragment extends BaseFragment {
    @BindView(R.id.recycler_view)
    LRecyclerView recyclerView;
    Unbinder unbinder;
    private boolean isViewCreate;
    private boolean isUiVisible;
    private int type;
    private CouponAdapter adapter;
    List<CouponTo>couponList;

    public CouponFragment(int type, List<CouponTo>couponList){
        this.type=type;
        this.couponList=couponList;
    }

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
            adapter = new CouponAdapter(getActivity(),type);

            setMRecycleView();
        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    protected void setMRecycleView() {

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
        recyclerView.setRefreshHeader(new RecycleViewHeadView(appContext));
        LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);

        recyclerView.setAdapter(lRecyclerViewAdapter);
        recyclerView.setPullRefreshEnabled(false);
        recyclerView.setLoadMoreEnabled(false);
        adapter.setList(couponList);
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recyclerView.setFooterViewColor(R.color.appColor, R.color.appColor, R.color.transparent);




    }
}
