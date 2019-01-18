package com.hzxm.wolaixish.news;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.hzxm.wolaixish.R;
import com.hzxm.wolaixish.base.BaseActivity;
import com.hzxm.wolaixish.news.adapter.NewsAdapter;
import com.hzxm.wolaixish.news.present.NewPresent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hzxmkuar.com.applibrary.domain.delivery.news.NewsTo;

/**
 * Created by Administrator on 2018/12/18.
 */

public class NewActivity extends BaseActivity {
    @BindView(R.id.recycle_view)
    LRecyclerView recycleView;
    @BindView(R.id.right_text)
    TextView rightText;
    private NewsAdapter adapter;
    private NewPresent present;
    private List<NewsTo.ListsEntity> newsList = new ArrayList<>();
    private List<NewsTo.ListsEntity> newsListIsread = new ArrayList<>();
    private boolean isSelect;//列表是否处于选择状态
    private String readIds = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);
        setRightAndTitleText("选择", "消息中心");
        adapter = new NewsAdapter(this);
        present = new NewPresent(this);
        present.getOrderList(1);
        setRecycleView(adapter, recycleView, present);
    }

    @OnClick({R.id.right_text})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.right_text:
                if (!isSelect) {
                    isSelect = true;
                    rightText.setText("标记为已读");
                    recycleView.setPullRefreshEnabled(false);
                    recycleView.setLoadMoreEnabled(false);
                    newsListIsread.clear();
                    for (int i = 0; i < newsList.size(); i++) {
                        NewsTo.ListsEntity mode = new NewsTo.ListsEntity();
                        mode.setIs_read(newsList.get(i).getIs_read());
                        mode.setContent(newsList.get(i).getContent());
                        mode.setDateline(newsList.get(i).getDateline());
                        mode.setTitle(newsList.get(i).getTitle());
                        mode.setId(newsList.get(i).getId());
                        newsListIsread.add(mode);
                    }
                    for (int i = 0; i < newsListIsread.size(); i++) {
                        newsListIsread.get(i).setIs_read(0);
                    }
                    adapter.setList(newsListIsread);

                } else {
                    isSelect = false;
                    recycleView.setLoadMoreEnabled(true);
                    recycleView.setPullRefreshEnabled(true);

                    for (int i = 0; i < newsListIsread.size(); i++) {
                        if (newsListIsread.get(i).getIs_read() == 1) {
                            readIds = readIds + newsListIsread.get(i).getId() + ",";
                        }
                    }
                    if (readIds.length() != 0) {
                        readIds = readIds.substring(0, readIds.length() - 1);
                        present.readMessage(readIds);
                        readIds = "";
                    } else {
                        rightText.setText("选择");
                        adapter.setList(newsList);
                    }

                }
                break;

        }
    }

    @Override
    protected void submitDataSuccess(Object data) {
        newsList = (List<NewsTo.ListsEntity>) data;
    }

    @Override
    public void loadDataSuccess(Object data) {
        rightText.setText("选择");

    }

    @Override
    public void recycleItemClick(View view, int position, Object data) {
        if (!isSelect) {
//        super.recycleItemClick(view, position, data);
            NewsTo.ListsEntity mode = (NewsTo.ListsEntity) data;
            Intent intent = new Intent(this, NewsDetailActivity.class);
            intent.putExtra("mode", mode);
            startActivity(intent);
        } else {
            if (newsListIsread.get(position).getIs_read() == 0) {
                newsListIsread.get(position).setIs_read(1);
            } else {
                newsListIsread.get(position).setIs_read(0);
            }
            adapter.notifyDataSetChanged();
        }
    }
}
