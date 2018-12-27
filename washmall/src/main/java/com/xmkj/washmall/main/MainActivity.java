package com.xmkj.washmall.main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Px;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.util.PingFangTextView;
import com.xmkj.washmall.base.util.StatueBarUtil;
import com.xmkj.washmall.main.fragment.CarFragment;
import com.xmkj.washmall.main.fragment.HomeFragment;
import com.xmkj.washmall.main.fragment.MallFragment;
import com.xmkj.washmall.main.fragment.MyselfFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.home_icon)
    View homeIcon;
    @BindView(R.id.home_text)
    PingFangTextView homeText;
    @BindView(R.id.mall_icon)
    View mallIcon;
    @BindView(R.id.mall_text)
    PingFangTextView mallText;
    @BindView(R.id.car_icon)
    View carIcon;
    @BindView(R.id.car_text)
    PingFangTextView carText;
    @BindView(R.id.myself_icon)
    View myselfIcon;
    @BindView(R.id.myself_text)
    PingFangTextView myselfText;

    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatueBarUtil.setStatueBarTextWhite(getWindow());
        ButterKnife.bind(this);
        initFragment();
    }

    private void initFragment() {
        fragmentList.add(new HomeFragment());
        fragmentList.add(new MallFragment());
        fragmentList.add(new CarFragment());
        fragmentList.add(new MyselfFragment());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, @Px int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                homeText.setTextColor(position==0?Color.parseColor("#5F49E4"):Color.parseColor("#000000"));
                mallText.setTextColor(position==1?Color.parseColor("#5F49E4"):Color.parseColor("#000000"));
                carText.setTextColor(position==2?Color.parseColor("#5F49E4"):Color.parseColor("#000000"));
                myselfText.setTextColor(position==3?Color.parseColor("#5F49E4"):Color.parseColor("#000000"));
                homeIcon.setBackgroundResource(position==0?R.drawable.main_home_select_icon:R.drawable.main_home_un_select);
                mallIcon.setBackgroundResource(position==1?R.drawable.main_mall_select:R.drawable.main_mall_un_select);
                carIcon.setBackgroundResource(position==2?R.drawable.main_car_select:R.drawable.main_car_un_select);
                myselfIcon.setBackgroundResource(position==3?R.drawable.main_myself_select:R.drawable.main_myself_un_select);
               if (position==0)
                    StatueBarUtil.setStatueBarTextWhite(getWindow());
                if (position==1||position==2)
                    StatueBarUtil.setStatueBarTextBlack(getWindow());
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

    @OnClick({R.id.home_layout, R.id.mall_layout, R.id.car_layout, R.id.myself_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_layout:
                viewPager.setCurrentItem(0);
                break;
            case R.id.mall_layout:
                viewPager.setCurrentItem(1);
                break;
            case R.id.car_layout:
                viewPager.setCurrentItem(2);
                break;
            case R.id.myself_layout:
                viewPager.setCurrentItem(3);
                break;
        }
    }
}
