package com.xmkj.washmall.myself.presenter;

import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.BasePresenter;
import com.xmkj.washmall.base.MyObserver;

import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.MyselfApi;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.myself.ModifyPasswordParam;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by xzz on 2019/5/15.
 */

public class ModifyPasswordPresenter extends BasePresenter {

    public ModifyPasswordPresenter(BaseActivity activity){
        initContext(activity);
    }
    public void modifyPassword(String oldPassword,String newPassword,String confirmPassword){
       showLoadingDialog();
        ModifyPasswordParam param=new ModifyPasswordParam();
        param.setOldpwd(oldPassword);
        param.setNewpwd(newPassword);
        param.setRepeatpwd(confirmPassword);
        param.setHash(getHashString(ModifyPasswordParam.class,param));
        ApiClient.create(MyselfApi.class).modifyPassword(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
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
