package com.xmkj.washmall.login.presenter;

import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.BasePresenter;
import com.xmkj.washmall.base.MyObserver;


import hzxmkuar.com.applibrary.LoginTo;
import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.LoginApi;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.VerificationParam;
import hzxmkuar.com.applibrary.domain.login.LoginParam;
import hzxmkuar.com.applibrary.domain.login.WechatLoginParam;
import hzxmkuar.com.applibrary.domain.login.WechatLoginTo;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2019/1/3.
 */

public class LoginPresenter extends BasePresenter{
    public LoginPresenter(BaseActivity activity){
        initContext(activity);
    }


   public void thirdPartLogin(String openId,String nickName,String headImage,int sex,int type){
       WechatLoginParam param=new WechatLoginParam();
       param.setJpush_id("android");
       param.setOpenid(openId);
       param.setHeadimgurl(headImage);
       param.setSex(sex);
       param.setAuth_type(type);
       param.setNickname(nickName);
       param.setHash(getHashStringNoUser(WechatLoginParam.class,param));
       showLoadingDialog();
       ApiClient.create(LoginApi.class).wechatLogin(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
               new MyObserver<MessageTo>(this) {
                   @Override
                   public void onNext(MessageTo msg) {
                       if (msg.getCode()==0){
                           getDataSuccess(msg.getData());
                       }else
                           showMessage(msg.getMsg());
                   }
               }
       );

   }

    public void login(String phoneNumber,String password){
        LoginParam param=new LoginParam();
        param.setUsername(phoneNumber);
        param.setPassword(password);
        param.setJpush_id("android");
        param.setHash(getHashStringNoUser(LoginParam.class,param));
        showLoadingDialog();
        ApiClient.create(LoginApi.class).login(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
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
