package com.xmkj.washmall.main.fragment;

import android.Manifest;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseFragment;
import com.xmkj.washmall.base.banner.HomeBannerHolderView;
import com.xmkj.washmall.base.util.SpUtil;
import com.xmkj.washmall.databinding.MainWardrobeItemBinding;
import com.xmkj.washmall.main.SelectPositionActivity;
import com.xmkj.washmall.main.presenter.MainHomePresenter;
import com.xmkj.washmall.message.MessageCenterActivity;
import com.xmkj.washmall.myself.ExchangeActivity;
import com.xmkj.washmall.myself.MyOrderActivity;
import com.xmkj.washmall.wash.SelectWashActivity;
import com.xmkj.washmall.wash.WardrobeActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import hzxmkuar.com.applibrary.domain.main.MainHomeAdTo;
import hzxmkuar.com.applibrary.domain.main.MainWardrobeTo;
import hzxmkuar.com.applibrary.impl.PermissionListener;

/**
 * Created by Administrator on 2018/12/25.
 **/

public class HomeFragment extends BaseFragment implements PermissionListener {
    @BindView(R.id.banner)
    ConvenientBanner<MainHomeAdTo> banner;
    Unbinder unbinder;
    @BindView(R.id.wardrobe_layout)
    GridLayout wardrobeLayout;
    public AMapLocationClientOption mLocationOption = null;
    public AMapLocationClient mLocationClient = null;
    @BindView(R.id.city_name)
    TextView cityName;
    @BindView(R.id.search_content)
    EditText searchContent;
    private String lat;
    private String lng;
    private MainHomePresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = View.inflate(container.getContext(), R.layout.fragment_home, null);
        unbinder = ButterKnife.bind(this, rootView);
        presenter = new MainHomePresenter(this);
        getPermission(Manifest.permission.ACCESS_FINE_LOCATION, this);
         setSearch();
        return rootView;
    }

    private void setSearch() {
        searchContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
             presenter.getWardrobeList(lat,lng,searchContent.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void setWardrobe(List<MainWardrobeTo> wardrobeList) {
        wardrobeLayout.removeAllViews();
        wardrobeList = new Gson().fromJson(JSON.toJSONString(wardrobeList), new TypeToken<List<MainWardrobeTo>>() {
        }.getType());
        for (int i = 0; i < wardrobeList.size(); i++) {
            View mView = View.inflate(appContext, R.layout.main_wardrobe_item, null);
            MainWardrobeTo wardrobeTo = wardrobeList.get(i);
            MainWardrobeItemBinding bind = DataBindingUtil.bind(mView);
            bind.address.setText(wardrobeTo.getDistance());
            bind.name.setText(wardrobeTo.getWardrobe_name());
            bind.statue.setText(wardrobeTo.getIs_full());
            bind.wardrobeWork.setText(wardrobeTo.getStatus_txt());
            displayImage(bind.imageView, wardrobeTo.getWardrobe_img());
            wardrobeLayout.addView(mView);
            mView.setOnClickListener(v -> {
                Intent intent = new Intent(appContext, WardrobeActivity.class);
                intent.putExtra("WardrobeId", wardrobeTo.getId());
                intent.putExtra("WardrobeName", wardrobeTo.getWardrobe_name());
                startActivity(intent);
                goToAnimation(1);
            });
        }
    }


    public void setBanner(List<MainHomeAdTo> adList) {
        banner.setPages(HomeBannerHolderView::new, adList).setPageIndicator(new int[]{R.drawable.page_indicate_un_focus, R.drawable.page_indicate_focus});
        banner.startTurning(5000);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.wash, R.id.recharge, R.id.order, R.id.scan, R.id.city_name, R.id.locate_position,R.id.message_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.wash:
                Intent intent = new Intent(appContext, SelectWashActivity.class);
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.recharge:
                intent = new Intent(appContext, ExchangeActivity.class);
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.order:
                intent = new Intent(appContext, MyOrderActivity.class);
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.scan:
                break;
            case R.id.city_name:
            case R.id.locate_position:
                intent = new Intent(appContext, SelectPositionActivity.class);
                startActivityForResult(intent, 10);
                goToAnimation(1);
                break;
            case R.id.message_layout:
                intent = new Intent(appContext, MessageCenterActivity.class);
                startActivityForResult(intent, 10);
                goToAnimation(1);
                break;
        }
    }

    @Override
    public void loadDataSuccess(Object data) {
        List<MainHomeAdTo> adList = (List<MainHomeAdTo>) data;
        setBanner(adList);
    }


    private void setLocate() {
        AMapLocationClientOption option = new AMapLocationClientOption();
        mLocationOption = new AMapLocationClientOption();
        mLocationClient = new AMapLocationClient(getActivity());
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
                cityName.setText(amapLocation.getStreet());
                mLocationClient.stopLocation();
                presenter.getWardrobeList(amapLocation.getLatitude() + "", amapLocation.getLongitude() + "",searchContent.getText().toString());
                lat = amapLocation.getLatitude() + "";
                lng = amapLocation.getLongitude() + "";
                SpUtil.put("Lat", lat);
                SpUtil.put("Lng", lng);
            }
        }
    };

    @Override
    public void accept(String permission) {
        setLocate();
    }

    @Override
    public void refuse(String permission) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 20) {

            String poi = data.getStringExtra("Result");
            cityName.setText(poi + "");
            presenter.getWardrobeList(data.getStringExtra("lat"), data.getStringExtra("lng"),searchContent.getText().toString());
            lat = data.getStringExtra("lat");
            lng = data.getStringExtra("lng");

        }
    }
}
