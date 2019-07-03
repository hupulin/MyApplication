package com.xmkj.washmall.message.presenter;

import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.BasePresenter;
import com.xmkj.washmall.base.MyObserver;

import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.UserApi;
import hzxmkuar.com.applibrary.domain.BaseParam;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.login.UserInfoTo;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 1ONE on 2019/6/12.
 */

public class ChatPresenter extends BasePresenter {

    public ChatPresenter(BaseActivity activity){
        initContext(activity);
        getUserInfo();
    }

    private void getUserInfo(){
        BaseParam param=new BaseParam();
        param.setHash(getHashString(BaseParam.class,param));
        showLoadingDialog();
        ApiClient.create(UserApi.class).getUserInfo(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageTo<UserInfoTo>>(this) {
                    @Override
                    public void onNext(MessageTo<UserInfoTo> msg) {
                        if (msg.getCode()==0)
                            getDataSuccess(msg.getData());
                        else
                            showMessage(msg.getMsg());
                    }
                }
        );
    }
}
