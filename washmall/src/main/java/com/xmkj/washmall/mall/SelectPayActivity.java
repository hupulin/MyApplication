package com.xmkj.washmall.mall;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alipay.sdk.app.PayTask;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.xmkj.washmall.R;
import com.xmkj.washmall.base.ActivityManager;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.Event;
import com.xmkj.washmall.myself.MallOrderActivity;
import com.xmkj.washmall.myself.MyOrderActivity;
import com.xmkj.washmall.myself.presenter.PayPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hzxmkuar.com.applibrary.domain.order.OrderResultTo;
import hzxmkuar.com.applibrary.domain.order.PayInfoTo;
import hzxmkuar.com.applibrary.domain.order.PayResult;
import hzxmkuar.com.applibrary.domain.order.WeChatPayTo;
import rx.Observable;

/**
 * Created by Administrator on 2018/12/27.
 */

public class SelectPayActivity extends BaseActivity {
    @BindView(R.id.balance_icon)
    View balanceIcon;
    @BindView(R.id.ali_icon)
    View aliIcon;
    @BindView(R.id.wechat_icon)
    View wechatIcon;
    @BindView(R.id.money)
    TextView money;
    @BindView(R.id.account)
    TextView account;
    private int payType;
    private PayPresenter presenter;
    private OrderResultTo mode;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1: {
                    if (payType == 1) {
                        Bundle bundle = msg.getData();
                        PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                        // 支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约时支付宝提供的公钥做验签
                        String resultStatus = payResult.getResultStatus();
                        if (TextUtils.equals(resultStatus, "9000")) {
                            Toast.makeText(appContext, "支付成功", Toast.LENGTH_SHORT).show();
                            EventBus.getDefault().post(new Event<>("PayResultData", 10));
                            Intent intent=new Intent(appContext, MallOrderActivity.class);

                            startActivity(intent);
                            Observable.from(ActivityManager.activityList).subscribe(activity -> activity.finish());
                            goToAnimation(1);
                            finish();

                        } else {
                            if (TextUtils.equals(resultStatus, "8000")) {
                                Toast.makeText(appContext, "支付结果确认中", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(appContext, "支付失败", Toast.LENGTH_SHORT).show();

                            }

                        }

                        break;
                    }


                }
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_pay);
        ButterKnife.bind(this);
        setTitleName("支付");
        presenter = new PayPresenter(this);
EventBus.getDefault().register(this);
        setView();
    }

    @SuppressLint("SetTextI18n")
    private void setView() {
        mode = (OrderResultTo) getIntent().getSerializableExtra("OrderResultTo");
        money.setText("￥" + (mode==null?getIntent().getStringExtra("Money"):mode.getTotal_amount()));
        account.setText("当前可用余额："+userInfoTo.getMyselfTo().getUser_info().getAccount());
    }

    @OnClick({R.id.balance_layout, R.id.ali_layout, R.id.wechat_layout, R.id.confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.balance_layout:
                balanceIcon.setBackgroundResource(R.drawable.address_select);
                aliIcon.setBackgroundResource(R.drawable.address_un_select);
                wechatIcon.setBackgroundResource(R.drawable.address_un_select);
                payType = 3;
                break;
            case R.id.ali_layout:
                balanceIcon.setBackgroundResource(R.drawable.address_un_select);
                aliIcon.setBackgroundResource(R.drawable.address_select);
                wechatIcon.setBackgroundResource(R.drawable.address_un_select);
                payType = 1;
                break;
            case R.id.wechat_layout:
                balanceIcon.setBackgroundResource(R.drawable.address_un_select);
                aliIcon.setBackgroundResource(R.drawable.address_un_select);
                wechatIcon.setBackgroundResource(R.drawable.address_select);
                payType = 2;
                break;
            case R.id.confirm:
                 if (payType==0){
                     showMessage("请选择支付方式");
                     return;
                 }
                   if (getIntent().getBooleanExtra("IsWash",false))
                    presenter.getWashPayInfo(mode==null?getIntent().getIntExtra("OrderId",0):mode.getOrder_id(), payType);
                else
                    presenter.getPayInfo(mode==null?getIntent().getIntExtra("OrderId",0):mode.getOrder_id(), payType);
                break;
        }
    }

    private void payBalanceDialog() {
        NiftyDialogBuilder dialog = NiftyDialogBuilder.getInstance(this);
        dialog.setContentView(R.layout.dialog_select_pay_balance);
        dialog.findViewById(R.id.cancel).setOnClickListener(v -> dialog.dismiss());
        dialog.findViewById(R.id.parent).setOnClickListener(v -> dialog.dismiss());
        dialog.findViewById(R.id.confirm).setOnClickListener(v -> {
            dialog.dismiss();

        });
        dialog.show();
    }

    @Override
    public void loadDataSuccess(Object data) {
        if (payType == 1) {

            PayInfoTo payInfoTo = new Gson().fromJson(JSON.toJSONString(data), PayInfoTo.class);
            payMoney(payInfoTo.getAlipay());
        }
        if (payType == 2) {
            WeChatPayTo payToInfo = (WeChatPayTo) data;
            WeChatPayTo.WxpayBean payTo = payToInfo.getWxpay();
            IWXAPI api = WXAPIFactory.createWXAPI(this, "wx34575f0ea7a2a608");
            PayReq request = new PayReq();
            request.appId = payTo.getAppid();
            request.partnerId = payTo.getPartnerid();
            request.prepayId = payTo.getPrepayid();
            request.packageValue = payTo.getPackageX();
            request.nonceStr = payTo.getNoncestr();
            request.timeStamp = payTo.getTimestamp();
            request.sign = payTo.getSign();
            api.sendReq(request);
        }
        if (payType==3){
            showMessage("支付成功");
            Intent intent=new Intent(appContext, MallOrderActivity.class);

            startActivity(intent);
            Observable.from(ActivityManager.activityList).subscribe(activity -> activity.finish());
            goToAnimation(1);
            finish();
        }
    }

    protected void payMoney(String url) {
        final String payPartner = url;
        Runnable payRunnable = () -> {
            PayTask aliPay = new PayTask(this);
            Map<String, String> result = aliPay.payV2(payPartner, true);
            Message msg1 = new Message();
            msg1.what = 1;
            msg1.obj = result;
            Bundle bundle = new Bundle();
            bundle.putString("OlderSid", url);
            msg1.setData(bundle);
            mHandler.sendMessage(msg1);
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void wxPaySuccess(Event event){
        if ("PayResultDataWX".equals(event.getType())){

            Intent intent=new Intent(appContext, MallOrderActivity.class);

            startActivity(intent);
            Observable.from(ActivityManager.activityList).subscribe(Activity::finish);
            goToAnimation(1);
            finish();
        }
    }
}
