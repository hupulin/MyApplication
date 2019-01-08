package com.xmkj.washmall.main.presenter;

import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.BaseFragment;
import com.xmkj.washmall.base.BasePresenter;
import com.xmkj.washmall.base.MyObserver;

import hzxmkuar.com.applibrary.api.ApiClient;
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

public class MyselfPresenter extends BasePresenter{

    public MyselfPresenter(BaseFragment fragment){
        initContext(fragment);
        getUserInfo();
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
                           getDataSuccess(msg.getData());
                        }
                    }
                }
        );
    }
}
