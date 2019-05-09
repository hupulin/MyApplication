package com.xmkj.washmall.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;


import com.tencent.smtt.sdk.WebView;
import com.xmkj.washmall.MainApp;
import com.xmkj.washmall.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/9/27.
 **/

public class WebActivity extends BaseActivity {

    @BindView(R.id.web_view)
    WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        setTitleName(getIntent().getStringExtra("Title"));
        webView.loadUrl(TextUtils.isEmpty(getIntent().getStringExtra("BannerUrl"))?MainApp.webBaseUrl + getIntent().getIntExtra("Type", 0):getIntent().getStringExtra("BannerUrl"));
    }
}
