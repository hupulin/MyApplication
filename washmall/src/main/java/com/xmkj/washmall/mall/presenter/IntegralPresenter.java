package com.xmkj.washmall.mall.presenter;

import android.os.Handler;

import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.BaseFragment;
import com.xmkj.washmall.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import hzxmkuar.com.applibrary.domain.main.MallGoodsTo;

/**
 * Created by Administrator on 2018/12/27.
 */

public class IntegralPresenter extends BasePresenter {
private List<MallGoodsTo>goodsList=new ArrayList<>();
    public IntegralPresenter(BaseActivity activity){
        initContext(activity);
        getGoodsList();
    }

    public void getGoodsList(){

        for (int i=0;i<4;i++) {
            MallGoodsTo goodsTo = new MallGoodsTo();
            goodsList.add(goodsTo);
        }

        new Handler().postDelayed(() -> setRecycleList(goodsList),500);
    }
}