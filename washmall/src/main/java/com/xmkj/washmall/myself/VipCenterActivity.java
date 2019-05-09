package com.xmkj.washmall.myself;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.util.PingFangTextView;
import com.xmkj.washmall.myself.fragment.VipCardFragment;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/12/28.
 */

public class VipCenterActivity extends BaseActivity {
    @BindView(R.id.silver)
    PingFangTextView silver;
    @BindView(R.id.gold)
    PingFangTextView gold;
    @BindView(R.id.platinum)
    PingFangTextView platinum;
    @BindView(R.id.move_line)
    AutoRelativeLayout moveLine;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    private List<Fragment>fragmentList=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip_center);
        ButterKnife.bind(this);
        setTitleName("会员中心");
        initFragment();
    }

    private void initFragment() {
        fragmentList.add(new VipCardFragment(1));
        fragmentList.add(new VipCardFragment(2));
        fragmentList.add(new VipCardFragment(3));
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float v, @Px int moveX) {
             moveLine.setX(getScreenWidth()/3*position+moveX/3);
            }

            @Override
            public void onPageSelected(int position) {
            silver.setTextColor(position==0?Color.parseColor("#ffffff"):Color.parseColor("#EEE8FF"));
            gold.setTextColor(position==1?Color.parseColor("#ffffff"):Color.parseColor("#EEE8FF"));
            platinum.setTextColor(position==2?Color.parseColor("#ffffff"):Color.parseColor("#EEE8FF"));
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @OnClick({R.id.silver, R.id.gold, R.id.platinum})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.silver:
                viewPager.setCurrentItem(0);
                break;
            case R.id.gold:
                viewPager.setCurrentItem(1);
                break;
            case R.id.platinum:
                viewPager.setCurrentItem(2);
                break;
        }
    }

    private FragmentPagerAdapter adapter=new FragmentPagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    };
}
