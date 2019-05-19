package com.xmkj.washmall.integral;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.WebActivity;
import com.xmkj.washmall.base.util.PingFangTextView;
import com.xmkj.washmall.base.util.StatueBarUtil;
import com.xmkj.washmall.databinding.IntegralRecordItemBinding;
import com.xmkj.washmall.integral.fragment.IntegralFragment;
import com.xmkj.washmall.integral.presenter.IntegralDetailPresenter;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hzxmkuar.com.applibrary.domain.integral.IntegralInfoTo;
import hzxmkuar.com.applibrary.domain.integral.IntegralRecordTo;

/**
 * Created by Administrator on 2018/12/28.
 **/

public class IntegralDetailActivity extends BaseActivity {
    @BindView(R.id.record_layout)
    GridLayout recordLayout;
    @BindView(R.id.score)
    PingFangTextView score;
    @BindView(R.id.all)
    TextView all;
    @BindView(R.id.waite_send)
    TextView waiteSend;
    @BindView(R.id.already_send)
    TextView alreadySend;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.move_line)
    AutoRelativeLayout moveLine;
    private IntegralDetailPresenter presenter;
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integral_detail);
        ButterKnife.bind(this);
        StatueBarUtil.setStatueBarTextWhite(getWindow());
        setTitleName("积分余额");
        presenter = new IntegralDetailPresenter(this);

        setViewPager();
    }

    private void setViewPager() {
        fragmentList.add(new IntegralFragment(0));
        fragmentList.add(new IntegralFragment(1));
        fragmentList.add(new IntegralFragment(2));
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float v, @Px int move) {
                  moveLine.setX(getScreenWidth()/3*position+move/3);
            }

            @Override
            public void onPageSelected(int position) {
                all.setTextColor(position == 0 ? Color.parseColor("#6e62ce") : Color.parseColor("#282828"));
                waiteSend.setTextColor(position == 1 ? Color.parseColor("#6e62ce") : Color.parseColor("#282828"));
                alreadySend.setTextColor(position == 2 ? Color.parseColor("#6e62ce") : Color.parseColor("#282828"));
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    private FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int i) {
            return fragmentList.get(i);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    };





    @Override
    public void loadDataSuccess(Object data) {
        IntegralInfoTo infoTo = new Gson().fromJson(JSON.toJSONString(data), IntegralInfoTo.class);
        score.setText(infoTo.getScore() + "");
    }



    @OnClick({R.id.all, R.id.waite_send, R.id.already_send, R.id.rule_des,R.id.rule})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.all:
                viewPager.setCurrentItem(0);
                break;
            case R.id.waite_send:
                viewPager.setCurrentItem(1);
                break;
            case R.id.already_send:
                viewPager.setCurrentItem(2);
                break;
            case R.id.rule_des:
                Intent intent=new Intent(appContext,IntegralRecordActivity.class);
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.rule:
                intent=new Intent(appContext,WebActivity.class);
                intent.putExtra("Title","积分说明");
                startActivity(intent);
                goToAnimation(1);
                break;
        }
    }
}
