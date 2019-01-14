package com.xmkj.washmall.integral.presenter;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.BasePresenter;
import com.xmkj.washmall.base.MyObserver;

import java.util.List;

import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.IntegralApi;
import hzxmkuar.com.applibrary.domain.MessageListTo;
import hzxmkuar.com.applibrary.domain.integral.IntegralRecordTo;
import hzxmkuar.com.applibrary.domain.integral.PageParam;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2019/1/12.
 */

public class IntegralRecordPresenter extends BasePresenter {

    public IntegralRecordPresenter(BaseActivity activity){
        initContext(activity);
        getScoreRecord();
    }
    private void getScoreRecord(){
        PageParam param=new PageParam();
        param.setUid(userInfoTo.getUid());
        param.setHashid(userInfoTo.getHashid());
        param.setHash(getHashString(PageParam.class,param));
        showLoadingDialog();
        ApiClient.create(IntegralApi.class).getIntegralRecord(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageListTo>(this) {
                    @Override
                    public void onNext(MessageListTo msg) {
                        if (msg.getCode()==0)
                            setRecycleList(new Gson().fromJson(JSON.toJSONString(msg.getDataList()),new TypeToken<List<IntegralRecordTo>>(){}.getType()));
                        else
                            showMessage(msg.getMsg());
                    }
                }
        );
    }
}
