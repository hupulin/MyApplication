package com.xmkj.washmall.login.presenter;

import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.BasePresenter;
import com.xmkj.washmall.base.MyObserver;
import com.xmkj.washmall.base.util.city.CityUtil;

import java.util.ArrayList;
import java.util.List;

import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.LoginApi;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.VerificationParam;
import hzxmkuar.com.applibrary.domain.login.BindPhoneParam;
import hzxmkuar.com.applibrary.domain.login.CityTo;
import hzxmkuar.com.applibrary.domain.login.RegisterParam;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/12/25.
 */

public class BindPhonePresenter extends BasePresenter {

    public List<CityTo> cityToList;

    public BindPhonePresenter(BaseActivity activity) {
        initContext(activity);
        initJson();
    }

    public void initJson() {
        cityToList = new ArrayList<>();
        showLoadingDialog();
        cityToList = CityUtil.initJson(activity);
        dismissLoadingDialog();

    }


    public void getVerificationCode(String phoneNumber){
        VerificationParam param=new VerificationParam();
        param.setMobile(phoneNumber);
        param.setType(3);
        param.setHash(getHashStringNoUser(VerificationParam.class,param));
        showLoadingDialog();
        ApiClient.create(LoginApi.class).getVerificationCode(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new MyObserver<MessageTo>(this) {
            @Override
            public void onNext(MessageTo msg) {
             getDataSuccess(msg.getData());
            }
        });
    }

    public void bindPhone(String phoneNumber,String verification,String password,String confirmPassword,String address,String detailAddress,String inviteCode){
        BindPhoneParam param=new BindPhoneParam();
        param.setMobile(phoneNumber);
        param.setSms_code(verification);
        param.setPasswd(password);
        param.setRepasswd(confirmPassword);
        param.setAddr_area(address);
        param.setAddr_detail(detailAddress);
        param.setInvite_code(inviteCode);
        param.setJpush_id("android");
        param.setOauth_id(activity.getIntent().getIntExtra("OauthId",0));
        param.setHash(getHashStringNoUser(BindPhoneParam.class,param));
        showLoadingDialog();
        ApiClient.create(LoginApi.class).bindPhone(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageTo>(this) {
                    @Override
                    public void onNext(MessageTo msg) {
                        if (msg.getCode()==0){
                            submitDataSuccess(msg.getData());
                        }else {
                            showMessage(msg.getMsg());
                        }
                    }
                }
        );
    }

}
