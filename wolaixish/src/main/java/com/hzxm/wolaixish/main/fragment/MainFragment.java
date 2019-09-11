package com.hzxm.wolaixish.main.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.hzxm.wolaixish.R;
import com.hzxm.wolaixish.base.ActivityManager;
import com.hzxm.wolaixish.base.BaseActivity;
import com.hzxm.wolaixish.base.BaseFragment;
import com.hzxm.wolaixish.login.LoginActivity;
import com.hzxm.wolaixish.main.ScanDecodeActivity;
import com.hzxm.wolaixish.main.WashDetailActivity;
import com.hzxm.wolaixish.main.adapter.OrderListAdapter;
import com.hzxm.wolaixish.main.present.MainPresenter;
import com.hzxm.wolaixish.news.NewActivity;
import com.hzxm.wolaixish.person.ChangeInfoActivity;
import com.hzxm.wolaixish.person.MyOrderListActivity;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.delivery.main.DeLiveryOrderListTo;
import rx.Observable;

/**
 * Created by Administrator on 2018/12/16.
 */

@SuppressLint("ValidFragment")
public class MainFragment extends BaseFragment {
    Unbinder unbinder;
    @BindView(R.id.recycle_view)
    LRecyclerView recycleView;
    private BaseActivity baseActivity;
    private MainPresenter presenter;
    private OrderListAdapter adapter;

    private DeLiveryOrderListTo.ListsEntity selectMode;

    //    private Context context;
    public MainFragment(BaseActivity activity) {
        this.baseActivity = activity;
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = View.inflate(appContext, R.layout.fragment_main, null);
        unbinder = ButterKnife.bind(this, mView);
        userInfoTo = userInfoHelp.getUserInfo();
        presenter = new MainPresenter(this);
        presenter.getOrderList(1);
        adapter = new OrderListAdapter(baseActivity);
        setRecycleView(adapter, recycleView, presenter);
        adapter.setOnAddSelectListener(new OrderListAdapter.PickUpTheGoodsListener() {
            @Override
            public void onPickUpTheGoods(int id) {
                showDialog(id);
            }

            @Override
            public void onScanDecode(int id) {
                Intent intent = new Intent(getActivity(), ScanDecodeActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);

            }

            @Override
            public void print(DeLiveryOrderListTo.ListsEntity mode) {
                selectMode=mode;


            }


        });
        return mView;
    }

    private void showDialog(int id) {
        NiftyDialogBuilder dialog = NiftyDialogBuilder.getInstance(baseActivity);
        dialog.setContentView(R.layout.my_dialog_comment_layout);
        TextView content = dialog.findViewById(R.id.content);
        content.setText("确认已将衣物放置完成，\n" +
                "请用户前来提取衣物！？");
        dialog.show();
        dialog.findViewById(R.id.confirm).setOnClickListener(view1 -> {
            presenter.notifyUserPickup(id);
            dialog.dismiss();

        });
        dialog.findViewById(R.id.parent).setOnClickListener(view1 -> dialog.dismiss());
        dialog.findViewById(R.id.cancel).setOnClickListener(view1 -> dialog.dismiss());
    }

    @OnClick({R.id.news_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.news_layout:
                startActivity(new Intent(baseActivity, NewActivity.class));
                goToAnimation(1);
                break;
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        presenter.getOrderList(1);

    }

    @Override
    public void recycleItemClick(View view, int position, Object data) {
        DeLiveryOrderListTo.ListsEntity mode = (DeLiveryOrderListTo.ListsEntity) data;
        Intent intent = new Intent(appContext, WashDetailActivity.class);
        intent.putExtra("OrderId", mode.getOrder_id());
        startActivity(intent);
        goToAnimation(1);
    }
}
