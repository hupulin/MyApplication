package com.xmkj.washmall.myself.presenter;

import android.os.Handler;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.BasePresenter;
import com.xmkj.washmall.base.MyObserver;

import java.util.ArrayList;
import java.util.List;

import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.MyselfApi;
import hzxmkuar.com.applibrary.domain.MessageListTo;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.car.GoodsCarTo;
import hzxmkuar.com.applibrary.domain.integral.PageParam;
import hzxmkuar.com.applibrary.domain.myself.CollectTo;
import hzxmkuar.com.applibrary.domain.myself.DeleteCollectParam;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/12/28.
 */

public class MyCollectPresenter extends BasePresenter {
    private List<CollectTo> collectList = new ArrayList<>();

    public MyCollectPresenter(BaseActivity activity) {
        initContext(activity);
        getGoodsList();
    }

    public void getGoodsList() {

        showLoadingDialog();
        PageParam param=new PageParam();
        param.setPage(recyclePageIndex);
        param.setHash(getHashString(PageParam.class,param));
        ApiClient.create(MyselfApi.class).getMyCollectList(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new MyObserver<MessageListTo>(this) {
            @Override
            public void onNext(MessageListTo msg) {
                if (msg.getCode()==0){
                    if (recyclePageIndex==1)
                        collectList.clear();
                    collectList.addAll(new Gson().fromJson(JSON.toJSONString(msg.getDataList()),new TypeToken<List<CollectTo>>(){}.getType()));
                    setRecycleList(collectList);

                }else
                    showMessage(msg.getMsg());
            }
        });

    }

    public void deleteCollect(int collectId){
        showLoadingDialog();
        DeleteCollectParam param=new DeleteCollectParam();
        param.setCollect_id(collectId);
        param.setHash(getHashString(DeleteCollectParam.class,param));
        ApiClient.create(MyselfApi.class).deleteCollect(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageTo>(this) {
                    @Override
                    public void onNext(MessageTo msg) {
                        if (msg.getCode()==0)
                            getGoodsList();
                        else
                            showMessage(msg.getMsg());
                    }
                }
        );
    }

    @Override
    public void recycleViewLoadMore() {
        super.recycleViewLoadMore();
        getGoodsList();
    }

    @Override
    public void recycleViewRefresh() {
        super.recycleViewRefresh();
        getGoodsList();
    }
}
