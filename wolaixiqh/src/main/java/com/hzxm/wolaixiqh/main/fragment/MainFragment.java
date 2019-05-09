package com.hzxm.wolaixiqh.main.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.hzxm.wolaixiqh.R;
import com.hzxm.wolaixiqh.base.BaseActivity;
import com.hzxm.wolaixiqh.base.BaseFragment;
import com.hzxm.wolaixiqh.main.EvaluateActivity;
import com.hzxm.wolaixiqh.main.ScanDecodeActivity;
import com.hzxm.wolaixiqh.main.adapter.OrderListAdapter;
import com.hzxm.wolaixiqh.main.present.MainPresenter;
import com.hzxm.wolaixiqh.news.NewsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

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
        adapter=new OrderListAdapter(baseActivity);
        setRecycleView(adapter,recycleView,presenter);
        adapter.setOnAddSelectListener(new OrderListAdapter.PickUpTheGoodsListener() {
            @Override
            public void onPickUpTheGoods(int id) {
                showDialog(id);
            }

            @Override
            public void onScanDecode(int id) {
                Intent intent=   new Intent(getActivity(), ScanDecodeActivity. class);
                intent.putExtra("id",id);
                startActivity(intent);

            }

            @Override
            public void feedbackInform(int id) {
                Intent intent=new Intent(getActivity(),EvaluateActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("type","1");
                startActivity(intent);
                goToAnimation(1);

            }

            @Override
            public void feedbackBack(int id) {
                Intent intent=new Intent(getActivity(),EvaluateActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("type","2");
                startActivity(intent);
                goToAnimation(1);

            }
        });
        return mView;
    }

    private void showDialog(int id){
        NiftyDialogBuilder dialog = NiftyDialogBuilder.getInstance(baseActivity);
        dialog.setContentView(R.layout.my_dialog_comment_layout);
        TextView title =dialog.findViewById(R.id.title);
        title.setText("取货成功后，请关上柜门");
        dialog.show();
        dialog.findViewById(R.id.confirm).setOnClickListener(view1 -> {
            Log.i("222", "showDialog: "+id);
            presenter.pickupConfirm(id);
            dialog.dismiss();

        });
        dialog.findViewById(R.id.parent).setOnClickListener(view1 -> dialog.dismiss());
        dialog.findViewById(R.id.cancel).setOnClickListener(view1 -> dialog.dismiss());
    }
    @OnClick({R.id.news_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.news_layout:
                startActivity(new Intent(baseActivity, NewsActivity.class));
                goToAnimation(1);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getOrderList(1);

    }

}
