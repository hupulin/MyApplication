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

public class AddressPresenter extends BasePresenter {
    private List<ContactTo > contactList=new ArrayList<>();
    public List<CityTo> cityToList;
    public AddressPresenter(BaseActivity activity){
        initContext(activity);
        getAddressList();
    }

    public void getAddressList(){
        contactList.add(new ContactTo());
        contactList.add(new ContactTo());
        contactList.add(new ContactTo());
        contactList.add(new ContactTo());
        new Handler().postDelayed(() -> setRecycleList(contactList),500);
    }

    public void initJson() {
        cityToList = new ArrayList<>();
        showLoadingDialog();
        cityToList = CityUtil.initJson(activity);
        dismissLoadingDialog();

    }
}
