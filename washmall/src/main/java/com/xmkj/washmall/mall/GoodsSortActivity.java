package com.xmkj.washmall.mall;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;

import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.mall.fragment.GoodsSortFragment;
import com.xmkj.washmall.wash.fragment.SelectWashFragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import util.smart.SmartTabLayout;

/**
 * Created by Administrator on 2018/12/27.
 */

public class GoodsSortActivity extends BaseActivity {
    @BindView(R.id.tab)
    SmartTabLayout tab;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_sort);
        ButterKnife.bind(this);
        setTitleName("洗衣液");
        setTab(new ArrayList<>(Arrays.asList("全部", "桶装洗衣液", "袋装洗衣液", "儿童")));
    }

    public void setTab(List<String> paymentTypeList) {

        FragmentPagerItems.Creator creator = FragmentPagerItems.with(this);
        Observable.from(paymentTypeList).subscribe(paymentTypeTo -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("TypeList", (Serializable) paymentTypeList);
            creator.add(paymentTypeTo, GoodsSortFragment.class, bundle);

        });
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getSupportFragmentManager(), creator.create());
        viewPager.setAdapter(adapter);
        tab.setViewPager(viewPager);


    }
}
