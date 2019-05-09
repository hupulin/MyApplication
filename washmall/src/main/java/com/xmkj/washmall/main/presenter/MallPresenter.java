package com.xmkj.washmall.main.presenter;

import com.alibaba.fastjson.JSON;
import com.google.android.gms.common.api.Api;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xmkj.washmall.base.BaseFragment;
import com.xmkj.washmall.base.BasePresenter;
import com.xmkj.washmall.base.MyObserver;

import java.util.ArrayList;
import java.util.List;

import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.MainHomeApi;
import hzxmkuar.com.applibrary.api.MallApi;
import hzxmkuar.com.applibrary.domain.BaseParam;
import hzxmkuar.com.applibrary.domain.MessageListTo;
import hzxmkuar.com.applibrary.domain.main.MallGoodsTo;
import hzxmkuar.com.applibrary.domain.mall.MallGoodsListParam;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2019/1/6.
 **/

public class MallPresenter extends BasePresenter {
   private List<MallGoodsTo> goodsList = new ArrayList<>();
    public MallPresenter(BaseFragment fragment){
        initContext(fragment);
        getMallTypeList();
        getGoodsList();
    }

    public void getMallTypeList(){
        BaseParam param=new BaseParam();
        param.setHashid(userInfoTo.getHashid());
        param.setUid(userInfoTo.getUid());
        param.setHash(getHashString(BaseParam.class,param));
        showLoadingDialog();
        ApiClient.create(MallApi.class).getTypeList(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageListTo>(this) {
                    @Override
                    public void onNext(MessageListTo msg) {
                        if (msg.getCode()==0)
                            getDataSuccess(msg.getDataList());
                        else
                            showMessage(msg.getMsg());
                    }
                }
        );
    }

    public void getGoodsList(){


        MallGoodsListParam param=new MallGoodsListParam();
        param.setPage(recyclePageIndex);
        param.setHash(getHashStringNoUser(MallGoodsListParam.class,param));
        ApiClient.create(MallApi.class).getGoodsList(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageListTo>(this) {
                    @Override
                    public void onNext(MessageListTo msg) {
                        if (msg.getCode()==0){

                            if (recyclePageIndex==1)
                                goodsList.clear();
                            goodsList.addAll(new Gson().fromJson(JSON.toJSONString(msg.getDataList()), new TypeToken<List<MallGoodsTo>>() {
                            }.getType()));
                            setRecycleList(goodsList);
                        }

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
