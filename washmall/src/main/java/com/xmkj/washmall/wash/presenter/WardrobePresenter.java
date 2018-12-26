package com.xmkj.washmall.wash.presenter;

import android.os.Handler;

import com.xmkj.washmall.base.BaseFragment;
import com.xmkj.washmall.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import hzxmkuar.com.applibrary.domain.main.MainWardrobeTo;

/**
 * Created by Administrator on 2018/12/26.
 */

public class WardrobePresenter extends BasePresenter {

    public WardrobePresenter(BaseFragment fragment){
        initContext(fragment);
        getWardrobe();
    }

    public void getWardrobe(){
        List<MainWardrobeTo> wardrobeList=new ArrayList<>();
        wardrobeList.add(new MainWardrobeTo());
        wardrobeList.add(new MainWardrobeTo());
        wardrobeList.add(new MainWardrobeTo());
        new Handler().postDelayed(() ->setRecycleList(wardrobeList) ,500);
    }
}
