package com.xmkj.washmall.car.presenter;

import android.os.Handler;

import com.alibaba.fastjson.JSON;
import com.google.android.gms.common.api.Api;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xmkj.washmall.base.BaseFragment;
import com.xmkj.washmall.base.BasePresenter;
import com.xmkj.washmall.base.MyObserver;
import com.xmkj.washmall.main.fragment.CarFragment;

import java.util.ArrayList;
import java.util.List;

import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.CarApi;
import hzxmkuar.com.applibrary.domain.BaseParam;
import hzxmkuar.com.applibrary.domain.MessageListTo;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.car.CarIdsParam;
import hzxmkuar.com.applibrary.domain.car.GoodsCarTo;
import hzxmkuar.com.applibrary.domain.car.ModifyCarNumParam;
import hzxmkuar.com.applibrary.domain.main.MallGoodsTo;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/12/27.
 */

public class CarPresenter extends BasePresenter {
    public List<GoodsCarTo> goodsList = new ArrayList<>();

    public CarPresenter(BaseFragment fragment) {
        initContext(fragment);
        getGoodsList();
    }

    private void getGoodsList() {
        BaseParam param = new BaseParam();
        param.setHashid(userInfoTo.getHashid());
        param.setUid(userInfoTo.getUid());
        param.setHash(getHashString(BaseParam.class, param));
        showLoadingDialog();
        ApiClient.create(CarApi.class).getCarList(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageListTo>(this) {
                    @Override
                    public void onNext(MessageListTo msg) {
                        if (msg.getCode() == 0) {
                            goodsList = new Gson().fromJson(JSON.toJSONString(msg.getDataList()), new TypeToken<List<GoodsCarTo>>() {
                            }.getType());
                            setRecycleList(goodsList);
                        } else
                            showMessage(msg.getMsg());
                    }
                }
        );
    }

    public void modifyNum(int carId, int goodsNum) {
        ModifyCarNumParam param = new ModifyCarNumParam();
        param.setUid(userInfoTo.getUid());
        param.setHashid(userInfoTo.getHashid());
        param.setCart_id(carId);
        param.setGoods_num(goodsNum);
        param.setHash(getHashString(ModifyCarNumParam.class, param));
        showLoadingDialog();
        ApiClient.create(CarApi.class).modifyGoodsNum(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageTo>(this) {
                    @Override
                    public void onNext(MessageTo msg) {
                        if (msg.getCode() == 0)
                            getGoodsList();
                        else
                            showMessage(msg.getMsg());
                    }
                }
        );



    }

    public void deleteGoods(String carId){
        CarIdsParam param=new CarIdsParam();
        param.setUid(userInfoTo.getUid());
        param.setHashid(userInfoTo.getHashid());
        param.setCart_ids(carId);
        param.setHash(getHashString(CarIdsParam.class,param));
        showLoadingDialog();
        ApiClient.create(CarApi.class).deleteGoods(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageTo>(this) {
                    @Override
                    public void onNext(MessageTo msg) {
                        if (msg.getCode()==0) {
                            ((CarFragment)mFragment).deleteSuccess();
                            getGoodsList();
                        }
                        else
                            showMessage(msg.getMsg());
                    }
                }
        );
    }

    public void settlementCar(String carId){
        CarIdsParam param=new CarIdsParam();
        param.setUid(userInfoTo.getUid());
        param.setHashid(userInfoTo.getHashid());
        param.setCart_ids(carId);
        param.setHash(getHashString(CarIdsParam.class,param));
        showLoadingDialog();
        ApiClient.create(CarApi.class).carSettment(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageTo>(this) {
                    @Override
                    public void onNext(MessageTo msg) {
                        if (msg.getCode()==0)
                           submitDataSuccess(msg.getData());
                        else
                            showMessage(msg.getMsg());
                    }
                }
        );
    }

    @Override
    public void recycleViewRefresh() {
        super.recycleViewRefresh();
        getGoodsList();
    }
}
