package com.hzxm.wolaixish.main.present;

import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.hzxm.wolaixish.base.BaseActivity;
import com.hzxm.wolaixish.base.BaseFragment;
import com.hzxm.wolaixish.base.BasePresenter;
import com.hzxm.wolaixish.base.MyObserver;
import com.hzxm.wolaixish.base.UserInfoHelp;
import com.hzxm.wolaixish.main.fragment.MainFragment;

import java.util.ArrayList;
import java.util.List;

import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.LoginApi;
import hzxmkuar.com.applibrary.api.MainHomeApi;
import hzxmkuar.com.applibrary.api.deliveryApi;
import hzxmkuar.com.applibrary.domain.MessageListTo;
import hzxmkuar.com.applibrary.domain.MessageTo;

import hzxmkuar.com.applibrary.domain.delivery.main.DeLiveryOrderListTo;
import hzxmkuar.com.applibrary.domain.delivery.main.DeliveryOrderlistParam;
import hzxmkuar.com.applibrary.domain.mall.GoodsDetailTo;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2019/1/14.
 */

public class MainPresenter extends BasePresenter {
    private List<DeLiveryOrderListTo.ListsEntity> Orderlist=new ArrayList<>();

    public MainPresenter(BaseFragment activity) {
        initContext(activity);
    }
    public void getOrderList (int page){
        DeliveryOrderlistParam param=new DeliveryOrderlistParam();
        param.setUid(userInfoTo.getUid());
        param.setHashid(userInfoTo.getHashid());
        param.setPage(page);
        param.setHash(getHashString(DeliveryOrderlistParam.class,param));
        showLoadingDialog();
        ApiClient.create(deliveryApi.class).getDeliveryOrderlist(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageTo<DeLiveryOrderListTo>>(this) {
                    @Override
                    public void onNext(MessageTo<DeLiveryOrderListTo> msg) {
                        if (msg.getCode()==0){
                            if (recyclePageIndex==1)
                                Orderlist.clear();
                            if (msg.getData().getLists()!=null)
                                Orderlist.addAll(msg.getData().getLists());
                            setRecycleList(Orderlist);
                        }
                    }
                }
        );
    }

    @Override
    public void recycleViewLoadMore() {
        super.recycleViewLoadMore();
        getOrderList(recyclePageIndex);
    }

    @Override
    public void recycleViewRefresh() {
        super.recycleViewRefresh();
        getOrderList(recyclePageIndex);
    }


}