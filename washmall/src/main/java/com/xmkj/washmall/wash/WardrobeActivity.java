package com.xmkj.washmall.wash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.ruffian.library.RTextView;
import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.util.StatueBarUtil;
import com.xmkj.washmall.wash.fragment.WardrobeFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hzxmkuar.com.applibrary.domain.wash.WardrobeTo;
import rx.Observable;
import util.smart.SmartTabLayout;

/**
 * Created by Administrator on 2018/12/26.
 **/

public class WardrobeActivity extends BaseActivity {
    @BindView(R.id.wardrobe_image)
    ImageView wardrobeImage;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.wardrobe_name)
    RTextView wardrobeName;
    @BindView(R.id.wardrobe_num)
    RTextView wardrobeNum;
    @BindView(R.id.tab)
    SmartTabLayout tab;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    private WardrobeTo wardrobeTo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wardrobe);
        ButterKnife.bind(this);
        StatueBarUtil.setStatueBarTextWhite(getWindow());
        setTitleName("1号智能柜");
        wardrobeTo = new WardrobeTo();
        setView();
        setTab(wardrobeTo.getTagList());
    }

    private void setView() {
        displayImage(wardrobeImage,wardrobeTo.getImageUrl());
        address.setText(wardrobeTo.getAddress());
        wardrobeNum.setText(wardrobeTo.getNum());
        wardrobeName.setText(wardrobeTo.getName());

    }


    public void setTab(List<String> paymentTypeList) {

        FragmentPagerItems.Creator creator = FragmentPagerItems.with(this);
        Observable.from(paymentTypeList).subscribe(paymentTypeTo -> {
            Bundle bundle = new Bundle();

            creator.add(paymentTypeTo, WardrobeFragment.class, bundle);

        });
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getSupportFragmentManager(), creator.create());
        viewPager.setAdapter(adapter);
        tab.setViewPager(viewPager);


    }
}
