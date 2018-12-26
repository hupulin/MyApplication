package com.xmkj.washmall.wash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;


import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.wash.fragment.SelectWashFragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hzxmkuar.com.applibrary.domain.wash.WashTypeTo;
import rx.Observable;
import util.smart.SmartTabLayout;

/**
 * Created by Administrator on 2018/12/26.
 */

public class SelectWashActivity extends BaseActivity {
    @BindView(R.id.tab)
    SmartTabLayout tab;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    public List<WashTypeTo> typeList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_wash);
        ButterKnife.bind(this);
        setTitleName("选择下单种类");
        typeList = new ArrayList<>();
        List<String>tagList=new ArrayList<>(Arrays.asList("上装","下装","裙装"));
        WashTypeTo washTypeTo=new WashTypeTo();
        washTypeTo.setTitle("洗衣");
        washTypeTo.setTag(tagList);
        typeList.add(washTypeTo);
        WashTypeTo washTypeTo1=new WashTypeTo();
        washTypeTo1.setTitle("洗鞋");
        typeList.add(washTypeTo1);
        WashTypeTo washTypeTo2=new WashTypeTo();
        washTypeTo2.setTitle("大件洗");
        typeList.add(washTypeTo2);
        WashTypeTo washTypeTo3=new WashTypeTo();
        washTypeTo3.setTitle("奢侈品洗护");
        typeList.add(washTypeTo3);
        setTab(typeList);
    }

    public void setTab(List<WashTypeTo> paymentTypeList) {

        FragmentPagerItems.Creator creator = FragmentPagerItems.with(this);
        Observable.from(paymentTypeList).subscribe(paymentTypeTo -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("TypeList", (Serializable) paymentTypeList);
            creator.add(paymentTypeTo.getTitle(), SelectWashFragment.class, bundle);

        });
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getSupportFragmentManager(), creator.create());
        viewPager.setAdapter(adapter);
        tab.setViewPager(viewPager);


    }
}
