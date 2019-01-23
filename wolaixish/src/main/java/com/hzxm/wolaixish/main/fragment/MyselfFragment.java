package com.hzxm.wolaixish.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.google.gson.Gson;
import com.hzxm.wolaixish.R;
import com.hzxm.wolaixish.base.BaseActivity;
import com.hzxm.wolaixish.base.BaseFragment;
import com.hzxm.wolaixish.main.present.MyselfPresent;
import com.hzxm.wolaixish.main.adapter.DeliveredOrderlAdapter;
import com.hzxm.wolaixish.person.ChangeInfoActivity;
import com.hzxm.wolaixish.person.MyOrderListActivity;
import com.makeramen.roundedimageview.RoundedImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import hzxmkuar.com.applibrary.domain.delivery.main.UserInfoTo;

/**
 *  Created by Administrator on 2018/12/15.
 */

public class MyselfFragment extends BaseFragment {
    Unbinder unbinder;
    @BindView(R.id.recycle_view)
    LRecyclerView recycleView;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.head_image)
    RoundedImageView headImage;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.order_today_text)
    TextView orderTodayt;
    @BindView(R.id.order_yesterday_text)
    TextView orderYesterday;
    @BindView(R.id.order_month_text)
    TextView orderMonth;
    @BindView(R.id.order_last_month_text)
    TextView orderLastMonth;
    @BindView(R.id.order_today_pay)
    TextView orderTodayAmount;
    @BindView(R.id.order_yesterday_pay)
    TextView orderYesterdayAmount;
    @BindView(R.id.order_month_pay)
    TextView orderMonthAmount;
    @BindView(R.id.order_last_month_pay)
    TextView orderLastMonthAmount;

    private BaseActivity baseActivity;
    private MyselfPresent myselfPresent;
    private UserInfoTo mode;
    public MyselfFragment(BaseActivity activity) {
        this.baseActivity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = View.inflate(appContext, R.layout.fragment_myself, null);
        unbinder = ButterKnife.bind(this, mView);
        userInfoTo = userInfoHelp.getUserInfo();
        recycleView.setFocusable(false);
        myselfPresent=new MyselfPresent(this);
        myselfPresent.getDeliveryserinfo();
        setRecycleViewNoScroll(new DeliveredOrderlAdapter(baseActivity),recycleView,myselfPresent);
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
                Intent intent=new Intent(baseActivity, ChangeInfoActivity.class);
                intent.putExtra("headImageUrl",mode.getUser_info().getFace_url());
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.find_more:
                startActivity(new Intent(baseActivity, MyOrderListActivity.class));
                goToAnimation(1);
                break;
        }
    }

    @Override
    protected void submitDataSuccess(Object data) {
        mode = new Gson().fromJson(JSON.toJSONString(data), UserInfoTo.class);
        disPlayRoundImage(headImage,mode.getUser_info().getFace_url());
        name.setText(mode.getUser_info().getNickname());
        orderTodayt.setText(mode.getOrder_statistics().getToday().getOrder_number()+"");
        orderTodayAmount.setText(mode.getOrder_statistics().getToday().getOrder_amount()+"");
        orderYesterday.setText(mode.getOrder_statistics().getYesterday().getOrder_number()+"");
        orderYesterdayAmount.setText(mode.getOrder_statistics().getYesterday().getOrder_amount()+"");
        orderMonth.setText(mode.getOrder_statistics().getMonth().getOrder_number()+"");
        orderMonthAmount.setText(mode.getOrder_statistics().getMonth().getOrder_amount()+"");
        orderLastMonth.setText(mode.getOrder_statistics().getLast_month().getOrder_number()+"");
        orderLastMonthAmount.setText(mode.getOrder_statistics().getLast_month().getOrder_amount()+"");

    }
}
