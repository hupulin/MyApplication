package com.xmkj.washmall.myself.presenter;

import android.os.Handler;

import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import hzxmkuar.com.applibrary.domain.car.GoodsCarTo;

/**
 * Created by Administrator on 2018/12/28.
 */

public class MyCollectPresenter extends BasePresenter {
    private List<GoodsCarTo> goodsList = new ArrayList<>();

    public MyCollectPresenter(BaseActivity activity) {
        initContext(activity);
        getGoodsList();
    }

    public void getGoodsList() {

        for (int i = 0; i < 4; i++) {
            GoodsCarTo goodsTo = new GoodsCarTo();
            goodsList.add(goodsTo);
        }

        new Handler().postDelayed(() -> setRecycleList(goodsList), 500);
    }

}
