package com.hzxm.wolaixiqh.news;

import android.os.Bundle;
import android.widget.TextView;

import com.hzxm.wolaixiqh.R;
import com.hzxm.wolaixiqh.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import hzxmkuar.com.applibrary.domain.delivery.news.NewsTo;

/**
 * Created by Administrator on 2019/1/16.
 */

public class NewsDetailActivity extends BaseActivity {
    private NewsTo.ListsEntity mode;
    @BindView(R.id.contend)
    TextView contend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);
        mode=(NewsTo.ListsEntity)getIntent().getSerializableExtra("mode");
        contend.setText(mode.getContent());
        setTitleName("详情");
    }
}
