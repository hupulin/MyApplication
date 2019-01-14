package com.xmkj.washmall.integral.presenter;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xmkj.washmall.base.BaseFragment;
import com.xmkj.washmall.base.BasePresenter;
import com.xmkj.washmall.base.MyObserver;

import java.util.List;

import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.IntegralApi;
import hzxmkuar.com.applibrary.domain.MessageListTo;
import hzxmkuar.com.applibrary.domain.integral.IntegralOrderListTo;
import hzxmkuar.com.applibrary.domain.order.MyOrderParam;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2019/1/12.
 */

public class IntegralFragmentPresenter extends BasePresenter{

    public IntegralFragmentPresenter(BaseFragment fragment){
        initContext(fragment);
    }

    public void getOrderList(int type){
        MyOrderParam param=new MyOrderParam();
        param.setPage(1);
        param.setOtype(type);
        param.setUid(userInfoTo.getUid());
        param.setHashid(userInfoTo.getHashid());
        param.setHash(getHashString(MyOrderParam.class,param));
        showLoadingDialog();
        ApiClient.create(IntegralApi.class).getOrderList(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageListTo>(this) {
                    @Override
                    public void onNext(MessageListTo msg) {
                        if (msg.getCode()==0)
                            setRecycleList(new Gson().fromJson(JSON.toJSONString(msg.getDataList()),new TypeToken<List<IntegralOrderListTo>>(){}.getType()));
                        else
                            showMessage(msg.getMsg());
                    }
                }
        );
    }

}
