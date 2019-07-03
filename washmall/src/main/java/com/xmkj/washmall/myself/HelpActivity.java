package com.xmkj.washmall.myself;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.myself.adapter.HelpCenterAdapter;
import com.xmkj.washmall.myself.presenter.HelpCenterPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import hzxmkuar.com.applibrary.domain.myself.HelpCenterTo;

/**
 * Created by Administrator on 2018/12/28.
 */

public class HelpActivity extends BaseActivity {
    @BindView(R.id.recycler_view)
    LRecyclerView recyclerView;
    @BindView(R.id.search)
    EditText search;
    private HelpCenterPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        ButterKnife.bind(this);
        setTitleName("帮助中心");
        presenter = new HelpCenterPresenter(this);
        setRecycleView(new HelpCenterAdapter(this), recyclerView, presenter);
        setSearch();
    }

    private void setSearch() {
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.getHelpData(search.getText().toString(), false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void recycleItemClick(View view, int position, Object data) {
        HelpCenterTo mode = (HelpCenterTo) data;
        Intent intent=new Intent(appContext,HelpListActivity.class);
        intent.putExtra("HelpCenterTo",mode);
        startActivity(intent);
        goToAnimation(1);

    }
}
