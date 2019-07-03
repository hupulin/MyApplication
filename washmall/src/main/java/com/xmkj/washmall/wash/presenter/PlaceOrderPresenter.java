package com.xmkj.washmall.wash.presenter;

import android.text.TextUtils;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.BasePresenter;
import com.xmkj.washmall.base.MyObserver;
import com.xmkj.washmall.base.util.SpUtil;
import com.xmkj.washmall.main.fragment.HomeFragment;
import com.xmkj.washmall.wash.PlaceOrderActivity;

import java.util.List;

import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.MainHomeApi;
import hzxmkuar.com.applibrary.api.WashApi;
import hzxmkuar.com.applibrary.domain.MessageListTo;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.main.MainWardrobeParam;
import hzxmkuar.com.applibrary.domain.main.MainWardrobeTo;
import hzxmkuar.com.applibrary.domain.mall.OrderIdTo;
import hzxmkuar.com.applibrary.domain.mall.OrderInfoTo;
import hzxmkuar.com.applibrary.domain.wash.AddWashOrderParam;
import hzxmkuar.com.applibrary.domain.wash.PlaceWashOrderTo;
import hzxmkuar.com.applibrary.domain.wash.WashJsonParam;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2019/1/9.
 **/

public class PlaceOrderPresenter extends BasePresenter{

    public PlaceOrderPresenter(BaseActivity activity){
        initContext(activity);
        getPlaceOrder();
    }

    private void getPlaceOrder(){
        WashJsonParam param=new WashJsonParam();
        param.setHashid(userInfoTo.getHashid());
        param.setUid(userInfoTo.getUid());
        param.setWash_json(activity.getIntent().getStringExtra("WashJson"));
        param.setHash(getHashString(WashJsonParam.class,param));
        showLoadingDialog();

        ApiClient.create(WashApi.class).getOrderInfo(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageTo>(this) {
                    @Override
                    public void onNext(MessageTo msg) {
                        if (msg.getCode()==0)
                            System.out.println();
                        else
                            showMessage(msg.getMsg());
                    }
                }
        );
    }

    public void getWardrobeList(TextView selectText){
        MainWardrobeParam param=new MainWardrobeParam();
        param.setHashid(userInfoTo.getHashid());
        param.setUid(userInfoTo.getUid());
        param.setPos_city(SpUtil.getInt("SelectCityId"));
        param.setLat(SpUtil.getString("Lat"));
        param.setLng(SpUtil.getString("Lng"));
        param.setHash(getHashString(MainWardrobeParam.class,param));
        showLoadingDialog();
        ApiClient.create(MainHomeApi.class).getWardrobeList(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageListTo>(this) {
                    @Override
                    public void onNext(MessageListTo msg) {
                        if (msg.getCode()==0)
                            ((PlaceOrderActivity)activity).selectWardRobeDialog(selectText,new Gson().fromJson(JSON.toJSONString(msg.getDataList()),new TypeToken<List<MainWardrobeTo>>(){}.getType()));
                    }
                }
        );
    }

    public void addOrder(int saveId,int pickId,String pickTime,String remark){
        AddWashOrderParam param=new AddWashOrderParam();
        param.setUid(userInfoTo.getUid());
        param.setHashid(userInfoTo.getHashid());
        param.setDeposit_id(saveId);
        param.setPickup_id(pickId);
        param.setPickup_time(pickTime);
        param.setRemarks(remark);
        param.setHash(getHashString(AddWashOrderParam.class,param));
        showLoadingDialog();
        ApiClient.create(WashApi.class).addOrder(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageTo>(this) {
                    @Override
                    public void onNext(MessageTo msg) {
                        if (msg.getCode()==0){
                            submitDataSuccess(new Gson().fromJson(JSON.toJSONString(msg.getData()), PlaceWashOrderTo.class));
                        }else
                            showMessage(msg.getMsg());
                    }
                }
        );

    }
}
