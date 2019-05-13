package com.xmkj.washmall.main.presenter;

import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.BasePresenter;
import com.xmkj.washmall.base.MyObserver;
import hzxmkuar.com.applibrary.domain.login.UserLoginParam;

import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.LoginApi;
import hzxmkuar.com.applibrary.api.UserApi;
import hzxmkuar.com.applibrary.domain.BaseParam;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.login.UserInfoTo;
import hzxmkuar.com.applibrary.domain.user.MyselfUserTo;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2019/1/8.
 */

public class MainPresenter extends BasePresenter {
    public MainPresenter(BaseActivity activity){
        initContext(activity);
        getUserInfo();
        userLogin();
    }

    private void getUserInfo(){
        BaseParam param=new BaseParam();
        param.setUid(userInfoTo.getUid());
        param.setHashid(userInfoTo.getHashid());
        param.setHash(getHashString(BaseParam.class,param));
        showLoadingDialog();
        ApiClient.create(UserApi.class).getMyselfUserInfo(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageTo<MyselfUserTo>>(this) {
                    @Override
                    public void onNext(MessageTo<MyselfUserTo> msg) {
                        if (msg.getCode()==0){
                            MyselfUserTo data=msg.getData();
                            userInfoTo.setMyselfTo(data);
                            userInfoHelp.saveUserInfo(userInfoTo);
                        }
                    }
                }
        );
    }

    private void userLogin(){
        UserLoginParam param=new UserLoginParam();
        param.setUsername("18069765024");
        param.setPasswd("123456");
        param.setRole_id(2);
        param.setJpush_id("android");
        param.setHash(getHashStringNoUser(UserLoginParam.class,param));
        showLoadingDialog();
        ApiClient.create(LoginApi.class).userLogin(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageTo>(this) {
            @Override
            public void onNext(MessageTo msg) {

            }
        });
    }
}
