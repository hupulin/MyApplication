package com.xmkj.washmall.myself.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseFragment;
import com.xmkj.washmall.myself.adapter.CouponAdapter;
import com.xmkj.washmall.myself.presenter.CouponPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

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

    public CouponFragment(int type){
        this.type=type;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = View.inflate(appContext, R.layout.common_fragment_recyclerview, null);

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
            CouponAdapter adapter=new CouponAdapter(getActivity());
            CouponPresenter presenter=new CouponPresenter(this);
            presenter.getCouponList(type);
            setRecycleView(adapter,recyclerView,presenter);
        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}