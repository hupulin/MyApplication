package com.xmkj.washmall.login.presenter;

import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.BasePresenter;
import com.xmkj.washmall.base.util.city.CityUtil;

import java.util.ArrayList;
import java.util.List;

import hzxmkuar.com.applibrary.domain.login.CityTo;

/**
 * Created by Administrator on 2018/12/25.
 */

public class RegisterPresenter extends BasePresenter {

    public List<CityTo> cityToList;

    public RegisterPresenter(BaseActivity activity) {
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
