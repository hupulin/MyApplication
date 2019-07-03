package com.xmkj.washmall.mall;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.mall.adapter.SelectCouponAdapter;
import com.xmkj.washmall.mall.presenter.SelectCouponPresenter;
import com.xmkj.washmall.myself.adapter.CouponAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import hzxmkuar.com.applibrary.domain.myself.CouponTo;

/**
 * Created by Administrator on 2018/12/28.
 */

public class SelectCouponActivity extends BaseActivity {

    @BindView(R.id.recycler_view)
    LRecyclerView recyclerView;
    @BindView(R.id.coupon_title)
    TextView couponTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_coupon);
        ButterKnife.bind(this);
        setTitleName("选择优惠券");
        SelectCouponPresenter presenter = new SelectCouponPresenter(this);
        setRecycleView(new SelectCouponAdapter(this, 1), recyclerView, presenter);
        presenter.getCouponList(getIntent().getIntExtra("OrderId", 0), getIntent().getIntExtra("UseType", 2));
    }

    @Override
    public void recycleItemClick(View view, int position, Object data) {
        CouponTo couponTo = (CouponTo) data;
        Intent intent = new Intent();
        intent.putExtra("CouponTo", couponTo);
        setResult(20, intent);
        finish();
    }

    @Override
    public void loadDataSuccess(Object data) {
couponTitle.setText("可使用红包("+data+"张)");
    }
}
