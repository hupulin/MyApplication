package com.xmkj.washmall.myself.presenter;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.BasePresenter;
import com.xmkj.washmall.base.MyObserver;

import java.util.List;

import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.MyselfApi;
import hzxmkuar.com.applibrary.api.OrderApi;
import hzxmkuar.com.applibrary.domain.BaseParam;
import hzxmkuar.com.applibrary.domain.MessageListTo;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.myself.ExchangeInfoTo;
import hzxmkuar.com.applibrary.domain.myself.ExchangePayParam;
import hzxmkuar.com.applibrary.domain.order.WeChatPayTo;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by xzz on 2019/4/29.
 */

public class ExchangePresenter extends BasePresenter {

    public ExchangePresenter(BaseActivity activity) {
        initContext(activity);
        getExchangeList();
    }

    private void getExchangeList() {
        BaseParam param = new BaseParam();
        param.setHash(getHashString(BaseParam.class, param));
        ApiClient.create(MyselfApi.class).getExchangeList(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageListTo>(this) {
                    @Override
                    public void onNext(MessageListTo msg) {
                        if (msg.getCode() == 0) {
                            getDataSuccess(new Gson().fromJson(JSON.toJSONString(msg.getDataList()), new TypeToken<List<ExchangeInfoTo>>() {
                            }.getType()));
                        } else
                            showMessage(msg.getMsg());
                    }
                }
        );
    }

    public void pay(String packageId, int payType) {
        ExchangePayParam param = new ExchangePayParam();
        param.setPay_type(payType + "");
        param.setPackage_id(packageId);
        param.setHash(getHashString(ExchangePayParam.class, param));
        showLoadingDialog();
        if (payType==2) {
            ApiClient.create(MyselfApi.class).exchangeWx(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                    new MyObserver<MessageTo<WeChatPayTo>>(this) {
                        @Override
                        public void onNext(MessageTo<WeChatPayTo> msg) {
                            if (msg.getCode() == 0)
                                submitDataSuccess(msg.getData());
                            else
                                showMessage(msg.getMsg());
                        }
                    }
            );
        }else {
            ApiClient.create(MyselfApi.class).exchange(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                    new MyObserver<MessageTo>(this) {
                        @Override
                        public void onNext(MessageTo msg) {
                            if (msg.getCode() == 0)
                                submitDataSuccess(msg.getData());
                            else
                                showMessage(msg.getMsg());
                        }
                    }
            );
        }
    }
}
