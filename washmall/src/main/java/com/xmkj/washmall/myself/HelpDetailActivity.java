package com.xmkj.washmall.myself;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.myself.presenter.HelpDetailPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import hzxmkuar.com.applibrary.domain.myself.HelpDetailTo;

/**
 * Created by 1ONE on 2019/5/26.
 */

public class HelpDetailActivity extends BaseActivity {
    @BindView(R.id.content)
    TextView content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_detail);
        ButterKnife.bind(this);
        setTitleName("");
        HelpDetailPresenter presenter=new HelpDetailPresenter(this);
    }

    @Override
    public void loadDataSuccess(Object data) {
        HelpDetailTo mode= (HelpDetailTo) data;
        content.setText(mode.getContent());
        setTitleName(mode.getDoc_title());
    }
}
