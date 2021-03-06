package com.hzxm.wolaixiqh.main.present;

import com.hzxm.wolaixiqh.base.BaseFragment;
import com.hzxm.wolaixiqh.base.BasePresenter;
import com.hzxm.wolaixiqh.base.MyObserver;

import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.DeliveryApi;
import hzxmkuar.com.applibrary.domain.BaseParam;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.delivery.main.PageParam;
import hzxmkuar.com.applibrary.domain.delivery.main.UserInfoTo;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2019/1/16.
 */

public class MyselfPresent  extends BasePresenter {
    public MyselfPresent(BaseFragment activity) {
        initContext(activity);
    }
    public void getDeliveryserinfo  (){
        BaseParam param=new BaseParam();
        param.setUid(userInfoTo.getUid());
        param.setHashid(userInfoTo.getHashid());
        param.setHash(getHashString(PageParam.class,param));
        showLoadingDialog();
        ApiClient.create(DeliveryApi.class).getUserInfoPickup(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageTo<UserInfoTo>>(this) {
                    @Override
                    public void onNext(MessageTo<UserInfoTo> msg) {
                        if (msg.getCode()==0){
                      setRecycleList(msg.getData().getLatest_order());
                      submitDataSuccess((UserInfoTo)msg.getData());
                        }
                    }
                }
        );
    }

}
