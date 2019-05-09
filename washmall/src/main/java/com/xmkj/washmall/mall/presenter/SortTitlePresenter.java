package com.xmkj.washmall.mall.presenter;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.BasePresenter;
import com.xmkj.washmall.base.MyObserver;

import java.util.List;

import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.MallApi;
import hzxmkuar.com.applibrary.domain.MessageListTo;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.mall.CatePidParam;
import hzxmkuar.com.applibrary.domain.mall.MallChildTypeTo;
import hzxmkuar.com.applibrary.domain.mall.MallTypeTo;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by xzz on 2019/5/9.
 */

public class SortTitlePresenter extends BasePresenter {

    public SortTitlePresenter(BaseActivity activity){
        initContext(activity);
        getTitle();
    }

    private void getTitle() {
        showLoadingDialog();
        CatePidParam param=new CatePidParam();
        param.setCate_pid(activity.getIntent().getIntExtra("CateId",0));
        param.setHash(getHashStringNoUser(CatePidParam.class,param));
        ApiClient.create(MallApi.class).getTitleList(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageListTo>(this) {
                    @Override
                    public void onNext(MessageListTo msg) {
                        if (msg.getCode()==0){
                            getDataSuccess(new Gson().fromJson(JSON.toJSONString(msg.getDataList()),new TypeToken<List<MallChildTypeTo>>(){}.getType()));
                        }else
                            showMessage(msg.getMsg());
                    }
                }
        );
    }
}
