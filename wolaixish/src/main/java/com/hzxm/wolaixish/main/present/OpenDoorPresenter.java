package com.hzxm.wolaixish.main.present;

import com.hzxm.wolaixish.base.BaseActivity;
import com.hzxm.wolaixish.base.BasePresenter;
import com.hzxm.wolaixish.base.MyObserver;

import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.DeliveryApi;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.delivery.main.OpenDoorParam;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class OpenDoorPresenter extends BasePresenter {
    public OpenDoorPresenter(BaseActivity activity) {
        initContext(activity);
    }

    public void OpenDoorPresent(int id, String result) {
        OpenDoorParam param = new OpenDoorParam();
        param.setUid(userInfoTo.getUid());
        param.setHashid(userInfoTo.getHashid());
        param.setOrder_id(id);
        param.setWardrobe_no(result);
        param.setHash(getHashString(OpenDoorParam.class, param));
        showLoadingDialog();
        ApiClient.create(DeliveryApi.class).openDoorDelivery(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageTo>(this) {
                    @Override
                    public void onNext(MessageTo msg) {
                        if (msg.getCode() == 0) {
                            submitDataSuccess(msg);
                        }else{
                            showMessage(msg.getMsg());
                        }
                    }
                }
        );
    }

}