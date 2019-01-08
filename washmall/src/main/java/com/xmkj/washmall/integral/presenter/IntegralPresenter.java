package com.xmkj.washmall.integral.presenter;

import android.os.Handler;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.BaseFragment;
import com.xmkj.washmall.base.BasePresenter;
import com.xmkj.washmall.base.MyObserver;

import java.util.ArrayList;
import java.util.List;

import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.IntegralApi;
import hzxmkuar.com.applibrary.api.MallApi;
import hzxmkuar.com.applibrary.domain.MessageListTo;
import hzxmkuar.com.applibrary.domain.integral.IntegralGoodsParam;
import hzxmkuar.com.applibrary.domain.integral.IntegralGoodsTo;
import hzxmkuar.com.applibrary.domain.main.MallGoodsTo;
import hzxmkuar.com.applibrary.domain.mall.MallGoodsListParam;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/12/27.
 */

public class IntegralPresenter extends BasePresenter {
private List<IntegralGoodsTo>goodsList=new ArrayList<>();
    public IntegralPresenter(BaseActivity activity){
        initContext(activity);
        getGoodsList();
    }

    public void getGoodsList(){
        IntegralGoodsParam param = new IntegralGoodsParam();
        param.setPage(recyclePageIndex);
        param.setUid(userInfoTo.getUid());
        param.setHashid(userInfoTo.getHashid());
        param.setHash(getHashString(IntegralGoodsParam.class, param));
        ApiClient.create(IntegralApi.class).getGoodsList(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageListTo>(this) {
                    @Override
                    public void onNext(MessageListTo msg) {
                        if (msg.getCode() == 0) {
                            goodsList.addAll(new Gson().fromJson(JSON.toJSONString(msg.getDataList()), new TypeToken<List<IntegralGoodsTo>>() {
                            }.getType()));
                            setRecycleList(goodsList);
                        } else
                            showMessage(msg.getMsg());
                    }
                }
        );

    }
}
