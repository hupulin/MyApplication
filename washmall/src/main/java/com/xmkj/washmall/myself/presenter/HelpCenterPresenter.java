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
import hzxmkuar.com.applibrary.domain.MessageListTo;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.inquery.HelpCenterParam;
import hzxmkuar.com.applibrary.domain.inquery.KeyWordParam;
import hzxmkuar.com.applibrary.domain.myself.HelpCenterTo;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 1ONE on 2019/5/26.
 */

public class HelpCenterPresenter extends BasePresenter {

    public HelpCenterPresenter(BaseActivity activity){
        initContext(activity);
        getHelpData("",true);
    }

    public void getHelpData(String search,boolean showLoading){
        HelpCenterParam param=new HelpCenterParam();
        param.setKeywords(search);
        param.setHash(getHashString(HelpCenterParam.class,param));
        if (showLoading)
            showLoadingDialog();
        ApiClient.create(MyselfApi.class).getHelpData(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageListTo>(this) {
                    @Override
                    public void onNext(MessageListTo msg) {
                        if (msg.getCode()==0)
                            setRecycleList(new Gson().fromJson(JSON.toJSONString(msg.getDataList()),new TypeToken<List<HelpCenterTo>>(){}.getType()));
                    }
                }
        );
    }

    @Override
    public void recycleViewRefresh() {
        super.recycleViewRefresh();
        getHelpData("",true);
    }
}
