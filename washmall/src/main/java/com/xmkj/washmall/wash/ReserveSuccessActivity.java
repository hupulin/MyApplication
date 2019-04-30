package com.xmkj.washmall.wash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/12/26.
 */

public class ReserveSuccessActivity extends BaseActivity {
    @BindView(R.id.rule_des)
    TextView ruleDes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_success);
        ButterKnife.bind(this);
        setTitleName("预定成功");
    }

    @OnClick({R.id.rule_des, R.id.repair, R.id.scan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rule_des:

                break;
            case R.id.repair:
                Intent intent=new Intent(appContext,RepairActivity.class);
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.scan:
                intent=new Intent(appContext,ScanActivity.class);
                intent.putExtra("OrderId",getIntent().getStringExtra("OrderId"));
                startActivity(intent);
                goToAnimation(1);

                break;
        }
    }
}
