package com.xmkj.washmall.login.presenter;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.BasePresenter;
import com.xmkj.washmall.base.MyObserver;


import hzxmkuar.com.applibrary.LoginTo;
import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.LoginApi;
import hzxmkuar.com.applibrary.api.UserApi;
import hzxmkuar.com.applibrary.domain.BaseParam;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.VerificationParam;
import hzxmkuar.com.applibrary.domain.login.LoginParam;
import hzxmkuar.com.applibrary.domain.login.UserInfoTo;
import hzxmkuar.com.applibrary.domain.login.WechatLoginParam;
import hzxmkuar.com.applibrary.domain.login.WechatLoginTo;
import hzxmkuar.com.applibrary.domain.user.MyselfUserTo;
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
       param.setSex(sex==0?1:sex);
       param.setAuth_type(type);
       param.setNickname(nickName);
       param.setHash(getHashStringNoUser(WechatLoginParam.class,param));
       showLoadingDialog();
       ApiClient.create(LoginApi.class).wechatLogin(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
               new MyObserver<MessageTo>(this) {
                   @Override
                   public void onNext(MessageTo msg) {
                       if (msg.getCode()==0){
                           WechatLoginTo loginTo = new Gson().fromJson(JSON.toJSONString(msg.getData()), WechatLoginTo.class);
                           if (loginTo.getUid()==0)
                           getDataSuccess(loginTo);
                           else {
                               UserInfoTo userInfoTo = new UserInfoTo();
                               userInfoTo.setUid(loginTo.getUid());
                               userInfoTo.setOauth_id(loginTo.getOauth_id());
                               userInfoTo.setHashid(loginTo.getHashid());
                               userInfoHelp.saveUserInfo(userInfoTo);
                               getUserInfo();
                           }
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
                    if (msg.getCode()==0) {
                        WechatLoginTo loginTo = new Gson().fromJson(JSON.toJSONString(msg.getData()), WechatLoginTo.class);
                        UserInfoTo userInfoTo = new UserInfoTo();
                        userInfoTo.setUid(loginTo.getUid());
                        userInfoTo.setOauth_id(loginTo.getOauth_id());
                        userInfoTo.setHashid(loginTo.getHashid());
                        userInfoHelp.saveUserInfo(userInfoTo);
                        getUserInfo();
                    }
                        else
                            showMessage(msg.getMsg());
                    }
                }
        );
    }

    private void getUserInfo(){
        BaseParam param=new BaseParam();
        userInfoTo=userInfoHelp.getUserInfo();
        param.setUid(userInfoTo.getUid());
        param.setHashid(userInfoTo.getHashid());
        param.setHash(getHashString(BaseParam.class,param));
        showLoadingDialog();
        ApiClient.create(UserApi.class).getMyselfUserInfo(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageTo<MyselfUserTo>>(this) {
                    @Override
                    public void onNext(MessageTo<MyselfUserTo> msg) {
                        if (msg.getCode()==0){
                            submitDataSuccess(msg.getData());
                        }
                    }
                }
        );
    }
}
