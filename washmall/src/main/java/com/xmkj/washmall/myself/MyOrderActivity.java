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
import com.xmkj.washmall.myself.fragment.OrderFragment;
import com.xmkj.washmall.myself.fragment.OrderWashFragment;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/12/25.
 **/

public class MyOrderActivity extends BaseActivity {
    @BindView(R.id.wash_order)
    View washOrder;
    @BindView(R.id.mall_order)
    View mallOrder;
    @BindView(R.id.all)
    TextView all;
    @BindView(R.id.waite_progress)
    TextView waiteProgress;
    @BindView(R.id.waite_send)
    TextView waiteSend;
    @BindView(R.id.already_send)
    TextView alreadySend;
    @BindView(R.id.finish_text)
    TextView finish;
    @BindView(R.id.move_line)
    AutoRelativeLayout moveLine;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.view_pager_wash)
    ViewPager viewPagerWash;

    private List<Fragment> fragmentList = new ArrayList<>();
    private List<Fragment> fragmentListWash = new ArrayList<>();
    private boolean firstLoadWash = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        ButterKnife.bind(this);
        setTitleName("我的订单");
        initWashFragment();
    }

    private void initWashFragment() {
        fragmentListWash.add(new OrderWashFragment(0));
        fragmentListWash.add(new OrderWashFragment(1));
        fragmentListWash.add(new OrderWashFragment(2));
        fragmentListWash.add(new OrderWashFragment(3));
        fragmentListWash.add(new OrderWashFragment(4));
        viewPagerWash.setAdapter(adapterWash);
        viewPagerWash.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float v, @Px int i1) {
                moveLine.setX(getScreenWidth() / 5 * position + i1 / 5);
            }

            @Override
            public void onPageSelected(int position) {
                all.setTextColor(position == 0 ? Color.parseColor("#6452CF") : Color.parseColor("#000000"));
                waiteProgress.setTextColor(position == 1 ? Color.parseColor("#6452CF") : Color.parseColor("#000000"));
                waiteSend.setTextColor(position == 2 ? Color.parseColor("#6452CF") : Color.parseColor("#000000"));
                alreadySend.setTextColor(position == 3 ? Color.parseColor("#6452CF") : Color.parseColor("#000000"));
                finish.setTextColor(position == 4 ? Color.parseColor("#6452CF") : Color.parseColor("#000000"));
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void initFragment() {
        fragmentList.add(new OrderFragment(0));
        fragmentList.add(new OrderFragment(1));
        fragmentList.add(new OrderFragment(2));
        fragmentList.add(new OrderFragment(3));
        fragmentList.add(new OrderFragment(4));
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float v, @Px int i1) {
                moveLine.setX(getScreenWidth() / 5 * position + i1 / 5);
            }

            @Override
            public void onPageSelected(int position) {
                all.setTextColor(position == 0 ? Color.parseColor("#6452CF") : Color.parseColor("#000000"));
                waiteProgress.setTextColor(position == 1 ? Color.parseColor("#6452CF") : Color.parseColor("#000000"));
                waiteSend.setTextColor(position == 2 ? Color.parseColor("#6452CF") : Color.parseColor("#000000"));
                alreadySend.setTextColor(position == 3 ? Color.parseColor("#6452CF") : Color.parseColor("#000000"));
                finish.setTextColor(position == 4 ? Color.parseColor("#6452CF") : Color.parseColor("#000000"));
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

    private FragmentPagerAdapter adapterWash = new FragmentPagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            return fragmentListWash.get(position);
        }

        @Override
        public int getCount() {
            return fragmentListWash.size();
        }
    };

    @OnClick({R.id.wash_order, R.id.mall_order, R.id.all, R.id.waite_progress, R.id.waite_send, R.id.already_send, R.id.finish_text})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.wash_order:
                viewPager.setVisibility(View.GONE);
                viewPagerWash.setVisibility(View.VISIBLE);
                viewPagerWash.setSelected(true);
                viewPager.setSelected(false);
                washOrder.setBackgroundResource(R.drawable.main_wash_order);
                mallOrder.setBackgroundResource(R.drawable.main_mall_order);
                viewPagerWash.setCurrentItem(0);
                viewPager.setCurrentItem(0);
                waiteProgress.setText("取货中");
                waiteSend.setText("清洗中");
                alreadySend.setText("送货中");
                finish.setText("待取货");

                break;
            case R.id.mall_order:
                viewPager.setVisibility(View.VISIBLE);
                viewPagerWash.setVisibility(View.GONE);
                viewPagerWash.setSelected(false);
                viewPager.setSelected(true);
                washOrder.setBackgroundResource(R.drawable.main_wash_order_un_select);
                mallOrder.setBackgroundResource(R.drawable.main_mall_order_select);
                viewPager.setCurrentItem(0);
                viewPagerWash.setCurrentItem(0);

                waiteProgress.setText("待处理");
                waiteSend.setText("待发货");
                alreadySend.setText("已发货");
                finish.setText("已完成");
                if (firstLoadWash){
                    firstLoadWash=false;
                    initFragment();
                }
                break;
            case R.id.all:
                if (viewPager.isSelected())
                    viewPager.setCurrentItem(0);
                else
                    viewPagerWash.setCurrentItem(0);
                break;
            case R.id.waite_progress:
                if (viewPager.isSelected())
                    viewPager.setCurrentItem(1);
                else
                    viewPagerWash.setCurrentItem(1);
                break;
            case R.id.waite_send:
                if (viewPager.isSelected())
                    viewPager.setCurrentItem(2);
                else
                    viewPagerWash.setCurrentItem(2);
                break;
            case R.id.already_send:
                if (viewPager.isSelected())
                    viewPager.setCurrentItem(3);
                else
                    viewPagerWash.setCurrentItem(3);
                break;
            case R.id.finish_text:
                if (viewPager.isSelected())
                    viewPager.setCurrentItem(4);
                else
                    viewPagerWash.setCurrentItem(4);
                break;
        }
    }
}
