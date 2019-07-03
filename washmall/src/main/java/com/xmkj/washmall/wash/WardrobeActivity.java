package com.xmkj.washmall.wash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.ruffian.library.RTextView;
import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.util.StatueBarUtil;
import com.xmkj.washmall.wash.fragment.WardrobeFragment;
import com.xmkj.washmall.wash.presenter.WardrobePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hzxmkuar.com.applibrary.domain.wardrobe.WardrobeDetailTo;
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
    public WardrobeDetailTo mode;
    public List<String> tagList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wardrobe);
        ButterKnife.bind(this);
        StatueBarUtil.setStatueBarTextWhite(getWindow());
        setTitleName(getIntent().getStringExtra("WardrobeName"));

        WardrobePresenter presenter = new WardrobePresenter(this);

    }

    private void setView() {
        displayImage(wardrobeImage,mode.getWardrobe_img());
        address.setText(mode.getAddress());
        wardrobeNum.setText("空闲：  "+mode.getFree_grid_num());
        wardrobeName.setText(mode.getWardrobe_name());

    }


    public void setTab(List<String>  paymentTypeList) {

        FragmentPagerItems.Creator creator = FragmentPagerItems.with(this);
        Observable.from(paymentTypeList).subscribe(paymentTypeTo -> {
            Bundle bundle = new Bundle();

            creator.add(paymentTypeTo, WardrobeFragment.class, bundle);

        });
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getSupportFragmentManager(), creator.create());
        viewPager.setAdapter(adapter);
        tab.setViewPager(viewPager);


    }

    @Override
    public void loadDataSuccess(Object data) {
        mode = new Gson().fromJson(JSON.toJSONString(data),WardrobeDetailTo.class);
        setView();
        tagList = new ArrayList<>();
        tagList.add("全部");
        Observable.from(mode.getGrid_list()).subscribe(gridListBean -> {
            if (!tagList.contains(gridListBean.getFloor_no()+"层"))
                tagList.add(gridListBean.getFloor_no()+"层");
        });
        setTab(tagList);
    }
}
