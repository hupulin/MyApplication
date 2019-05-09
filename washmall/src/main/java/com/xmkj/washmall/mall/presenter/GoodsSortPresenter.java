package com.xmkj.washmall.mall.presenter;

import android.os.Handler;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xmkj.washmall.base.BaseFragment;
import com.xmkj.washmall.base.BasePresenter;
import com.xmkj.washmall.base.MyObserver;

import java.util.ArrayList;
import java.util.List;

import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.MallApi;
import hzxmkuar.com.applibrary.domain.MessageListTo;
import hzxmkuar.com.applibrary.domain.main.MallGoodsTo;
import hzxmkuar.com.applibrary.domain.mall.MallGoodsListParam;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/12/27.
 */

public class GoodsSortPresenter extends BasePresenter {
    private List<MallGoodsTo> goodsList = new ArrayList<>();

    public GoodsSortPresenter(BaseFragment fragment) {
        initContext(fragment);

    }

    public void getGoodsList(int typeId) {


        MallGoodsListParam param = new MallGoodsListParam();
        param.setCate_id(typeId);
        param.setHash(getHashStringNoUser(MallGoodsListParam.class, param));
        ApiClient.create(MallApi.class).getGoodsList(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageListTo>(this) {
                    @Override
                    public void onNext(MessageListTo msg) {
                        if (msg.getCode() == 0) {
                            goodsList.addAll(new Gson().fromJson(JSON.toJSONString(msg.getDataList()), new TypeToken<List<MallGoodsTo>>() {
                            }.getType()));
                            setRecycleList(goodsList);
                        } else
                            showMessage(msg.getMsg());
                    }
                }
        );
    }
}
