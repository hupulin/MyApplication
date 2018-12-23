package com.hzxm.wolaixish.person;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.hzxm.wolaixish.R;
import com.hzxm.wolaixish.base.BaseActivity;
import com.hzxm.wolaixish.base.Constant;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hzxmkuar.com.applibrary.impl.PermissionListener;

/**
 *  Created by Administrator on 2018/12/16.
 */

public class ChangeInfoActivity  extends BaseActivity implements OnDateSetListener {
    @BindView(R.id.man)
    TextView man;
    @BindView(R.id.women)
    TextView women;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_info);
        ButterKnife.bind(this);
        setView();
    }

    private void setView() {

        setRightAndTitleText("保存","编辑个人资料");

    }
    @SuppressLint("SetTextI18n")
    @OnClick({R.id.right_text,R.id.birthday,R.id.change_password,R.id.man,R.id.women,R.id.logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.right_text:
                Toast.makeText(this,"333",Toast.LENGTH_LONG).show();
                break;
            case R.id.birthday:
                showBirthday();
                break;
            case R.id.change_password:
                startActivity(new Intent(this,ChangePasswordActivity.class));
                goToAnimation(1);
                break;
            case R.id.man:
                man.setTextColor(Color.parseColor("#000000"));
                man.setBackground(getResources().getDrawable(R.drawable.select_man_bg));
                women.setBackground(getResources().getDrawable(R.drawable.transparent));
                women.setTextColor(Color.parseColor("#2E23FF"));
                break;
            case R.id.women:
                women.setTextColor(Color.parseColor("#000000"));
                women.setBackground(getResources().getDrawable(R.drawable.select_women_bg));
                man.setTextColor(Color.parseColor("#2E23FF"));
                man.setBackground(getResources().getDrawable(R.drawable.transparent));
                break;
            case R.id.logout:
                NiftyDialogBuilder dialog=NiftyDialogBuilder.getInstance(this);
                dialog.setContentView(R.layout.my_dialog_comment_layout);
                dialog.show();
                dialog.findViewById(R.id.confirm).setOnClickListener(view1 -> {
                    dialog.dismiss();

                });
                dialog.findViewById(R.id.parent).setOnClickListener(view1 -> dialog.dismiss());
                dialog.findViewById(R.id.cancel).setOnClickListener(view1 -> dialog.dismiss());
                break;
        }
    }
    public void showBirthday() {
        long tenYears = 60L * 365 * 1000 * 60 * 60 * 24L;
        long oneYears = 365 * 1000 * 60 * 60 * 24L;
        TimePickerDialog mDialogAll = new TimePickerDialog.Builder().setCallBack((OnDateSetListener) this).
                setCancelStringId(Constant.CANCEL).
                setTitleStringId(Constant.CHOOSING_BIRTHDAY).
                setSureStringId(Constant.CONFIRM).
                setMonthText(Constant.MONTH).setDayText(Constant.DAY).setMinMillseconds(System.currentTimeMillis() - tenYears).setMaxMillseconds(System.currentTimeMillis() + oneYears).setCurrentMillseconds(System.currentTimeMillis()).
                setCyclic(false).setThemeColor(Color.parseColor("#6d75a4")).setType(Type.YEAR_MONTH_DAY).setWheelItemTextNormalColor(R.color.timetimepicker_default_text_color).setWheelItemTextSelectorColor(R.color.timepicker_toolbar_bg).setWheelItemTextSize(12).build();
        mDialogAll.show(getSupportFragmentManager(), "");

    }


    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
//       millseconds birthday.setText(DateUtil.longToDate(millSeconds, DateUtil.mDateFormatStringLine));
    }
}
