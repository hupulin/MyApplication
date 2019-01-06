package com.xmkj.washmall.main.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.alibaba.fastjson.JSON;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseFragment;
import com.xmkj.washmall.base.banner.HomeBannerHolderView;
import com.xmkj.washmall.databinding.MainWardrobeItemBinding;
import com.xmkj.washmall.main.presenter.MainHomePresenter;
import com.xmkj.washmall.myself.MyOrderActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import hzxmkuar.com.applibrary.domain.main.MainHomeAdTo;
import hzxmkuar.com.applibrary.domain.main.MainWardrobeTo;

/**
 * Created by Administrator on 2018/12/25.
 **/

public class HomeFragment extends BaseFragment {
    @BindView(R.id.banner)
    ConvenientBanner<MainHomeAdTo> banner;
    Unbinder unbinder;
    @BindView(R.id.wardrobe_layout)
    GridLayout wardrobeLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = View.inflate(container.getContext(), R.layout.fragment_home, null);
        unbinder = ButterKnife.bind(this, rootView);
        MainHomePresenter presenter=new MainHomePresenter(this);


        return rootView;
    }

    public void setWardrobe(List<MainWardrobeTo>wardrobeList) {
           wardrobeList=new Gson().fromJson(JSON.toJSONString(wardrobeList),new TypeToken<List<MainWardrobeTo>>(){}.getType());
        for (int i = 0; i < wardrobeList.size(); i++) {
            View mView = View.inflate(appContext, R.layout.main_wardrobe_item, null);
            MainWardrobeTo wardrobeTo = wardrobeList.get(i);
            MainWardrobeItemBinding bind = DataBindingUtil.bind(mView);
            System.out.println(wardrobeTo+"===");
            bind.address.setText(wardrobeTo.getDistance());
            bind.name.setText(wardrobeTo.getWardrobe_name());
            bind.statue.setText(wardrobeTo.getIs_full());
            displayImage(bind.imageView, wardrobeTo.getWardrobe_img());
            wardrobeLayout.addView(mView);
        }
    }


    public void setBanner( List<MainHomeAdTo> adList) {
        banner.setPages(HomeBannerHolderView::new, adList).setPageIndicator(new int[]{R.drawable.page_indicate_un_focus, R.drawable.page_indicate_focus});
        banner.startTurning(5000);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.wash, R.id.recharge, R.id.order, R.id.scan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.wash:
                Intent intent=new Intent();
                break;
            case R.id.recharge:
                break;
            case R.id.order:
                intent=new Intent(appContext, MyOrderActivity.class);
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.scan:
                break;
        }
    }

    @Override
    public void loadDataSuccess(Object data) {
        List<MainHomeAdTo>adList= (List<MainHomeAdTo>) data;
       setBanner(adList);
    }


}
