package com.xmkj.washmall.login;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jzxiang.pickerview.adapters.ArrayWheelAdapter;
import com.jzxiang.pickerview.config.PickerConfig;
import com.jzxiang.pickerview.wheel.WheelView;
import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.CommonDialog;
import com.xmkj.washmall.login.presenter.RegisterPresenter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hzxmkuar.com.applibrary.domain.login.CityTo;

/**
 * Created by Administrator on 2018/12/25.
 */

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.verification)
    EditText verification;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.confirm_password)
    EditText confirmPassword;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.detail_address)
    EditText detailAddress;
    @BindView(R.id.invitation_code)
    EditText invitationCode;
    private RegisterPresenter presenter;
    private String[] provinceData;
    private String[][] cityData;
    private HashMap<String, String[]> areaMap = new HashMap<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        presenter = new RegisterPresenter(this);
    }

    private void showPopWindow() {
        List<CityTo> cityList = presenter.cityToList;
        CommonDialog dialog = new CommonDialog(this, R.layout.edit_address_pop_layout, R.style.MyDialogStyle);

        WheelView wvProvince = dialog.findViewById(R.id.wv_address_province);
        WheelView wvCitys = dialog.findViewById(R.id.wv_address_city);
        WheelView wvArea = dialog.findViewById(R.id.wv_address_area);
        PickerConfig config = new PickerConfig();
        config.mThemeColor= Color.parseColor("#928DFF");
        config.mWheelTVNormalColor= Color.parseColor("#928DFF");
        config.mWheelTVSelectorColor= Color.parseColor("#928DFF");
        config.mToolBarTVColor= Color.parseColor("#928DFF");
        wvProvince.setConfig(config);
        wvCitys.setConfig(config);
        wvArea.setConfig(config);
        dialog.findViewById(R.id.cancel).setOnClickListener(view1 -> dialog.dismiss());
        dialog.findViewById(R.id.confirm).setOnClickListener(view1 -> {
            String id = cityList.get(wvProvince.getCurrentItem()).getId() + "," + cityList.get(wvProvince.getCurrentItem()).getSub().get(wvCitys.getCurrentItem()).getId() + "," + cityList.get(wvProvince.getCurrentItem()).getSub().get(wvCitys.getCurrentItem()).getSub().get(wvArea.getCurrentItem()).getId();
            dialog.dismiss();
            address.setTag(id);
            address.setText(cityList.get(wvProvince.getCurrentItem()).getArea() + " " + cityList.get(wvProvince.getCurrentItem()).getSub().get(wvCitys.getCurrentItem()).getArea() + " " + cityList.get(wvProvince.getCurrentItem()).getSub().get(wvCitys.getCurrentItem()).getSub().get(wvArea.getCurrentItem()).getArea());
        });

        dialog.show();

        wvProvince.setViewAdapter(new ArrayWheelAdapter<>(appContext, provinceData));
        wvCitys.setViewAdapter(new ArrayWheelAdapter<>(appContext, cityData[0]));
        wvArea.setViewAdapter(new ArrayWheelAdapter<>(appContext, areaMap.get(cityData[0][0])));
        wvProvince.addChangingListener((wheel, oldValue, newValue) -> {
            if (cityData!=null&&cityData[newValue]!=null&&cityData[newValue][0]!=null&&cityData[newValue][0].length()>0) {
                wvCitys.setViewAdapter(new ArrayWheelAdapter<>(appContext, cityData[newValue]));

                wvArea.setViewAdapter(new ArrayWheelAdapter<>(appContext, areaMap.get(cityData[newValue][0])));
            }
        });
        wvCitys.addChangingListener((wheel, oldValue, newValue) -> {
            wvArea.setViewAdapter(new ArrayWheelAdapter<>(appContext, areaMap.get(cityData[wvProvince.getCurrentItem()][wvCitys.getCurrentItem()])));
        });
    }

    @OnClick({R.id.verification, R.id.select_address, R.id.login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.verification:
                break;
            case R.id.select_address:
                getCityData();
                showPopWindow();
                break;
            case R.id.login:
                break;
        }
    }

    private void getCityData() {
            List<CityTo>cityList=presenter.cityToList;
            provinceData = new String[cityList.size()];
            cityData = new String[cityList.size()][];

            for (int i = 0; i < provinceData.length; i++) {
                provinceData[i] = cityList.get(i).getArea();

                if (cityList.get(i).getSub() != null) {
                    cityData[i] = new String[cityList.get(i).getSub().size()];


                    for (int j = 0; j < cityData[i].length; j++) {

                        cityData[i][j] = cityList.get(i).getSub().get(j).getArea();
                        if (cityList.get(i).getSub().get(j).getSub() != null && cityList.get(i).getSub().get(j).getSub().size() > 0) {
                            String[] areaList = new String[cityList.get(i).getSub().get(j).getSub().size()];
                            for (int k = 0; k < cityList.get(i).getSub().get(j).getSub().size(); k++) {
                                areaList[k] = cityList.get(i).getSub().get(j).getSub().get(k).getArea();
                            }
                            areaMap.put(cityData[i][j], areaList);
                        }
                    }

                }
            }


    }
}
