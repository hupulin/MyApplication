package com.hzxm.wolaixiqh.login.present;

import com.hzxm.wolaixiqh.base.BaseActivity;
import com.hzxm.wolaixiqh.base.BasePresenter;
import com.hzxm.wolaixiqh.base.MyObserver;

import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.DeliveryApi;
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
        param.setRole_id(1);
        param.setHash(getHashStringNoUser(LoginShopUserParam.class,param));
        showLoadingDialog();
        ApiClient.create(DeliveryApi.class).shopUserLogin(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
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
