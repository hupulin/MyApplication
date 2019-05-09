package com.xmkj.washmall.myself;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.EditText;

import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.myself.presenter.FeedbackPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/12/28.
 **/

public class FeedBackActivity extends BaseActivity {
    @BindView(R.id.content)
    EditText content;
    private FeedbackPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        ButterKnife.bind(this);
        setTitleName("意见反馈");
        presenter = new FeedbackPresenter(this);
    }

    @OnClick(R.id.confirm)
    public void onViewClicked() {
        if (TextUtils.isEmpty(content.getText().toString())){
            showMessage("请填写反馈内容");
            return;
        }
        presenter.feedbck(content.getText().toString());

    }

    @Override
    protected void submitDataSuccess(Object data) {
        showMessage("意见反馈成功");
        new Handler().postDelayed(this::finish,2500);
    }
}
