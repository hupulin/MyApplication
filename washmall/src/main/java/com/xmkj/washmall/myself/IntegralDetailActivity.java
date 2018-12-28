package com.xmkj.washmall.myself;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.GridLayout;

import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.WebActivity;
import com.xmkj.washmall.base.util.StatueBarUtil;
import com.xmkj.washmall.databinding.IntegralRecordItemBinding;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hzxmkuar.com.applibrary.domain.myself.IntegralRecordTo;

/**
 * Created by Administrator on 2018/12/28.
 **/

public class IntegralDetailActivity extends BaseActivity {
    @BindView(R.id.record_layout)
    GridLayout recordLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integral_detail);
        ButterKnife.bind(this);
        StatueBarUtil.setStatueBarTextWhite(getWindow());
        setTitleName("积分明细");
        setRecordLayout();
    }

    private void setRecordLayout() {
        List<IntegralRecordTo> recordList = new ArrayList<>();
        IntegralRecordTo recordTo = new IntegralRecordTo();
        recordList.add(recordTo);
        recordList.add(recordTo);
        recordList.add(recordTo);
        for (int i = 0; i < recordList.size(); i++) {
            IntegralRecordTo mode = recordList.get(i);
            View mView = View.inflate(appContext, R.layout.integral_record_item, null);
            IntegralRecordItemBinding binding = DataBindingUtil.bind(mView);
            binding.recordName.setText(mode.getRecordName());
            binding.recordTime.setText(mode.getTime());
            binding.recordScore.setText(mode.getRecordScore());
            recordLayout.addView(mView);
        }

    }

    @OnClick(R.id.rule_des)
    public void onViewClicked() {
        Intent intent=new Intent(appContext, WebActivity.class);
        intent.putExtra("Type",1);
        startActivity(intent);
        goToAnimation(1);
    }
}
