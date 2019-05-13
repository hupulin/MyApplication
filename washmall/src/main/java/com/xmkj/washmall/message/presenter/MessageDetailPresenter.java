package com.xmkj.washmall.message.presenter;

import com.alibaba.fastjson.JSON;
import com.google.android.gms.common.api.Api;
import com.google.gson.Gson;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.BasePresenter;
import com.xmkj.washmall.base.MyObserver;

import hzxmkuar.com.applibrary.IdParam;
import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.MainHomeApi;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.message.SystemMessageTo;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by xzz on 2019/5/12.
 */

public class MessageDetailPresenter extends BasePresenter {

    public MessageDetailPresenter(BaseActivity activity){
        initContext(activity);
        getMessageDetail();
    }

    public void getMessageDetail(){
     showLoadingDialog();
        IdParam param=new IdParam();
        param.setId(activity.getIntent().getIntExtra("MessageId",0));
        param.setHash(getHashString(IdParam.class,param));
        ApiClient.create(MainHomeApi.class).getMessageDetail(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageTo>(this) {
                    @Override
                    public void onNext(MessageTo msg) {
                        if (msg.getCode()==0){
                            getDataSuccess(new Gson().fromJson(JSON.toJSONString(msg.getData()), SystemMessageTo.class));
                        }else
                            showMessage(msg.getMsg());
                    }
                }
        );
    }
}
