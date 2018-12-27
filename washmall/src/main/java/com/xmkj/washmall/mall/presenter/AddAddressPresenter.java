package com.xmkj.washmall.mall.presenter;

import android.os.Handler;

import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.BasePresenter;
import com.xmkj.washmall.base.util.city.CityUtil;

import java.util.ArrayList;
import java.util.List;

import hzxmkuar.com.applibrary.domain.login.CityTo;
import hzxmkuar.com.applibrary.domain.main.ContactTo;

/**
 * Created by Administrator on 2018/12/27.
 */

public class AddAddressPresenter extends BasePresenter {
    private List<ContactTo > contactList=new ArrayList<>();
    public List<CityTo> cityToList;
    public AddAddressPresenter(BaseActivity activity){
        initContext(activity);
      initJson();
    }



    public void initJson() {
        cityToList = new ArrayList<>();
        showLoadingDialog();
        cityToList = CityUtil.initJson(activity);
        dismissLoadingDialog();

    }
}
