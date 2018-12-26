package com.xmkj.washmall.wash;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;

import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.WebActivity;
import com.xmkj.washmall.base.util.DateUtil;
import com.xmkj.washmall.base.util.PingFangTextView;
import com.xmkj.washmall.base.util.TimePickerExpect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;

/**
 * Created by Administrator on 2018/12/26.
 */

public class PlaceOrderActivity extends BaseActivity implements OnDateSetListener {

    @BindView(R.id.save_wardrobe)
    TextView saveWardrobe;
    @BindView(R.id.remark)
    EditText remark;
    @BindView(R.id.pickup_time)
    TextView pickupTime;
    @BindView(R.id.pickup_wardrobe)
    TextView pickupWardrobe;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
        ButterKnife.bind(this);
        setTitleName("下单");
    }

    @OnClick({R.id.pickup_time, R.id.pickup_wardrobe, R.id.confirm,R.id.save_wardrobe,R.id.rule_des})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rule_des:
                Intent intent=new Intent(appContext, WebActivity.class);
                intent.putExtra("Type",1);
                intent.putExtra("Title","规则");
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.save_wardrobe:
                selectWardRobeDialog(saveWardrobe);
                break;
            case R.id.pickup_time:
                initTimeDialog();
                break;
            case R.id.pickup_wardrobe:
                selectWardRobeDialog(pickupWardrobe);
                break;
            case R.id.confirm:
                break;
        }
    }

    private void selectWardRobeDialog(TextView selectText){
        List<String>wardList=new ArrayList<>(Arrays.asList("1号柜","2号柜","3号柜","4号柜"));
        NiftyDialogBuilder dialog=NiftyDialogBuilder.getInstance(this);
        dialog.setContentView(R.layout.dialog_select_wardrobe);
        GridLayout gridLayout=dialog.findViewById(R.id.grid_view);
        Observable.from(wardList).subscribe(s -> {
            PingFangTextView textView=new PingFangTextView(appContext);
            GridLayout.LayoutParams layoutParams=new GridLayout.LayoutParams();
            layoutParams.height= ViewGroup.LayoutParams.WRAP_CONTENT;
            layoutParams.width=getScreenWidth();
            layoutParams.bottomMargin=18*getScreenWidth()/750;
            layoutParams.topMargin=18*getScreenWidth()/750;
            textView.setLayoutParams(layoutParams);
            textView.setText(s);
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(Color.parseColor("#000000"));
            textView.setTextSize(10*getScreenWidth()/750);
            textView.setOnClickListener(v -> {
                dialog.dismiss();
                selectText.setText(s);
            });
            gridLayout.addView(textView);
        });
        dialog.show();
        dialog.findViewById(R.id.cancel).setOnClickListener(v -> dialog.dismiss());
        dialog.findViewById(R.id.confirm).setOnClickListener(v -> dialog.dismiss());
        dialog.findViewById(R.id.parent).setOnClickListener(v -> dialog.dismiss());
    }

    private void initTimeDialog() {
        TimePickerExpect mimePickerExpect = new TimePickerExpect.Builder()
                .setType(Type.YEAR_MONTH_DAY)
                .setCallBack(this)
                .setTitleStringId("选择时间")
                .setYearText("年")
                .setMonthText("月")
                .setThemeColor(Color.parseColor("#8273EF"))
                .setDayText("日")
                .setWheelItemTextSize(14)

                .setCyclic(false)
                .setMinMillseconds(System.currentTimeMillis())
                .buildNew();
        mimePickerExpect.show(getSupportFragmentManager(), "year_month_day");
    }

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
         pickupTime.setText(DateUtil.longToString(millseconds,DateUtil.mFormatDateString));
    }
}
