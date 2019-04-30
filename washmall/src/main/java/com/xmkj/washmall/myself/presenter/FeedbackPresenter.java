package com.xmkj.washmall.myself.presenter;

import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.BasePresenter;

import hzxmkuar.com.applibrary.api.ApiClient;

/**
 * Created by xzz on 2019/4/29.
 */

public class FeedbackPresenter extends BasePresenter {

    public FeedbackPresenter(BaseActivity activity){
        initContext(activity);

    }

    public void feedbck(){
        showLoadingDialog();
//        ApiClient.create(Myself)
    }
}
