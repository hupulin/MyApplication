package com.hzxm.wolaixiqh.person;

import android.os.Bundle;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.hzxm.wolaixiqh.R;
import com.hzxm.wolaixiqh.base.BaseActivity;
import com.hzxm.wolaixiqh.person.Adapter.MyOrderListAdapter;
import com.hzxm.wolaixiqh.person.present.MyOderListPresent;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *  Created by Administrator on 2018/12/17.
 */

public class MyOrderListActivity extends BaseActivity {
    @BindView(R.id.recycle_view)
    LRecyclerView recycleView;
    private MyOderListPresent present;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorder_list);
        ButterKnife.bind(this);
        setTitleName("订单列表");
        present=new MyOderListPresent(this);
        present.getMyOrderList(1);
        setRecycleView(new MyOrderListAdapter(this),recycleView,present);
    }
}
