package com.xmkj.washmall.mall.presenter;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.BasePresenter;
import com.xmkj.washmall.base.MyObserver;

import java.util.List;

import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.OrderApi;
import hzxmkuar.com.applibrary.domain.MessageListTo;
import hzxmkuar.com.applibrary.domain.mall.CanUseCouponParam;
import hzxmkuar.com.applibrary.domain.myself.CouponTo;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 1ONE on 2019/7/1.
 */

public class SelectCouponPresenter extends BasePresenter {
    private int orderId;
    private int type;
    public int couponCount;
    public SelectCouponPresenter(BaseActivity activity){
        initContext(activity);

    }
    public void getCouponList(int orderId,int type){
        this.orderId=orderId;
        this.type=type;
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
                            List<CouponTo>couponList=new Gson().fromJson(JSON.toJSONString(msg.getDataList()), new TypeToken<List<CouponTo>>() {
                            }.getType());
                            setRecycleList(couponList);
                            couponCount=couponList.size();
                            getDataSuccess(couponList.size());

                        }  else
                            showMessage(msg.getMsg());
                    }
                }
        );
    }

    @Override
    public void recycleViewRefresh() {
        super.recycleViewRefresh();
        getCouponList(orderId,type);
    }

}
