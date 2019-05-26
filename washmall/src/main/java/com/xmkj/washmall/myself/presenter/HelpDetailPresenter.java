package com.xmkj.washmall.myself.presenter;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.BasePresenter;
import com.xmkj.washmall.base.MyObserver;

import hzxmkuar.com.applibrary.IdParam;
import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.MyselfApi;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.myself.HelpDetailTo;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 1ONE on 2019/5/26.
 */

public class HelpDetailPresenter extends BasePresenter {

    public HelpDetailPresenter(BaseActivity activity){
        initContext(activity);
        getHelpDetail();
    }

    private void getHelpDetail() {
        showLoadingDialog();
        IdParam param=new IdParam();
        param.setId(activity.getIntent().getIntExtra("Id",0));
        param.setHash(getHashStringNoUser(IdParam.class,param));
        ApiClient.create(MyselfApi.class).getHelpDetail(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageTo>(this) {
                    @Override
                    public void onNext(MessageTo msg) {
                        if (msg.getCode()==0)
                            getDataSuccess(new Gson().fromJson(JSON.toJSONString(msg.getData()), HelpDetailTo.class));
                        else showMessage(msg.getMsg());
                    }
                }
        );
    }
}
