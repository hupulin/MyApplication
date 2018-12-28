package com.xmkj.washmall.mall;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseActivity;

/**
 * Created by Administrator on 2018/12/28.
 */

public class AbortOrderActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_order);
        setTitleName("关于订单");
    }
}
