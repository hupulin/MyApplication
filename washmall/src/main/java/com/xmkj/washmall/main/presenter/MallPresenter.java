package com.xmkj.washmall.main.presenter;

import com.google.android.gms.common.api.Api;
import com.xmkj.washmall.base.BaseFragment;
import com.xmkj.washmall.base.BasePresenter;
import com.xmkj.washmall.base.MyObserver;

import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.MainHomeApi;
import hzxmkuar.com.applibrary.api.MallApi;
import hzxmkuar.com.applibrary.domain.BaseParam;
import hzxmkuar.com.applibrary.domain.MessageListTo;
import hzxmkuar.com.applibrary.domain.mall.MallGoodsListParam;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2019/1/6.
 **/

public class MallPresenter extends BasePresenter {

    public MallPresenter(BaseFragment fragment){
        initContext(fragment);
        getMallTypeList();
        getGoodsList();
    }

    public void getMallTypeList(){
        BaseParam param=new BaseParam();
        param.setHashid(userInfoTo.getHashid());
        param.setUid(userInfoTo.getUid());
        param.setHash(getHashString(BaseParam.class,param));
        showLoadingDialog();
        ApiClient.create(MallApi.class).getTypeList(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageListTo>(this) {
                    @Override
                    public void onNext(MessageListTo msg) {
                        if (msg.getCode()==0)
                            getDataSuccess(msg.getDataList());
                        else
                            showMessage(msg.getMsg());
                    }
                }
        );
    }

    public void getGoodsList(){


        MallGoodsListParam param=new MallGoodsListParam();

        param.setHash(getHashStringNoUser(MallGoodsListParam.class,param));
        ApiClient.create(MallApi.class).getGoodsList(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageListTo>(this) {
                    @Override
                    public void onNext(MessageListTo msg) {
                        if (msg.getCode()==0)
                            submitDataSuccess(msg.getDataList());
                        else
                            showMessage(msg.getMsg());
                    }
                }
        );
    }
}
