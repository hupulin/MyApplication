package com.xmkj.washmall.myself.presenter;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.BasePresenter;
import com.xmkj.washmall.base.MyObserver;
import com.xmkj.washmall.mall.SelectPayActivity;

import java.util.List;

import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.OrderApi;
import hzxmkuar.com.applibrary.domain.MessageListTo;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.mall.CanUseCouponParam;
import hzxmkuar.com.applibrary.domain.myself.CouponTo;
import hzxmkuar.com.applibrary.domain.order.PayParam;
import hzxmkuar.com.applibrary.domain.order.WeChatPayTo;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2019/1/7.
 */

public class PayPresenter extends BasePresenter {

    public PayPresenter(BaseActivity activity) {
        initContext(activity);
    }

    public void getPayInfo(int orderId, int payType, int couponId) {
        PayParam param = new PayParam();
        param.setUid(userInfoTo.getUid());
        param.setHashid(userInfoTo.getHashid());
        param.setPay_type(payType);
        param.setOrder_id(orderId);
        param.setCoupon_id(couponId);
        param.setHash(getHashString(PayParam.class, param));
        showLoadingDialog();
        if (payType == 2) {
            ApiClient.create(OrderApi.class).getWxPayInfo(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                    new MyObserver<MessageTo<WeChatPayTo>>(this) {
                        @Override
                        public void onNext(MessageTo<WeChatPayTo> msg) {
                            if (msg.getCode() == 0)
                                getDataSuccess(msg.getData());
                            else
                                showMessage(msg.getMsg());
                        }
                    }
            );
        } else {
            ApiClient.create(OrderApi.class).getPayInfo(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                    new MyObserver<MessageTo>(this) {
                        @Override
                        public void onNext(MessageTo msg) {
                            if (msg.getCode() == 0)
                                getDataSuccess(msg.getData());
                            else
                                showMessage(msg.getMsg());
                        }
                    }
            );
        }
    }

    public void getWashPayInfo(int orderId, int payType, int couponId) {


        PayParam param = new PayParam();
        param.setUid(userInfoTo.getUid());
        param.setHashid(userInfoTo.getHashid());
        param.setPay_type(payType);
        param.setOrder_id(orderId);
        param.setCoupon_id(couponId);
        param.setHash(getHashString(PayParam.class, param));
        showLoadingDialog();

        if (payType == 2) {
            ApiClient.create(OrderApi.class).getWXWashPayInfo(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                    new MyObserver<MessageTo<WeChatPayTo>>(this) {
                        @Override
                        public void onNext(MessageTo<WeChatPayTo> msg) {
                            if (msg.getCode() == 0)
                                getDataSuccess(msg.getData());
                            else
                                showMessage(msg.getMsg());
                        }
                    }
            );
        } else {
            ApiClient.create(OrderApi.class).getWashPayInfo(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                    new MyObserver<MessageTo>(this) {
                        @Override
                        public void onNext(MessageTo msg) {
                            if (msg.getCode() == 0)
                                getDataSuccess(msg.getData());
                            else
                                showMessage(msg.getMsg());
                        }
                    }
            );
        }
    }

    public void getCouponList(int orderId,int type){
        showLoadingDialog();
        CanUseCouponParam param=new CanUseCouponParam();
        param.setOrder_id(orderId);
        param.setUser_type(type);
        param.setHash(getHashString(CanUseCouponParam.class,param));
        ApiClient.create(OrderApi.class).selectCouponList(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageListTo>(this) {
                    @Override
                    public void onNext(MessageListTo msg) {
                        if (msg.getCode()==0) {
                            List<CouponTo> couponList=new Gson().fromJson(JSON.toJSONString(msg.getDataList()), new TypeToken<List<CouponTo>>() {
                            }.getType());


                            ((SelectPayActivity)activity).getCouponSuccess(couponList==null?0:couponList.size());

                        }  else
                            showMessage(msg.getMsg());
                    }
                }
        );
    }
}
