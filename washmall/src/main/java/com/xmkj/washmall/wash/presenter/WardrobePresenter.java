package com.xmkj.washmall.wash.presenter;

import android.os.Handler;

import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.BaseFragment;
import com.xmkj.washmall.base.BasePresenter;
import com.xmkj.washmall.base.MyObserver;

import java.util.ArrayList;
import java.util.List;

import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.MainHomeApi;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.main.MainWardrobeTo;
import hzxmkuar.com.applibrary.domain.wardrobe.WardrobeDetailParam;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/12/26.
 **/

public class WardrobePresenter extends BasePresenter {

    public WardrobePresenter(BaseActivity activity){
        initContext(activity);
        getWardrobe();
    }

    public void getWardrobe(){
        WardrobeDetailParam param=new WardrobeDetailParam();
        param.setWid(activity.getIntent().getIntExtra("WardrobeId",0));
        param.setFloor_no(0);
        param.setHash(getHashStringNoUser(WardrobeDetailParam.class,param));
        showLoadingDialog();
        ApiClient.create(MainHomeApi.class).getWardrobeDetail(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageTo>(this) {
                    @Override
                    public void onNext(MessageTo msg) {
                        if (msg.getCode()==0)
                            getDataSuccess(msg.getData());
                        else
                            showMessage(msg.getMsg());
                    }
                }
        );
    }
}
