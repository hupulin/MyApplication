package com.xmkj.washmall.myself;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import hzxmkuar.com.applibrary.domain.myself.HelpCenterTo;
import rx.Observable;

/**
 * Created by 1ONE on 2019/6/10.
 */

public class HelpListActivity extends BaseActivity {

    @BindView(R.id.help_layout)
    GridLayout helpLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_list);
        ButterKnife.bind(this);
        setTitleName("");
        setView();
    }

    private void setView() {
        HelpCenterTo mode = (HelpCenterTo) getIntent().getSerializableExtra("HelpCenterTo");
        setTitleName(mode.getCate_name());
        Observable.from(mode.getList2()).subscribe(help -> {
            View mView = View.inflate(appContext, R.layout.help_list_item, null);
            ((TextView) mView.findViewById(R.id.name)).setText(help.getDoc_title());
            helpLayout.addView(mView);
            mView.setOnClickListener(v -> {
                Intent intent = new Intent(appContext, HelpDetailActivity.class);
                intent.putExtra("Id", help.getId());
                startActivity(intent);
                goToAnimation(1);
            });
        });
    }
}
