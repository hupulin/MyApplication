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
import com.sunmi.peripheral.printer.ICallback;
import com.sunmi.peripheral.printer.InnerPrinterCallback;
import com.sunmi.peripheral.printer.InnerPrinterException;
import com.sunmi.peripheral.printer.InnerPrinterManager;
import com.sunmi.peripheral.printer.SunmiPrinterService;

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
    private SunmiPrinterService mService;
    private DeLiveryOrderListTo.ListsEntity selectMode;

    //    private Context context;
    public MainFragment(BaseActivity activity) {
        this.baseActivity = activity;
        try {
            InnerPrinterManager.getInstance().bindService(appContext, innerPrinterCallback);

        } catch (InnerPrinterException e) {
            showMessage(e + "");
            e.printStackTrace();
        }
    }

    ICallback callback = new ICallback() {
        @Override
        public IBinder asBinder() {
            return null;
        }

        @Override
        public void onRunResult(boolean isSuccess) throws RemoteException {

        }

        @Override
        public void onReturnString(String result) throws RemoteException {
            showMessage(result + "result");
        }

        @Override
        public void onRaiseException(int code, String msg) throws RemoteException {
            showMessage(msg + "msg");
        }

        @Override
        public void onPrintResult(int code, String msg) throws RemoteException {
            showMessage(msg + "result======");
        }
    };


    InnerPrinterCallback innerPrinterCallback = new InnerPrinterCallback() {

        @Override
        protected void onConnected(SunmiPrinterService service) {
              mService=service;
        }

        @Override
        protected void onDisconnected() {
            System.out.println("连接失败");
            showMessage("连接失败");
        }
    };


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
                try {
                    mService.printBarCode(mode.getOrder_sn(), 8, 80, 2, 0, callback);

                    mService.printText("\n下单时间   "+mode.getOrder_time()+"\n" + "预约衣柜   "+mode.getDelivery_wardrobe_no()+"\n" + "存货衣柜   "+mode.getDeposit_wardrobe_name()+"\n" + "备注   "+mode.getRemarks()+"\n" + "订单编号   "+mode.getOrder_sn()+"\n", callback);


                } catch (RemoteException e) {
                    e.printStackTrace();
                }

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
