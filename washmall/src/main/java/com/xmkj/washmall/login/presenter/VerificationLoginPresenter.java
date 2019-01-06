package com.xmkj.washmall.login.presenter;

import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.BasePresenter;
import com.xmkj.washmall.base.MyObserver;

import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.LoginApi;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.VerificationParam;
import hzxmkuar.com.applibrary.domain.login.LoginParam;
import hzxmkuar.com.applibrary.domain.login.VerificationLoginParam;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2019/1/3.
 */

public class VerificationLoginPresenter extends BasePresenter{
    public VerificationLoginPresenter(BaseActivity activity){
        initContext(activity);
    }


    public void getVerificationCode(String phoneNumber){
        VerificationParam param=new VerificationParam();
        param.setMobile(phoneNumber);
        param.setType(1);
        param.setHash(getHashStringNoUser(VerificationParam.class,param));
        ApiClient.create(LoginApi.class).getVerificationCode(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new MyObserver<MessageTo>(this) {
            @Override
            public void onNext(MessageTo msg) {
              if (msg.getCode()==0)
                  getDataSuccess(msg.getData());
                else
                    showMessage(msg.getMsg());
            }
        });
    }

    public void login(String phoneNumber,String verificationCode){
        VerificationLoginParam param=new VerificationLoginParam();
        param.setMobile(phoneNumber);
        param.setSms_code(verificationCode);
        param.setJpush_id("android");
        param.setHash(getHashStringNoUser(VerificationLoginParam.class,param));
        showLoadingDialog();
        ApiClient.create(LoginApi.class).verificationLogin(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
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
