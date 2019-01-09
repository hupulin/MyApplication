package com.xmkj.washmall.myself.presenter;

import android.os.Handler;

import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.BaseFragment;
import com.xmkj.washmall.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.WashApi;
import hzxmkuar.com.applibrary.domain.BaseParam;
import hzxmkuar.com.applibrary.domain.myself.CouponTo;

/**
 * Created by Administrator on 2018/12/28.
 */

public class CouponPresenter extends BasePresenter {
    private List<CouponTo>couponList=new ArrayList<>();

    public CouponPresenter(BaseActivity activity){
        initContext(activity);
    }

    public void getCouponList(){
        BaseParam param=new BaseParam();

        ApiClient.create(WashApi.class)
    }
}
