package com.hzxm.wolaixish.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.hzxm.wolaixish.R;
import com.hzxm.wolaixish.base.BaseActivity;
import com.hzxm.wolaixish.base.BaseFragment;
import com.hzxm.wolaixish.main.adapter.OrderListAdapter;
import com.hzxm.wolaixish.main.present.MainPresenter;
import com.hzxm.wolaixish.news.NewActivity;
import com.hzxm.wolaixish.person.ChangeInfoActivity;
import com.hzxm.wolaixish.person.MyOrderListActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import hzxmkuar.com.applibrary.domain.MessageTo;

/**
 * Created by Administrator on 2018/12/16.
 */

public class MainFragment extends BaseFragment {
    Unbinder unbinder;
    @BindView(R.id.recycle_view)
    LRecyclerView recycleView;
    private BaseActivity baseActivity;
    private MainPresenter presenter;
    public MainFragment(BaseActivity activity) {
        this.baseActivity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = View.inflate(appContext, R.layout.fragment_main, null);
        unbinder = ButterKnife.bind(this, mView);
        userInfoTo = userInfoHelp.getUserInfo();
        presenter = new MainPresenter(this);
        presenter.getOrderList(1);
        setRecycleView(new OrderListAdapter(baseActivity),recycleView,presenter);
        return mView;
    }

    @OnClick({R.id.news_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.news_layout:
                startActivity(new Intent(baseActivity, NewActivity.class));
                goToAnimation(1);
                break;
        }
    }



}
