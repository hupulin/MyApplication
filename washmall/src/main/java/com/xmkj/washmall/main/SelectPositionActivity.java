package com.xmkj.washmall.main;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hzxmkuar.com.applibrary.impl.PermissionListener;

public class SelectPositionActivity extends BaseActivity implements PermissionListener, PoiSearch.OnPoiSearchListener {
    @BindView(R.id.city)
    TextView city;
    @BindView(R.id.search)
    EditText search;
    @BindView(R.id.current_position)
    TextView currentPosition;
    @BindView(R.id.around_layout)
    GridLayout aroundLayout;
    public AMapLocationClientOption mLocationOption = null;
    public AMapLocationClient mLocationClient = null;
    private PoiSearch poiSearch;
    private PoiSearch.Query query;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_position);
        ButterKnife.bind(this);
        setTitleName("选择地址");
        getPermission(Manifest.permission.ACCESS_FINE_LOCATION, this);
        setSearch();
    }

    private void setSearch() {
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                query = new PoiSearch.Query(search.getText().toString(), "", "0571");
                query.setPageSize(10);
                query.setPageNum(1);
                poiSearch = new PoiSearch(SelectPositionActivity.this, query);
                poiSearch.setOnPoiSearchListener(SelectPositionActivity.this);
                poiSearch.searchPOIAsyn();


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void accept(String permission) {
        setLocate();
    }

    private void setLocate() {
        query = new PoiSearch.Query("", "", "0571");
        query.setPageSize(10);
        query.setPageNum(1);
        poiSearch = new PoiSearch(this, query);
        poiSearch.setOnPoiSearchListener(this);
        AMapLocationClientOption option = new AMapLocationClientOption();
        mLocationOption = new AMapLocationClientOption();
        mLocationClient = new AMapLocationClient(this);
        mLocationClient.setLocationListener(mAMapLocationListener);
        option.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
        mLocationClient.setLocationOption(mLocationOption);
        mLocationClient.startLocation();

    }

    AMapLocationListener mAMapLocationListener = amapLocation -> {

        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                System.out.println(amapLocation + "");
                city.setText(amapLocation.getCity());
                currentPosition.setText(amapLocation.getDistrict() + amapLocation.getStreet() + amapLocation.getAoiName());
                mLocationClient.stopLocation();
                query = new PoiSearch.Query("", "", "");
                query.setPageSize(10);
                query.setPageNum(1);
                poiSearch = new PoiSearch(this, query);
                poiSearch.setBound(new PoiSearch.SearchBound( new LatLonPoint(amapLocation.getLatitude(),amapLocation.getLongitude()),10000));

                poiSearch.searchPOIAsyn();
                poiSearch.setOnPoiSearchListener(this);
            }
        }

    };

    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
        setAroundLayout(poiResult.getPois());
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }

    private void setAroundLayout(List<PoiItem> poiList) {

        aroundLayout.removeAllViews();
        for (int i = 0; i < poiList.size(); i++) {
            PoiItem poiItem = poiList.get(i);
            View mView = View.inflate(appContext, R.layout.position_around_item, null);
            ((TextView) mView.findViewById(R.id.address)).setText(poiItem + "");
            aroundLayout.addView(mView);
            mView.setOnClickListener(v -> {
                Intent intent = new Intent();
                intent.putExtra("Result", poiItem + "");
                intent.putExtra("lat", poiItem.getLatLonPoint().getLatitude() + "");
                intent.putExtra("lng", poiItem.getLatLonPoint().getLongitude() + "");
                setResult(20, intent);
                goToAnimation(2);
                finish();
            });
        }
    }

    @OnClick(R.id.re_locate)
    public void onViewClicked() {
        Toast.makeText(appContext,"重新定位中",Toast.LENGTH_LONG).show();
        mLocationClient.startLocation();
    }
}
