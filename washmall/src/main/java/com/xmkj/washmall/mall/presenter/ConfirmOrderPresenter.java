package com.xmkj.washmall.mall.presenter;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.BasePresenter;
import com.xmkj.washmall.base.MyObserver;

import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.OrderApi;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.mall.SettlementIdParam;
import hzxmkuar.com.applibrary.domain.order.AddOrderParam;
import hzxmkuar.com.applibrary.domain.order.ConfirmOrderInfoTo;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2019/1/7.
 */

public class ConfirmOrderPresenter extends BasePresenter {

    public ConfirmOrderPresenter(BaseActivity activity){
        initContext(activity);
        getOrderInfo();
    }
    private void getOrderInfo(){
        SettlementIdParam param=new SettlementIdParam();
        param.setSettlement_ids(activity.getIntent().getStringExtra("SettlementId"));
        param.setUid(userInfoTo.getUid());
        param.setHashid(userInfoTo.getHashid());
        param.setHash(getHashString(SettlementIdParam.class,param));
        showLoadingDialog();
        ApiClient.create(OrderApi.class).getOrderInfo(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageTo>(this) {
                    @Override
                    public void onNext(MessageTo msg) {
                        if (msg.getCode()==0)
                            getDataSuccess(msg.getData());
                        else
                            showMessage(msg.getMsg());
                    }
                }
        );
    }

    public void addOrder(int addressId,String remark){
        AddOrderParam param=new AddOrderParam();
        param.setUid(userInfoTo.getUid());
        param.setHashid(userInfoTo.getHashid());
        param.setAddress_id(addressId);
        param.setSettlement_ids(activity.getIntent().getStringExtra("SettlementId"));
        param.setRemarks(remark);
        param.setHash(getHashString(AddOrderParam.class,param));
        showLoadingDialog();
        ApiClient.create(OrderApi.class).addOrder(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageTo>(this) {
                    @Override
                    public void onNext(MessageTo msg) {
                        if (msg.getCode()==0)
                            submitDataSuccess(msg.getData());
                        else
                            showMessage(msg.getMsg());
                    }
                }
        );
    }
}
