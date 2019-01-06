package com.xmkj.washmall.main.presenter;

import com.xmkj.washmall.base.BaseFragment;
import com.xmkj.washmall.base.BasePresenter;
import com.xmkj.washmall.base.MyObserver;
import com.xmkj.washmall.main.fragment.HomeFragment;

import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.MainHomeApi;
import hzxmkuar.com.applibrary.domain.BaseParam;
import hzxmkuar.com.applibrary.domain.MessageListTo;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.main.MainAdParent;
import hzxmkuar.com.applibrary.domain.main.MainWardrobeParam;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2019/1/6.
 */

public class MainHomePresenter extends BasePresenter {

    public MainHomePresenter(BaseFragment fragment){
        initContext(fragment);
        getMainAd();
        getWardrobeList();
    }

    private void getMainAd(){
        BaseParam param=new BaseParam();
        param.setUid(userInfoTo.getUid());
        param.setHashid(userInfoTo.getHashid());
        param.setHash(getHashString(BaseParam.class,param));
        showLoadingDialog();
        ApiClient.create(MainHomeApi.class).getMainHomeAd(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageTo<MainAdParent>>(this) {
                    @Override
                    public void onNext(MessageTo<MainAdParent> msg) {
                        if (msg.getCode()==0)
                            getDataSuccess(msg.getData().getIndex_slideshow());
                    }
                }
        );
    }

    private void getWardrobeList(){
        MainWardrobeParam param=new MainWardrobeParam();
        param.setHashid(userInfoTo.getHashid());
        param.setUid(userInfoTo.getUid());
        param.setHash(getHashString(MainWardrobeParam.class,param));
        ApiClient.create(MainHomeApi.class).getWardrobeList(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageListTo>(this) {
                    @Override
                    public void onNext(MessageListTo msg) {
                        if (msg.getCode()==0)
                            ((HomeFragment)mFragment).setWardrobe(msg.getDataList());
                    }
                }
        );
    }
}
