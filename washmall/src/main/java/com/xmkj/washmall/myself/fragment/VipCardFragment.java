package com.xmkj.washmall.myself.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseFragment;
import com.xmkj.washmall.base.util.PingFangTextView;
import com.xmkj.washmall.myself.ExchangeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/12/25.
 **/

@SuppressLint("ValidFragment")
public class VipCardFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.vip_bg)
    View vipBg;
    @BindView(R.id.discount)
    TextView discount;
    @BindView(R.id.exchange)
    View exchange;
    @BindView(R.id.tip)
    PingFangTextView tip;
    private boolean isViewCreate;
    private boolean isUiVisible;

    private int type;

    public VipCardFragment(int type) {
        this.type = type;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = View.inflate(appContext, R.layout.vip_card_fragment, null);

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
            vipBg.setBackgroundResource(type == 1 ? R.drawable.vip_card_bg : type == 2 ? R.drawable.vip_card_gold_bg : R.drawable.vip_card_masonry_bg);
            discount.setText(type == 1 ? "下单9折" : type == 2 ? "下单8折" : "下单7折");
            exchange.setVisibility(userInfoTo.getMyselfTo().getUser_info().getMember_level()>=type?View.GONE:View.VISIBLE);
            tip.setText(userInfoTo.getMyselfTo().getUser_info().getMember_level()>=type?"已生效权益":"待生效权益");
        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.exchange)
    public void onViewClicked() {
        Intent intent = new Intent(appContext, ExchangeActivity.class);
        startActivity(intent);
        goToAnimation(1);
    }
}
