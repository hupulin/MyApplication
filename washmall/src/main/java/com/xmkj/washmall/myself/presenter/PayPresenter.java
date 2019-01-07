package com.xmkj.washmall.myself.presenter;

import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.BasePresenter;
import com.xmkj.washmall.base.MyObserver;

import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.OrderApi;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.order.PayParam;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2019/1/7.
 */

public class PayPresenter extends BasePresenter {

    public PayPresenter(BaseActivity activity){
        initContext(activity);
    }

    public void getPayInfo(int orderId,int payType){
        PayParam param=new PayParam();
        param.setUid(userInfoTo.getUid());
        param.setHashid(userInfoTo.getHashid());
        param.setPay_type(payType);
        param.setOrder_id(orderId);
        param.setHash(getHashString(PayParam.class,param));
        showLoadingDialog();
        ApiClient.create(OrderApi.class).getPayInfo(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
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
}
