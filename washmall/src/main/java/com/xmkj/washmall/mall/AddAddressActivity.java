package com.xmkj.washmall.mall;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jzxiang.pickerview.adapters.ArrayWheelAdapter;
import com.jzxiang.pickerview.config.PickerConfig;
import com.jzxiang.pickerview.wheel.WheelView;
import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.CommonDialog;
import com.xmkj.washmall.base.util.PingFangTextView;
import com.xmkj.washmall.mall.presenter.AddAddressPresenter;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hzxmkuar.com.applibrary.domain.login.CityTo;
import hzxmkuar.com.applibrary.domain.order.AddressTo;

/**
 * Created by Administrator on 2018/12/27.
 */

public class AddAddressActivity extends BaseActivity {

    @BindView(R.id.contact_name)
    EditText contactName;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.select_area)
    TextView selectArea;
    @BindView(R.id.detail_address)
    EditText detailAddress;
    @BindView(R.id.confirm)
    PingFangTextView confirm;
    private String[] provinceData;
    private String[][] cityData;
    private HashMap<String, String[]> areaMap = new HashMap<>();
    private AddAddressPresenter presenter;
    private AddressTo mode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        ButterKnife.bind(this);
        setTitleName("添加地址");
        presenter = new AddAddressPresenter(this);
        mode = (AddressTo) getIntent().getSerializableExtra("AddressTo");
        setView();
    }

    private void setView() {
        if (mode != null) {
            setTitleName("编辑地址");
            contactName.setText(mode.getConsignee());
            phone.setText(mode.getTelephone());
            detailAddress.setText(mode.getAddress());
            selectArea.setText(mode.getProvince() + " " + mode.getCity() + " " + mode.getArea());
confirm.setText("确定");
        }
    }

    @OnClick({R.id.select_area, R.id.confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.select_area:
                getCityData();
                showPopWindow();
                break;
            case R.id.confirm:
                if (TextUtils.isEmpty(contactName.getText().toString())) {
                    showMessage("请填写联系人");
                    return;
                }
                if (!checkPhone(phone.getText().toString()))
                    return;
                if (TextUtils.isEmpty(selectArea.getText().toString())) {
                    showMessage("请选择区域");
                    return;
                }
                if (TextUtils.isEmpty(detailAddress.getText().toString())) {
                    showMessage("请填写详细地址");
                    return;
                }
                if (mode == null)
                    presenter.addAddress(contactName.getText().toString(), phone.getText().toString(), ((String) selectArea.getText()).split(" ")[0], ((String) selectArea.getText()).split(" ")[1], ((String) selectArea.getText()).split(" ")[2], detailAddress.getText().toString());
                else
                    presenter.editAddress(mode.getId(), contactName.getText().toString(), phone.getText().toString(), ((String) selectArea.getText()).split(" ")[0], ((String) selectArea.getText()).split(" ")[1], ((String) selectArea.getText()).split(" ")[2], detailAddress.getText().toString());
                break;
        }
    }

    private void showPopWindow() {
        List<CityTo> cityList = presenter.cityToList;
        CommonDialog dialog = new CommonDialog(this, R.layout.edit_address_pop_layout, R.style.MyDialogStyle);

        WheelView wvProvince = dialog.findViewById(R.id.wv_address_province);
        WheelView wvCitys = dialog.findViewById(R.id.wv_address_city);
        WheelView wvArea = dialog.findViewById(R.id.wv_address_area);
        PickerConfig config = new PickerConfig();
        config.mThemeColor = Color.parseColor("#928DFF");
        config.mWheelTVNormalColor = Color.parseColor("#928DFF");
        config.mWheelTVSelectorColor = Color.parseColor("#928DFF");
        config.mToolBarTVColor = Color.parseColor("#928DFF");
        wvProvince.setConfig(config);
        wvCitys.setConfig(config);
        wvArea.setConfig(config);
        dialog.findViewById(R.id.cancel).setOnClickListener(view1 -> dialog.dismiss());
        dialog.findViewById(R.id.confirm).setOnClickListener(view1 -> {
            String id = cityList.get(wvProvince.getCurrentItem()).getId() + "," + cityList.get(wvProvince.getCurrentItem()).getSub().get(wvCitys.getCurrentItem()).getId() + "," + cityList.get(wvProvince.getCurrentItem()).getSub().get(wvCitys.getCurrentItem()).getSub().get(wvArea.getCurrentItem()).getId();
            dialog.dismiss();
            selectArea.setTag(id);
            selectArea.setText(cityList.get(wvProvince.getCurrentItem()).getArea() + " " + cityList.get(wvProvince.getCurrentItem()).getSub().get(wvCitys.getCurrentItem()).getArea() + " " + cityList.get(wvProvince.getCurrentItem()).getSub().get(wvCitys.getCurrentItem()).getSub().get(wvArea.getCurrentItem()).getArea());
        });

        dialog.show();

        wvProvince.setViewAdapter(new ArrayWheelAdapter<>(appContext, provinceData));
        wvCitys.setViewAdapter(new ArrayWheelAdapter<>(appContext, cityData[0]));
        wvArea.setViewAdapter(new ArrayWheelAdapter<>(appContext, areaMap.get(cityData[0][0])));
        wvProvince.addChangingListener((wheel, oldValue, newValue) -> {
            if (cityData != null && cityData[newValue] != null && cityData[newValue][0] != null && cityData[newValue][0].length() > 0) {
                wvCitys.setViewAdapter(new ArrayWheelAdapter<>(appContext, cityData[newValue]));

                wvArea.setViewAdapter(new ArrayWheelAdapter<>(appContext, areaMap.get(cityData[newValue][0])));
            }
        });
        wvCitys.addChangingListener((wheel, oldValue, newValue) -> {
            wvArea.setViewAdapter(new ArrayWheelAdapter<>(appContext, areaMap.get(cityData[wvProvince.getCurrentItem()][wvCitys.getCurrentItem()])));
        });
    }

    private void getCityData() {
        List<CityTo> cityList = presenter.cityToList;
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

    @Override
    public void loadDataSuccess(Object data) {
        showMessage("添加地址成功");
        new Handler().postDelayed(this::finish, 2500);
    }

    @Override
    protected void submitDataSuccess(Object data) {
        showMessage("修改地址成功");
        new Handler().postDelayed(this::finish, 2500);
    }
}
