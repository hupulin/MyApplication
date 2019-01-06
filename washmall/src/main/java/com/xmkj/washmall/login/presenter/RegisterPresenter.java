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
import hzxmkuar.com.applibrary.domain.login.CityTo;
import hzxmkuar.com.applibrary.domain.login.RegisterParam;
import hzxmkuar.com.applibrary.domain.login.WechatLoginTo;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/12/25.
 */

public class RegisterPresenter extends BasePresenter {

    public List<CityTo> cityToList;

    public RegisterPresenter(BaseActivity activity) {
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
        param.setType(1);
        param.setHash(getHashStringNoUser(VerificationParam.class,param));
        showLoadingDialog();
        ApiClient.create(LoginApi.class).getVerificationCode(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new MyObserver<MessageTo>(this) {
            @Override
            public void onNext(MessageTo msg) {
             getDataSuccess(msg.getData());
            }
        });
    }

    public void register(String phoneNumber,String verification,String password,String confirmPassword,String address,String detailAddress,String inviteCode){
        RegisterParam param=new RegisterParam();
        param.setMobile(phoneNumber);
        param.setSms_code(verification);
        param.setPasswd(password);
        param.setRepasswd(confirmPassword);
        param.setAddr_area(address);
        param.setAddr_detail(detailAddress);
        param.setInvite_code(inviteCode);
        param.setJpush_id("android");
        param.setHash(getHashStringNoUser(RegisterParam.class,param));
        showLoadingDialog();
        System.out.println(param+"param");
        ApiClient.create(LoginApi.class).register(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
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
