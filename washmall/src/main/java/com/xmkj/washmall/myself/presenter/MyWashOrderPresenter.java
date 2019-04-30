package com.xmkj.washmall.myself.presenter;

import android.os.Handler;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xmkj.washmall.base.BaseFragment;
import com.xmkj.washmall.base.BasePresenter;
import com.xmkj.washmall.base.MyObserver;

import java.util.ArrayList;
import java.util.List;

import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.WashApi;
import hzxmkuar.com.applibrary.domain.MessageListTo;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.mall.OrderIdParam;
import hzxmkuar.com.applibrary.domain.mall.OrderIdTo;
import hzxmkuar.com.applibrary.domain.order.MallOrderTo;
import hzxmkuar.com.applibrary.domain.order.MyOrderParam;
import hzxmkuar.com.applibrary.domain.order.WashOrderTo;
import hzxmkuar.com.applibrary.domain.wash.MyWashOrderParam;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/12/26.
 */

public class MyWashOrderPresenter extends BasePresenter {
     private List<WashOrderTo>orderList=new ArrayList<>();
    private int index;
    public MyWashOrderPresenter(BaseFragment fragment){
        initContext(fragment);
    }

    public void getOrderList(int index){
        MyWashOrderParam param=new MyWashOrderParam();
        param.setOrder_type(index);
        this.index=index;
        param.setPage(recyclePageIndex);
        param.setUid(userInfoTo.getUid());
        param.setHashid(userInfoTo.getHashid());
        param.setHash(getHashString(MyWashOrderParam.class,param));
        showLoadingDialog();
        ApiClient.create(WashApi.class).getOrderList(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageListTo>(this) {
                    @Override
                    public void onNext(MessageListTo msg) {
                        if (msg.getCode()==0){
                            if (recyclePageIndex==1)
                                orderList.clear();
                            orderList.addAll(new Gson().fromJson(JSON.toJSONString(msg.getDataList()),new TypeToken<List<WashOrderTo>>(){}.getType()));
                       setRecycleList(orderList);
                        }else
                            showMessage(msg.getMsg());

                    }
                }
        );

    }

    public void cancelOrder(int orderId){
        OrderIdParam param=new OrderIdParam();
        param.setOrder_id(orderId+"");
        param.setHash(getHashString(OrderIdParam.class,param));
        showLoadingDialog();
        ApiClient.create(WashApi.class).cancelOrder(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageTo>(this) {
                    @Override
                    public void onNext(MessageTo msg) {
                        if (msg.getCode()==0){

                            showMessage("取消订单成功");
                            getOrderList(index);
                        }else
                            showMessage(msg.getMsg());
                    }
                }
        );
    }
}
