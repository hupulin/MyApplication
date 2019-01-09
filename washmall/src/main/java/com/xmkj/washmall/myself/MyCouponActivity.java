package com.xmkj.washmall.myself;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.myself.fragment.CouponFragment;
import com.xmkj.washmall.myself.presenter.CouponPresenter;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/12/28.
 **/

public class MyCouponActivity extends BaseActivity {
    @BindView(R.id.un_use)
    TextView unUse;
    @BindView(R.id.already_use)
    TextView alreadyUse;
    @BindView(R.id.out_date)
    TextView outDate;
    @BindView(R.id.move_line)
    AutoRelativeLayout moveLine;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_coupon);
        ButterKnife.bind(this);
        setTitleName("优惠券");
        initFragment();
        CouponPresenter presenter=new CouponPresenter(this);
    }

    private void initFragment() {
        fragmentList.add(new CouponFragment(1));
        fragmentList.add(new CouponFragment(2));
        fragmentList.add(new CouponFragment(3));
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float v, @Px int moveX) {
                moveLine.setX(position * getScreenWidth() / 3 + moveX / 3);
            }

            @Override
            public void onPageSelected(int position) {
                unUse.setTextColor(position == 0 ? Color.parseColor("#8482FF") : Color.parseColor("#000000"));
                alreadyUse.setTextColor(position == 1 ? Color.parseColor("#8482FF") : Color.parseColor("#000000"));
                outDate.setTextColor(position == 2 ? Color.parseColor("#8482FF") : Color.parseColor("#000000"));
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }


    private FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    };

    @OnClick({R.id.save, R.id.un_use, R.id.already_use, R.id.out_date})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.save:
                break;
            case R.id.un_use:
                viewPager.setCurrentItem(0);
                break;
            case R.id.already_use:
                viewPager.setCurrentItem(1);
                break;
            case R.id.out_date:
                viewPager.setCurrentItem(2);
                break;
        }
    }
}
