package com.xmkj.washmall.myself.presenter;

import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.BasePresenter;
import com.xmkj.washmall.base.MyObserver;

import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.MyselfApi;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.myself.FeedbackParam;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by xzz on 2019/4/29.
 */

public class FeedbackPresenter extends BasePresenter {

    public FeedbackPresenter(BaseActivity activity){
        initContext(activity);

    }

    public void feedbck(String content){
        showLoadingDialog();
        FeedbackParam param=new FeedbackParam();
        param.setContent(content);
        param.setHash(getHashString(FeedbackParam.class,param));
        ApiClient.create(MyselfApi.class).feedback(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
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
