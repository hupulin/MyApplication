package com.xmkj.washmall.wash.presenter;

import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.BasePresenter;
import com.xmkj.washmall.base.MyObserver;

import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.WashApi;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.wardrobe.OpenWardrobeParam;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by xzz on 2019/4/30.
 */

public class ScanPresenter extends BasePresenter {

    public ScanPresenter(BaseActivity activity){
        initContext(activity);
    }

    public void openWardrobe(String result){
        showLoadingDialog();
        OpenWardrobeParam param=new OpenWardrobeParam();
        param.setOrder_id(activity.getIntent().getStringExtra("OrderId"));
        param.setWardrobe_no(result);
        param.setHash(getHashString(OpenWardrobeParam.class,param));
        ApiClient.create(WashApi.class).openWardrobe(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageTo>(this) {
                    @Override
                    public void onNext(MessageTo msg) {
                        if (msg.getCode()==0){
                            showMessage("开箱成功");
                        }else
                            showMessage(msg.getMsg());
                    }
                }
        );
    }
}
