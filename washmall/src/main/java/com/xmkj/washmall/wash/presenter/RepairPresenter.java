package com.xmkj.washmall.wash.presenter;

import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.BasePresenter;
import com.xmkj.washmall.base.MyObserver;

import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.WashApi;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.wash.EvaluateOrderParam;
import hzxmkuar.com.applibrary.domain.wash.RepairParam;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by xzz on 2019/5/12.
 */

public class RepairPresenter extends BasePresenter {

    public RepairPresenter(BaseActivity activity){
        initContext(activity);
    }

    public void repair(String content){
        showLoadingDialog();
        RepairParam param=new RepairParam();
        param.setWardrobe_no(activity.getIntent().getStringExtra("WardrobeNo"));
        param.setContent(content);
        param.setHash(getHashString(RepairParam.class,param));
        ApiClient.create(WashApi.class).repair(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
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
