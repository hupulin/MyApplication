package com.hzxm.wolaixish.login.present;

import com.hzxm.wolaixish.base.BaseActivity;
import com.hzxm.wolaixish.base.BasePresenter;
import com.hzxm.wolaixish.base.MyObserver;

import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.deliveryApi;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.delivery.login.LoginShopUserParam;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2019/1/3.
 */

public class LoginPresenter extends BasePresenter {
    public LoginPresenter(BaseActivity activity){
        initContext(activity);
    }

    public void login(String username,String password){
        LoginShopUserParam param=new LoginShopUserParam();
        param.setUsername(username);
        param.setPasswd(password);
        param.setJpush_id("android");
        param.setRole_id(2);
        param.setHash(getHashStringNoUser(LoginShopUserParam.class,param));
        showLoadingDialog();
        ApiClient.create(deliveryApi.class).shopUserLogin(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
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
