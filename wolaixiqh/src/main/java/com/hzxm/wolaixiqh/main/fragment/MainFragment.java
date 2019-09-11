package com.hzxm.wolaixiqh.main.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
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
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.hzxm.wolaixiqh.R;
import com.hzxm.wolaixiqh.base.BaseActivity;
import com.hzxm.wolaixiqh.base.BaseFragment;
import com.hzxm.wolaixiqh.main.EvaluateActivity;
import com.hzxm.wolaixiqh.main.ScanDecodeActivity;
import com.hzxm.wolaixiqh.main.WashDetailActivity;
import com.hzxm.wolaixiqh.main.adapter.OrderListAdapter;
import com.hzxm.wolaixiqh.main.present.MainPresenter;
import com.hzxm.wolaixiqh.news.NewsActivity;
import com.sunmi.peripheral.printer.ICallback;
import com.sunmi.peripheral.printer.InnerPrinterCallback;
import com.sunmi.peripheral.printer.InnerPrinterException;
import com.sunmi.peripheral.printer.InnerPrinterManager;
import com.sunmi.peripheral.printer.SunmiPrinterService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import hzxmkuar.com.applibrary.domain.delivery.main.DeLiveryOrderListTo;

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
    //    private Context context;
    public MainFragment(BaseActivity activity) {
        this.baseActivity = activity;
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
            mService = service;
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
        presenter.getOrderList();
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

            @Override
            public void print(DeLiveryOrderListTo.ListsEntity mode) {
                try {
//                    mService.printBarCode(mode.getOrder_sn().substring(0,16),7, 80, 1, 1, callback);
                    mService.printBitmap(creatBarcode(mode.getOrder_sn().replaceAll("w","W"),384,80), callback);


                    mService.printText("\n下单时间   "+mode.getOrder_time()+"\n" + "预约衣柜   "+mode.getDelivery_wardrobe_no()+"\n" + "存货衣柜   "+mode.getDeposit_wardrobe_name()+"\n" + "备注   "+mode.getRemarks()+"\n" + "订单编号   "+mode.getOrder_sn()+"\n", callback);


                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        return mView;
    }

    private void showDialog(int id){
        NiftyDialogBuilder dialog = NiftyDialogBuilder.getInstance(baseActivity);
        dialog.setContentView(R.layout.my_dialog_comment_layout);
        TextView content =dialog.findViewById(R.id.content);
        content.setText("取货成功后，请关上柜门");
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
        presenter.getOrderList();

    }

    @Override
    public void recycleItemClick(View view, int position, Object data) {
        DeLiveryOrderListTo.ListsEntity mode= (DeLiveryOrderListTo.ListsEntity) data;
        Intent intent=new Intent(appContext, WashDetailActivity.class);
        intent.putExtra("OrderId",mode.getOrder_id());
        startActivity(intent);
        goToAnimation(1);
    }

    private static final int BLACK = 0xff000000;
    private static final int WHITE = 0xFFFFFFFF;
    private static BarcodeFormat barcodeFormat= BarcodeFormat.CODE_128;
    public  static Bitmap creatBarcode(String contents, int desiredWidth, int desiredHeight) {
        MultiFormatWriter writer = new MultiFormatWriter();
        BitMatrix result=null;
        try {
            result = writer.encode(contents, barcodeFormat, desiredWidth,
                    desiredHeight);
        } catch (WriterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        int width = result.getWidth();
        int height = result.getHeight();
        int[] pixels = new int[width * height];
        // All are 0, or black, by default
        for (int y = 0; y < height; y++) {
            int offset = y * width;
            for (int x = 0; x < width; x++) {
                pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }
}
