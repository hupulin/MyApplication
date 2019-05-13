package com.xmkj.washmall.wash.presenter;

import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.BasePresenter;
import com.xmkj.washmall.base.MyObserver;

import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.WashApi;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.wash.EvaluateOrderParam;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by xzz on 2019/5/12.
 */

public class EvaluateWashPresenter extends BasePresenter {

    public EvaluateWashPresenter(BaseActivity activity){
        initContext(activity);
    }

    public void evaluate(String content){
        showLoadingDialog();
        EvaluateOrderParam param=new EvaluateOrderParam();
        param.setOrder_id(activity.getIntent().getStringExtra("OrderId"));
        param.setComment(content);
        param.setHash(getHashString(EvaluateOrderParam.class,param));
        ApiClient.create(WashApi.class).evaluateOrder(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageTo>(this) {
                    @Override
                    public void onNext(MessageTo msg) {
                        if (msg.getCode()==0){
                            submitDataSuccess(msg);
                        }else
                            showMessage(msg.getMsg());
                    }
                }
        );
    }
}
