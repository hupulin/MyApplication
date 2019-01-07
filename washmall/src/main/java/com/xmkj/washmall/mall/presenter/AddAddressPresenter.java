package com.xmkj.washmall.mall.presenter;

import android.os.Handler;

import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.BasePresenter;
import com.xmkj.washmall.base.MyObserver;
import com.xmkj.washmall.base.util.city.CityUtil;

import java.util.ArrayList;
import java.util.List;

import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.MallApi;
import hzxmkuar.com.applibrary.api.OrderApi;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.login.CityTo;
import hzxmkuar.com.applibrary.domain.main.ContactTo;
import hzxmkuar.com.applibrary.domain.order.AddAddressParam;
import hzxmkuar.com.applibrary.domain.order.EditAddressParam;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/12/27.
 */

public class AddAddressPresenter extends BasePresenter {
    private List<ContactTo > contactList=new ArrayList<>();
    public List<CityTo> cityToList;
    public AddAddressPresenter(BaseActivity activity){
        initContext(activity);
      initJson();
    }



    public void initJson() {
        cityToList = new ArrayList<>();
        showLoadingDialog();
        cityToList = CityUtil.initJson(activity);
        dismissLoadingDialog();

    }

    public void addAddress(String consignee,String telephone,String province,String city,String area,String address){
        AddAddressParam param=new AddAddressParam();
        param.setHashid(userInfoTo.getHashid());
        param.setUid(userInfoTo.getUid());
        param.setConsignee(consignee);
        param.setTelephone(telephone);
        param.setProvince(province);
        param.setCity(city);
        param.setArea(area);
        param.setAddress(address);
        param.setHash(getHashString(AddAddressParam.class,param));
        showLoadingDialog();
        ApiClient.create(OrderApi.class).addAddress(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageTo>(this) {
                    @Override
                    public void onNext(MessageTo msg) {
                     if (msg.getCode()==0)
                         getDataSuccess(msg.getData());
                        else
                            showMessage(msg.getMsg());
                    }
                }
        );
    }

    public void editAddress(int id,String consignee,String telephone,String province,String city,String area,String address){
        EditAddressParam param=new EditAddressParam();
        param.setHashid(userInfoTo.getHashid());
        param.setUid(userInfoTo.getUid());
        param.setId(id);
        param.setConsignee(consignee);
        param.setTelephone(telephone);
        param.setProvince(province);
        param.setCity(city);
        param.setArea(area);
        param.setAddress(address);
        param.setHash(getHashString(EditAddressParam.class,param));
        showLoadingDialog();
        ApiClient.create(OrderApi.class).editAddress(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
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
}
