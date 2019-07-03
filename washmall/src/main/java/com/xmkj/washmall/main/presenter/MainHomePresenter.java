package com.xmkj.washmall.main.presenter;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xmkj.washmall.base.BaseFragment;
import com.xmkj.washmall.base.BasePresenter;
import com.xmkj.washmall.base.MyObserver;

import com.xmkj.washmall.base.util.SpUtil;
import com.xmkj.washmall.main.fragment.HomeFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.MainHomeApi;
import hzxmkuar.com.applibrary.domain.BaseParam;
import hzxmkuar.com.applibrary.domain.MessageListTo;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.login.CityTo;
import hzxmkuar.com.applibrary.domain.main.MainAdParent;
import hzxmkuar.com.applibrary.domain.main.MainWardrobeParam;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2019/1/6.
 */

public class MainHomePresenter extends BasePresenter {
    private List<CityTo> cityList;
    public MainHomePresenter(BaseFragment fragment){
        initContext(fragment);
        getMainAd();
          initJson(true);
    }

    private void getMainAd(){
        BaseParam param=new BaseParam();
        param.setUid(userInfoTo.getUid());
        param.setHashid(userInfoTo.getHashid());
        param.setHash(getHashString(BaseParam.class,param));
        showLoadingDialog();
        ApiClient.create(MainHomeApi.class).getMainHomeAd(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageTo<MainAdParent>>(this) {
                    @Override
                    public void onNext(MessageTo<MainAdParent> msg) {
                        if (msg.getCode()==0)
                            getDataSuccess(msg.getData().getIndex_slideshow());
                    }
                }
        );
    }

    public void getWardrobeList(String lat,String lng,String search,String cityName){
        MainWardrobeParam param=new MainWardrobeParam();
        param.setLat(lat);
        param.setLng(lng);
        param.setKeywords(search);
        param.setHashid(userInfoTo.getHashid());
        param.setUid(userInfoTo.getUid());
        param.setPos_city(getCityId(cityName));
        SpUtil.put("SelectCityId",param.getPos_city());
        param.setHash(getHashString(MainWardrobeParam.class,param));
        ApiClient.create(MainHomeApi.class).getWardrobeList(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageListTo>(this) {
                    @Override
                    public void onNext(MessageListTo msg) {
                        if (msg.getCode()==0)
                            ((HomeFragment)mFragment).setWardrobe(msg.getDataList());
                    }
                }
        );
    }

    public void initJson(boolean startLocate) {

        JSONObject mJsonObj = null;
        try {
            StringBuffer sb = new StringBuffer();
            InputStream is = mFragment.getClass().getClassLoader().getResourceAsStream("assets/" + "province.json");
            int len = -1;
            byte[] buf = new byte[1024];
            while ((len = is.read(buf)) != -1) {
                sb.append(new String(buf, 0, len, "utf-8"));
            }
            is.close();
            mJsonObj = new JSONObject(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            cityList = new Gson().fromJson(mJsonObj.getJSONArray("RECORD").toString(), new TypeToken<List<CityTo>>() {
            }.getType());
            if (startLocate)
                ((HomeFragment)mFragment).mLocationClient.startLocation();

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public int getCityId(String cityName) {
        int cityId = 0;

        for (int i = 0; i < cityList.size(); i++) {
            if (cityList.get(i).getSub() != null) {
                for (CityTo cityTo : cityList.get(i).getSub()) {

                    if (cityTo.getArea().contains(cityName) || cityName.contains(cityTo.getArea())) {
                        cityId = cityTo.getId();
                    }
                }
            }
        }
        return cityId;
    }

}
