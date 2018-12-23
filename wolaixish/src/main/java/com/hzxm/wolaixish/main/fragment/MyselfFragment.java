package com.hzxm.wolaixish.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.Toast;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.hzxm.wolaixish.R;
import com.hzxm.wolaixish.base.BaseActivity;
import com.hzxm.wolaixish.base.BaseFragment;
import com.hzxm.wolaixish.person.Adapter.DeliveredOrderlAdapter;
import com.hzxm.wolaixish.person.ChangeInfoActivity;
import com.hzxm.wolaixish.person.MyOrderListActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 *  Created by Administrator on 2018/12/15.
 */

public class MyselfFragment extends BaseFragment {
    Unbinder unbinder;
    @BindView(R.id.recycle_view)
    LRecyclerView recycleView;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    private BaseActivity baseActivity;

    public MyselfFragment(BaseActivity activity) {
        this.baseActivity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = View.inflate(appContext, R.layout.fragment_myself, null);
        unbinder = ButterKnife.bind(this, mView);
        userInfoTo = userInfoHelp.getUserInfo();
//        presenter = new MySelfPresenter(this);
//        MobSDK.init(appContext);
        recycleView.setFocusable(false);
        setRecycleViewNoScroll(new DeliveredOrderlAdapter(baseActivity),recycleView);
        return mView;
    }
    @Override
    public void onResume() {
        super.onResume();
//        presenter.getMySelfInfo();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    @OnClick({R.id.head_image,R.id.find_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.head_image:
                startActivity(new Intent(baseActivity, ChangeInfoActivity.class));
                goToAnimation(1);
                break;
            case R.id.find_more:
                startActivity(new Intent(baseActivity, MyOrderListActivity.class));
                goToAnimation(1);
                break;
        }
    }
}
