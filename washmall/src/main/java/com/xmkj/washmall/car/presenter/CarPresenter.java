package com.xmkj.washmall.car.presenter;

import android.os.Handler;

import com.xmkj.washmall.base.BaseFragment;
import com.xmkj.washmall.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import hzxmkuar.com.applibrary.domain.car.GoodsCarTo;
import hzxmkuar.com.applibrary.domain.main.MallGoodsTo;

/**
 * Created by Administrator on 2018/12/27.
 */

public class CarPresenter extends BasePresenter{
    private List<GoodsCarTo> goodsList=new ArrayList<>();
    public CarPresenter(BaseFragment fragment){
        initContext(fragment);
        getGoodsList();
    }

    public void getGoodsList(){

        for (int i=0;i<4;i++) {
            GoodsCarTo goodsTo = new GoodsCarTo();
            goodsList.add(goodsTo);
        }

        new Handler().postDelayed(() -> setRecycleList(goodsList),500);
    }
}
