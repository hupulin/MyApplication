package com.xmkj.washmall.wash;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.wash.presenter.RepairPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/12/26.
 */

public class RepairActivity extends BaseActivity {
    @BindView(R.id.confirm)
    TextView confirm;
    @BindView(R.id.content)
    EditText content;
    private RepairPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair);
        ButterKnife.bind(this);
        setTitleName("故障报修");
        presenter = new RepairPresenter(this);
    }

    @OnClick(R.id.confirm)
    public void onViewClicked() {
        if (TextUtils.isEmpty(content.getText().toString())) {
            showMessage("请填写报修内容");
            return;
        }
        presenter.repair(content.getText().toString());
    }

    @Override
    protected void submitDataSuccess(Object data) {
        showMessage("报修成功");
        new Handler().postDelayed(() -> {
            finish();
            goToAnimation(2);
        }, 2500);
    }

}
