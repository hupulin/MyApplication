package com.xmkj.washmall.mall;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.mall.presenter.SelectCouponPresenter;
import com.xmkj.washmall.myself.adapter.CouponAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import hzxmkuar.com.applibrary.domain.myself.CouponTo;

/**
 * Created by Administrator on 2018/12/28.
 */

public class SelectCouponActivity extends BaseActivity {
    @BindView(R.id.coupon_title)
    TextView couponTitle;
    @BindView(R.id.recycler_view)
    LRecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_coupon);
        ButterKnife.bind(this);
        setTitleName("选择优惠券");
        SelectCouponPresenter presenter = new SelectCouponPresenter(this);
//        setRecycleView(new CouponAdapter(this),recyclerView,presenter);
        presenter.getCouponList(2);
    }

    @Override
    public void recycleItemClick(View view, int position, Object data) {
        CouponTo couponTo= (CouponTo) data;
        Intent intent=new Intent();
//        intent.putExtra("Discount",couponTo.getMoney());
        setResult(20,intent);
        finish();
    }
}
