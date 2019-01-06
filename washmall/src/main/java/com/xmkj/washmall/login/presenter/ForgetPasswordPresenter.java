package com.xmkj.washmall.login.presenter;

import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.BasePresenter;
import com.xmkj.washmall.base.MyObserver;

import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.LoginApi;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.VerificationParam;
import hzxmkuar.com.applibrary.domain.login.ModifyPasswordParam;
import hzxmkuar.com.applibrary.domain.login.VerificationLoginParam;
import hzxmkuar.com.applibrary.domain.user.ModifyImageParam;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2019/1/3.
 */

public class ForgetPasswordPresenter extends BasePresenter{
    public ForgetPasswordPresenter(BaseActivity activity){
        initContext(activity);
    }


    public void getVerificationCode(String phoneNumber){
        VerificationParam param=new VerificationParam();
        param.setMobile(phoneNumber);
        param.setType(2);
        param.setHash(getHashStringNoUser(VerificationParam.class,param));
        showLoadingDialog();
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

    public void modifyPassword(String phoneNumber,String verificationCode,String password,String confirmPassword){
        ModifyPasswordParam param=new ModifyPasswordParam();
        param.setMobile(phoneNumber);
        param.setSms_code(verificationCode);
        param.setPasswd(password);
        param.setRepasswd(confirmPassword);
        param.setHash(getHashStringNoUser(ModifyPasswordParam.class,param));
        showLoadingDialog();
        ApiClient.create(LoginApi.class).modifyPassword(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
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
