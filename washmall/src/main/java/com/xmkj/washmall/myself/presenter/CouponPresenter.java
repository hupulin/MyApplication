package com.xmkj.washmall.myself.presenter;

import android.os.Handler;

import com.xmkj.washmall.base.BaseFragment;
import com.xmkj.washmall.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import hzxmkuar.com.applibrary.domain.myself.CouponTo;

/**
 * Created by Administrator on 2018/12/28.
 */

public class CouponPresenter extends BasePresenter {
    private List<CouponTo>couponList=new ArrayList<>();
    public CouponPresenter(BaseFragment fragment){
        initContext(fragment);
    }
    public void getCouponList(int type){
        CouponTo couponTo=new CouponTo();
        couponTo.setType(type);
        couponList.add(couponTo);
        couponList.add(couponTo);
        couponList.add(couponTo);
        new Handler().postDelayed(() -> setRecycleList(couponList),500);
    }
}
