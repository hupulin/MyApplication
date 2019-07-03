package com.hzxm.wolaixiqh.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;

import com.hzxm.wolaixiqh.R;
import com.hzxm.wolaixiqh.base.BaseActivity;
import com.hzxm.wolaixiqh.main.present.EvaluatePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/12/26.
 */

public class EvaluateActivity extends BaseActivity {

    @BindView(R.id.content)
    EditText content;
    @BindView(R.id.image_layout)
    GridLayout imageLayout;
    private String type;
    private int id;
    EvaluatePresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate);
        ButterKnife.bind(this);
        type = getIntent().getStringExtra("type");
        id = getIntent().getIntExtra("id", 0);
//        setTitleName(type=="1"?"反馈告知":"反馈退回");
        setPostImageLayout(imageLayout);
        presenter = new EvaluatePresenter(this);

        setRightAndTitleText("确定", "1".equals(type)  ? "反馈告知" : "反馈退回");
    }

    @OnClick({R.id.right_text,})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.right_text:
                if (TextUtils.isEmpty(content.getText().toString())) {
                    showMessage("请填写反馈原因");
                    return;
                }
                    presenter.upLoadImage(imagePaths,id,content.getText().toString(),type);
                break;


        }
    }

    @Override
    protected void submitDataSuccess(Object data) {
        super.submitDataSuccess(data);
        finish();
    }

    @Override
    public void loadDataSuccess(Object data) {
        super.loadDataSuccess(data);
        finish();

    }
}
