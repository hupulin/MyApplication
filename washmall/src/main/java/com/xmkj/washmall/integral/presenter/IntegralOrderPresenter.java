package com.xmkj.washmall.integral.presenter;

import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.BasePresenter;
import com.xmkj.washmall.base.MyObserver;

import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.IntegralApi;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.integral.AddIntegralOrderParam;
import hzxmkuar.com.applibrary.domain.integral.IntegralGoodsTo;
import hzxmkuar.com.applibrary.domain.mall.GoodsIdParam;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2019/1/8.
 */

public class IntegralOrderPresenter extends BasePresenter {
    public IntegralOrderPresenter(BaseActivity activity){
        initContext(activity);
        getOrderInfo();
    }

    private void getOrderInfo(){
        GoodsIdParam param=new GoodsIdParam();
        param.setUid(userInfoTo.getUid());
        param.setHashid(userInfoTo.getHashid());
        param.setGoods_id(activity.getIntent().getIntExtra("GoodsId",0));
        param.setHash(getHashString(GoodsIdParam.class,param));
        showLoadingDialog();
        ApiClient.create(IntegralApi.class).confirmOrder(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
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

    public void addOrder(String remark,int addressId){
        AddIntegralOrderParam param=new AddIntegralOrderParam();
        param.setUid(userInfoTo.getUid());
        param.setHashid(userInfoTo.getHashid());
        param.setGoods_id(activity.getIntent().getIntExtra("GoodsId",0));
        param.setRemarks(remark);
        param.setAddress_id(addressId);
        param.setHash(getHashString(AddIntegralOrderParam.class,param));
        showLoadingDialog();
        ApiClient.create(IntegralApi.class).addOrder(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageTo>(this) {
                    @Override
                    public void onNext(MessageTo msg) {
                        if (msg.getCode()==0)
                           submitDataSuccess(msg);
                        else
                            showMessage(msg.getMsg());
                    }
                }
        );
    }
}
