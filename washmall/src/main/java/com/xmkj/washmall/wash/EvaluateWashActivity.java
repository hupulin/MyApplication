package com.xmkj.washmall.wash;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.wash.presenter.EvaluateWashPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/12/26.
 */

public class EvaluateWashActivity extends BaseActivity {

    @BindView(R.id.content)
    EditText content;
    private EvaluateWashPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate);
        ButterKnife.bind(this);
        setTitleName("评价订单");
        presenter = new EvaluateWashPresenter(this);
    }

    @OnClick(R.id.confirm)
    public void onViewClicked() {
        if (TextUtils.isEmpty(content.getText().toString())) {
            showMessage("请填写评价内容");
            return;
        }
        presenter.evaluate(content.getText().toString());
    }

    @Override
    protected void submitDataSuccess(Object data) {
        showMessage("评价成功");
        new Handler().postDelayed(() -> {
            finish();
            goToAnimation(2);
        }, 2500);
    }
}
