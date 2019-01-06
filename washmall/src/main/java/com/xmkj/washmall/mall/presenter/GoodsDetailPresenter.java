package com.xmkj.washmall.mall.presenter;

import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.BasePresenter;
import com.xmkj.washmall.base.MyObserver;

import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.MallApi;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.main.MallGoodsTo;
import hzxmkuar.com.applibrary.domain.mall.GoodsIdParam;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2019/1/6.
 */

public class GoodsDetailPresenter extends BasePresenter {

    public GoodsDetailPresenter(BaseActivity activity){
        initContext(activity);
        getGoodsDetail();
    }

    public void getGoodsDetail(){
        GoodsIdParam param=new GoodsIdParam();
        param.setGoods_id(((MallGoodsTo)activity.getIntent().getSerializableExtra("GoodsTo")).getGoods_id());
        param.setHash(getHashStringNoUser(GoodsIdParam.class,param));
        ApiClient.create(MallApi.class).getGoodsDetail(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
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
