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

public class WashOrderActivity extends BaseActivity {

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

    private List<Fragment> fragmentList = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wash_order);
        ButterKnife.bind(this);
        setTitleName("我徕洗订单");
        initFragment();
    }



    private void initFragment() {

        fragmentList.add(new OrderWashFragment(0));
        fragmentList.add(new OrderWashFragment(1));
        fragmentList.add(new OrderWashFragment(2));
        fragmentList.add(new OrderWashFragment(3));
        fragmentList.add(new OrderWashFragment(4));
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

        viewPager.setCurrentItem(getIntent().getIntExtra("Index",0));
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



    @OnClick({R.id.all, R.id.waite_progress, R.id.waite_send, R.id.already_send, R.id.finish_text})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.all:

                    viewPager.setCurrentItem(0);
                break;
            case R.id.waite_progress:

                    viewPager.setCurrentItem(1);
                break;
            case R.id.waite_send:

                    viewPager.setCurrentItem(2);
                break;
            case R.id.already_send:

                    viewPager.setCurrentItem(3);
                break;
            case R.id.finish_text:

                    viewPager.setCurrentItem(4);
                break;
        }
    }
}
