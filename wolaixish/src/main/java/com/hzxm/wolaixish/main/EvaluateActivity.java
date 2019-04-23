package com.hzxm.wolaixish.main;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hzxm.wolaixish.R;
import com.hzxm.wolaixish.base.BaseActivity;
import com.makeramen.roundedimageview.RoundedImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/12/26.
 */

public class EvaluateActivity extends BaseActivity {

    @BindView(R.id.content)
    EditText content;
    @BindView(R.id.image_layout)
    GridLayout imageLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate);
        ButterKnife.bind(this);
        setTitleName("反馈退回");
        setPostImageLayout(imageLayout);
        setRightAndTitleText("反馈退回","确定");
    }
}
