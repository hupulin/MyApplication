package com.hzxm.wolaixish.main.present;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.hzxm.wolaixish.base.BaseActivity;
import com.hzxm.wolaixish.base.BasePresenter;
import com.hzxm.wolaixish.base.MyObserver;


import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.WashApi;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.mall.OrderIdParam;
import hzxmkuar.com.applibrary.domain.wash.WashDetailTo;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 1ONE on 2019/5/29.
 */

public class WashDetailPresenter extends BasePresenter {

    public WashDetailPresenter(BaseActivity activity){
        initContext(activity);
        getOrderDetail();
    }

    private void getOrderDetail() {
       showLoadingDialog();
        OrderIdParam param=new OrderIdParam();
        param.setOrder_id(activity.getIntent().getIntExtra("OrderId",0)+"");
        param.setHash(getHashString(OrderIdParam.class,param));
        ApiClient.create(WashApi.class).getOrderDetailSend(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageTo>(this) {
                    @Override
                    public void onNext(MessageTo msg) {
                        if (msg.getCode()==0) {
                            WashDetailTo detailTo = new Gson().fromJson(JSON.toJSONString(msg.getData()), WashDetailTo.class);
                           getDataSuccess(detailTo);
                        }else
                            showMessage(msg.getMsg());
                    }
                }
        );
    }
}
