package com.hzxm.wolaixish.person.present;

import com.hzxm.wolaixish.base.BaseActivity;
import com.hzxm.wolaixish.base.BasePresenter;
import com.hzxm.wolaixish.base.MyObserver;

import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.DeliveryApi;
import hzxmkuar.com.applibrary.domain.BaseParam;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.delivery.login.updateUserPasswdParam;
import hzxmkuar.com.applibrary.domain.delivery.main.PageParam;
import hzxmkuar.com.applibrary.domain.delivery.main.UserInfoTo;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2019/1/16.
 */

public class ChangePasswordPresent extends BasePresenter{

    public ChangePasswordPresent(BaseActivity activity) {
        initContext(activity);
    }
    public void updateUserPasswd  (String passwd,String newPasswd,String reNewPasswd){
        updateUserPasswdParam param=new updateUserPasswdParam();
        param.setUid(userInfoTo.getUid());
        param.setHashid(userInfoTo.getHashid());
        param.setPasswd(passwd);
        param.setNewpasswd(newPasswd);
        param.setRe_newpasswd(reNewPasswd);
        param.setHash(getHashString(updateUserPasswdParam.class,param));
        showLoadingDialog();
        ApiClient.create(DeliveryApi.class).updateUserPasswd(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageTo>(this) {
                    @Override
                    public void onNext(MessageTo msg) {
                        if (msg.getCode()==0){
                            showMessage("修改密码成功");
                            submitDataSuccess(msg.getData());
                        }else {
                            showMessage(msg.getMsg());
                        }
                    }
                }
        );
    }


}
