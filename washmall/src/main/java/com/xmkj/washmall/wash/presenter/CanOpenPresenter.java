package com.xmkj.washmall.wash.presenter;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xmkj.washmall.base.BaseActivity;
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
import hzxmkuar.com.applibrary.domain.order.WashOrderTo;
import hzxmkuar.com.applibrary.domain.wardrobe.OpenWardrobeParam;
import hzxmkuar.com.applibrary.domain.wash.CanOpenParam;
import hzxmkuar.com.applibrary.domain.wash.MyWashOrderParam;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/12/26.
 */

public class CanOpenPresenter extends BasePresenter {
    private List<WashOrderTo> orderList = new ArrayList<>();

    public CanOpenPresenter(BaseActivity activity) {
        initContext(activity);
        getOrderList();
    }

    public void getOrderList() {
        CanOpenParam param = new CanOpenParam();
        param.setWardrobe_no(activity.getIntent().getStringExtra("WardrobeNo"));
        param.setPage(recyclePageIndex);
        param.setHash(getHashString(CanOpenParam.class, param));
        showLoadingDialog();
        ApiClient.create(WashApi.class).getCanOpenList(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageListTo>(this) {
                    @Override
                    public void onNext(MessageListTo msg) {
                        if (msg.getCode() == 0) {
                            if (recyclePageIndex == 1)
                                orderList.clear();
                            orderList.addAll(new Gson().fromJson(JSON.toJSONString(msg.getDataList()), new TypeToken<List<WashOrderTo>>() {
                            }.getType()));
                            setRecycleList(orderList);
                            getDataSuccess(orderList);
                        } else
                            showMessage(msg.getMsg());

                    }
                }
        );

    }


    @Override
    public void recycleViewLoadMore() {
        super.recycleViewLoadMore();
        getOrderList();
    }

    @Override
    public void recycleViewRefresh() {
        super.recycleViewRefresh();
        getOrderList();
    }


    public void openWardrobe(String orderId){
        showLoadingDialog();
        OpenWardrobeParam param=new OpenWardrobeParam();
        param.setOrder_id(orderId);
        param.setWardrobe_no(activity.getIntent().getStringExtra("WardrobeNo"));
        param.setHash(getHashString(OpenWardrobeParam.class,param));
        ApiClient.create(WashApi.class).openWardrobe(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageTo>(this) {
                    @Override
                    public void onNext(MessageTo msg) {
                        if (msg.getCode()==0){
                            showMessage("开箱成功");
                            submitDataSuccess(msg.getData());
                        }else
                            showMessage(msg.getMsg());
                    }
                }
        );
    }
}
