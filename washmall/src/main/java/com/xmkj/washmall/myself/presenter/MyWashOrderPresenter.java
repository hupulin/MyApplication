package com.xmkj.washmall.myself.presenter;

import android.os.Handler;

import com.xmkj.washmall.base.BaseFragment;
import com.xmkj.washmall.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import hzxmkuar.com.applibrary.domain.order.MallOrderTo;
import hzxmkuar.com.applibrary.domain.order.WashOrderTo;

/**
 * Created by Administrator on 2018/12/26.
 */

public class MyWashOrderPresenter extends BasePresenter {
     private List<WashOrderTo>orderList=new ArrayList<>();
    public MyWashOrderPresenter(BaseFragment fragment){
        initContext(fragment);
    }

    public void getOrderList(int index){
        WashOrderTo orderTo = new WashOrderTo();
        orderTo.setType(index);
        orderList.add(orderTo);
        orderList.add(orderTo);
        orderList.add(orderTo);
        new Handler().postDelayed(() -> {
            setRecycleList(orderList);
        },1000);

    }
}