package com.xmkj.washmall.wash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;


import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.wash.fragment.SelectWashFragment;
import com.xmkj.washmall.wash.presenter.SelectWashPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hzxmkuar.com.applibrary.domain.wash.WashInfoTo;
import rx.Observable;
import util.smart.SmartTabLayout;

/**
 * Created by Administrator on 2018/12/26.
 **/

public class SelectWashActivity extends BaseActivity {
    @BindView(R.id.tab)
    SmartTabLayout tab;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    public List<String>typeList=new ArrayList<>();
    public HashMap<String,List<WashInfoTo>>washItemMap=new HashMap<>();
    public HashMap<String,List<WashInfoTo>>washInfoMap=new HashMap<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_wash);
        ButterKnife.bind(this);
        setTitleName("选择下单种类");




        SelectWashPresenter presenter=new SelectWashPresenter(this);
    }

    public void setTab(List<String> paymentTypeList) {

        FragmentPagerItems.Creator creator = FragmentPagerItems.with(this);
        Observable.from(paymentTypeList).subscribe(paymentTypeTo -> {
            Bundle bundle = new Bundle();
            creator.add(paymentTypeTo, SelectWashFragment.class, bundle);

        });
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getSupportFragmentManager(), creator.create());
        viewPager.setAdapter(adapter);
        tab.setViewPager(viewPager);
        viewPager.setOffscreenPageLimit(paymentTypeList.size());

    }

    @Override
    public void loadDataSuccess(Object data) {
        List<WashInfoTo>washList=new Gson().fromJson(JSON.toJSONString(data),new TypeToken<List<WashInfoTo>>(){}.getType());
        Observable.from(washList).subscribe(washInfoTo -> {

                typeList.add(washInfoTo.getService_name());
                washItemMap.put(washInfoTo.getService_name(),washInfoTo.getList2());
                Observable.from(washInfoTo.getList2()).subscribe(washInfoTo1 -> washInfoMap.put(washInfoTo1.getService_name(),washInfoTo1.getList3()));
                setTab(typeList);

        });

    }
}
