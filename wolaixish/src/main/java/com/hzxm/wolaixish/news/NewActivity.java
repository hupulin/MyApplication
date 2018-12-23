package com.hzxm.wolaixish.news;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.hzxm.wolaixish.R;
import com.hzxm.wolaixish.base.BaseActivity;
import com.hzxm.wolaixish.news.adapter.NewsAdapter;
import com.hzxm.wolaixish.person.Adapter.MyOrderListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *  Created by Administrator on 2018/12/18.
 */

public class NewActivity extends BaseActivity {
    @BindView(R.id.recycle_view)
    LRecyclerView recycleView;
    @BindView(R.id.right_text)
    TextView rightText;
    private NewsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);
        setRightAndTitleText("选择","消息中心");
         adapter=new NewsAdapter(this);
        setRecycleView(adapter,recycleView);
    }

    @OnClick({R.id.right_text})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.right_text:
                rightText.setText("标记为已读");

                adapter.notifyDataSetChanged();
                break;

        }
    }
}
