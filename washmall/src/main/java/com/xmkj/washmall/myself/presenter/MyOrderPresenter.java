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
import hzxmkuar.com.applibrary.api.OrderApi;
import hzxmkuar.com.applibrary.domain.MessageListTo;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.order.MallOrderTo;
import hzxmkuar.com.applibrary.domain.order.MyOrderParam;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/12/26.
 */

public class MyOrderPresenter extends BasePresenter {
     private List<MallOrderTo>orderList=new ArrayList<>();
     private int type;
    public MyOrderPresenter(BaseFragment fragment){
        initContext(fragment);
    }

    public void getOrderList(int index){
        type=index;
        MyOrderParam param=new MyOrderParam();
        param.setPage(recyclePageIndex);
        param.setOtype(index);
        param.setUid(userInfoTo.getUid());
        param.setHashid(userInfoTo.getHashid());
        param.setHash(getHashString(MyOrderParam.class,param));
        showLoadingDialog();
        ApiClient.create(OrderApi.class).myMallOrderList(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageListTo>(this) {
                    @Override
                    public void onNext(MessageListTo msg) {
                        if (msg.getCode()==0){
                            if (recyclePageIndex==1)
                                orderList.clear();
                            orderList.addAll(new Gson().fromJson(JSON.toJSONString(msg.getDataList()),new TypeToken<List<MallOrderTo>>(){}.getType()));
                           setRecycleList(orderList);
                        }
                        else
                            showMessage(msg.getMsg());
                    }
                }
        );

    }

    @Override
    public void recycleViewRefresh() {
        super.recycleViewRefresh();
        getOrderList(type);
    }
}
