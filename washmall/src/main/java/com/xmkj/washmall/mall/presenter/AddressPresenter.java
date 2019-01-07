package com.xmkj.washmall.mall.presenter;

import android.os.Handler;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.BasePresenter;
import com.xmkj.washmall.base.MyObserver;
import com.xmkj.washmall.base.util.city.CityUtil;

import java.util.ArrayList;
import java.util.List;

import hzxmkuar.com.applibrary.IdParam;
import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.OrderApi;
import hzxmkuar.com.applibrary.domain.BaseParam;
import hzxmkuar.com.applibrary.domain.MessageListTo;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.login.CityTo;
import hzxmkuar.com.applibrary.domain.main.ContactTo;
import hzxmkuar.com.applibrary.domain.order.AddressTo;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/12/27.
 */

public class AddressPresenter extends BasePresenter {

    public AddressPresenter(BaseActivity activity){
        initContext(activity);
        getAddressList();
    }

    public void getAddressList(){
        BaseParam param=new BaseParam();
        param.setUid(userInfoTo.getUid());
        param.setHashid(userInfoTo.getHashid());
        param.setHash(getHashString(BaseParam.class,param));
        showLoadingDialog();
        ApiClient.create(OrderApi.class).addressList(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageListTo>(this) {
                    @Override
                    public void onNext(MessageListTo msg) {
                        if (msg.getCode()==0){
                            setRecycleList(new Gson().fromJson(JSON.toJSONString(msg.getDataList()),new TypeToken<List<AddressTo>>(){}.getType()));
                        }else
                            showMessage(msg.getMsg());
                    }
                }
        );
    }

    public void deleteAddress(int id){
        IdParam param=new IdParam();
        param.setId(id);
        param.setHashid(userInfoTo.getHashid());
        param.setUid(userInfoTo.getUid());
        param.setHash(getHashString(IdParam.class,param));
        showLoadingDialog();
        ApiClient.create(OrderApi.class).deleteAddress(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageTo>(this) {
                    @Override
                    public void onNext(MessageTo msg) {
                        if (msg.getCode()==0){
                            showMessage("删除成功");
                            getAddressList();
                        }else
                            showMessage(msg.getMsg());
                    }
                }
        );
    }


    public void setDefaultAddress(int id){
        IdParam param=new IdParam();
        param.setId(id);
        param.setHashid(userInfoTo.getHashid());
        param.setUid(userInfoTo.getUid());
        param.setHash(getHashString(IdParam.class,param));
        showLoadingDialog();
        ApiClient.create(OrderApi.class).deleteAddress(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageTo>(this) {
                    @Override
                    public void onNext(MessageTo msg) {
                        if (msg.getCode()==0){
                            showMessage("删除成功");
                            getAddressList();
                        }else
                            showMessage(msg.getMsg());
                    }
                }
        );
    }
}
