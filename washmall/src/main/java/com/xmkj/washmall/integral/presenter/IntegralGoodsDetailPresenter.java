package com.xmkj.washmall.integral.presenter;

import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.BasePresenter;
import com.xmkj.washmall.base.MyObserver;

import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.IntegralApi;
import hzxmkuar.com.applibrary.api.MallApi;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.integral.IntegralGoodsTo;
import hzxmkuar.com.applibrary.domain.main.MallGoodsTo;
import hzxmkuar.com.applibrary.domain.mall.GoodsIdParam;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2019/1/8.
 */

public class IntegralGoodsDetailPresenter extends BasePresenter {
    public IntegralGoodsDetailPresenter(BaseActivity activity){
        initContext(activity);
        getGoodsDetail();
    }

    private void getGoodsDetail(){
        GoodsIdParam param=new GoodsIdParam();
        param.setUid(userInfoTo.getUid());
        param.setHashid(userInfoTo.getHashid());
        param.setGoods_id(((IntegralGoodsTo)activity.getIntent().getSerializableExtra("GoodsTo")).getGoods_id());
        param.setHash(getHashString(GoodsIdParam.class,param));
        showLoadingDialog();
        ApiClient.create(IntegralApi.class).getGoodsDetail(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
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
