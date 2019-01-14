package com.xmkj.washmall.mall.presenter;

import android.os.Handler;

import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.BaseFragment;
import com.xmkj.washmall.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import hzxmkuar.com.applibrary.domain.myself.CouponTo;

/**
 * Created by Administrator on 2018/12/28.
 */

public class SelectCouponPresenter extends BasePresenter {
    private List<CouponTo>couponList=new ArrayList<>();
    public SelectCouponPresenter(BaseActivity activity){
        initContext(activity);
    }
    public void getCouponList(int type){
        CouponTo couponTo=new CouponTo();
//        couponTo.setType(type);
        couponList.add(couponTo);
        couponList.add(couponTo);
        couponList.add(couponTo);
        new Handler().postDelayed(() -> setRecycleList(couponList),500);
    }
}
