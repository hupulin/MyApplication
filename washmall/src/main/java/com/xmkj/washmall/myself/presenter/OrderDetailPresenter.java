package com.xmkj.washmall.myself.presenter;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.BasePresenter;
import com.xmkj.washmall.base.MyObserver;

import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.OrderApi;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.mall.OrderDetailTo;
import hzxmkuar.com.applibrary.domain.mall.OrderIdParam;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by xzz on 2019/5/1.
 */

public class OrderDetailPresenter extends BasePresenter {

    public OrderDetailPresenter(BaseActivity activity){
        initContext(activity);
        getGoodsDetail();
    }

    private void getGoodsDetail() {
    showLoadingDialog();
        OrderIdParam param=new OrderIdParam();
        param.setOrder_id(activity.getIntent().getStringExtra("OrderId"));
        param.setHash(getHashString(OrderIdParam.class,param));
        ApiClient.create(OrderApi.class).orderDetail(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageTo>(this) {
                    @Override
                    public void onNext(MessageTo msg) {
                        if (msg.getCode()==0)
                            getDataSuccess(new Gson().fromJson(JSON.toJSONString(msg.getData()), OrderDetailTo.class));
                        else
                            showMessage(msg.getMsg());
                    }
                }
        );
    }
}
