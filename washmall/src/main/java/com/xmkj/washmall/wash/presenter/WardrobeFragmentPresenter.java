package com.xmkj.washmall.wash.presenter;

import android.os.Handler;

import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.BaseFragment;
import com.xmkj.washmall.base.BasePresenter;
import com.xmkj.washmall.base.MyObserver;
import com.xmkj.washmall.wash.WardrobeActivity;

import java.util.ArrayList;
import java.util.List;

import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.MainHomeApi;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.wardrobe.WardrobeDetailParam;
import hzxmkuar.com.applibrary.domain.wardrobe.WardrobeDetailTo;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/12/26.
 **/

public class WardrobeFragmentPresenter extends BasePresenter {

    public WardrobeFragmentPresenter(BaseFragment fragment){
        initContext(fragment);
        getWardrobeList();
    }

    public void getWardrobeList(){
        List<WardrobeDetailTo.GridListBean>wardrobeList=new ArrayList<>();
        if (FragmentPagerItem.getPosition(mFragment.getArguments())==0)
            Observable.from(((WardrobeActivity)mFragment.getActivity()).mode.getGrid_list()).subscribe(wardrobeList::add);
            else
        Observable.from(((WardrobeActivity)mFragment.getActivity()).mode.getGrid_list()).filter(gridListBean -> ((WardrobeActivity)mFragment.getActivity()).tagList.get(FragmentPagerItem.getPosition(mFragment.getArguments())).equals(gridListBean.getFloor_no()+"层")).subscribe(wardrobeList::add);

        new Handler().postDelayed(() -> mFragment.getActivity().runOnUiThread(() -> setRecycleList(wardrobeList)),100);
    }
}
