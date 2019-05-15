package com.xmkj.washmall.myself;

import android.annotation.SuppressLint;
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
import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.Event;
import com.xmkj.washmall.base.util.PingFangTextView;
import com.xmkj.washmall.myself.presenter.ExchangePresenter;
import com.zhy.autolayout.AutoLinearLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hzxmkuar.com.applibrary.domain.myself.ExchangeInfoTo;
import hzxmkuar.com.applibrary.domain.order.PayInfoTo;
import hzxmkuar.com.applibrary.domain.order.PayResult;
import hzxmkuar.com.applibrary.domain.order.WeChatPayTo;

/**
 * Created by Administrator on 2018/12/28.
 **/

public class ExchangeActivity extends BaseActivity {
    @BindView(R.id.money1)
    PingFangTextView money1;
    @BindView(R.id.send1)
    TextView send1;
    @BindView(R.id.discount1)
    TextView discount1;
    @BindView(R.id.money2)
    PingFangTextView money2;
    @BindView(R.id.send2)
    TextView send2;
    @BindView(R.id.discount2)
    TextView discount2;
    @BindView(R.id.money3)
    PingFangTextView money3;
    @BindView(R.id.send3)
    TextView send3;
    @BindView(R.id.discount3)
    TextView discount3;
    @BindView(R.id.ali_icon)
    View aliIcon;
    @BindView(R.id.wechat_icon)
    View wechatIcon;
    @BindView(R.id.first_layout)
    AutoLinearLayout firstLayout;
    @BindView(R.id.second_layout)
    AutoLinearLayout secondLayout;
    @BindView(R.id.third_layout)
    AutoLinearLayout thirdLayout;
    @BindView(R.id.amount)
    TextView amount;
    private List<ExchangeInfoTo> exchangeList;
    private ExchangeInfoTo selectExchange = new ExchangeInfoTo();
    private int payType = 1;

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
    private ExchangePresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);
        ButterKnife.bind(this);
        setTitleName("充值");
        presenter = new ExchangePresenter(this);
    }

    @Override
    public void loadDataSuccess(Object data) {
        exchangeList = (List<ExchangeInfoTo>) data;
        setView();

    }

    @SuppressLint("SetTextI18n")
    private void setView() {
        money1.setText(exchangeList.get(0).getRecharge_amount());
        send1.setText("送" + exchangeList.get(0).getGive_amount());
        discount1.setText(exchangeList.get(0).getTips());
        money2.setText(exchangeList.get(1).getRecharge_amount());
        send2.setText("送" + exchangeList.get(1).getGive_amount());
        discount2.setText(exchangeList.get(1).getTips());
        money3.setText(exchangeList.get(2).getRecharge_amount());
        send3.setText("送" + exchangeList.get(2).getGive_amount());
        discount3.setText(exchangeList.get(2).getTips());
        amount.setText("￥"+userInfoTo.getMyselfTo().getUser_info().getAccount());
    }

    @OnClick({R.id.ali_layout, R.id.wechat_layout, R.id.confirm, R.id.first_layout, R.id.second_layout, R.id.third_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ali_layout:
                aliIcon.setBackgroundResource(R.drawable.address_select);
                wechatIcon.setBackgroundResource(R.drawable.address_un_select);
                payType = 1;
                break;
            case R.id.wechat_layout:
                aliIcon.setBackgroundResource(R.drawable.address_un_select);
                wechatIcon.setBackgroundResource(R.drawable.address_select);
                payType = 2;
                break;
            case R.id.confirm:
                if (selectExchange.getPackage_id() == 0) {
                    showMessage("选择充值金额");
                    return;
                }
                presenter.pay(selectExchange.getPackage_id() + "", payType);
                break;
            case R.id.first_layout:
                selectExchange = exchangeList.get(0);
                firstLayout.setBackgroundResource(R.drawable.exchange_select);
                secondLayout.setBackgroundResource(R.drawable.exchange_un_select);
                thirdLayout.setBackgroundResource(R.drawable.exchange_un_select);
                break;
            case R.id.second_layout:
                selectExchange = exchangeList.get(1);
                secondLayout.setBackgroundResource(R.drawable.exchange_select);
                firstLayout.setBackgroundResource(R.drawable.exchange_un_select);
                thirdLayout.setBackgroundResource(R.drawable.exchange_un_select);
                break;
            case R.id.third_layout:
                selectExchange = exchangeList.get(2);
                thirdLayout.setBackgroundResource(R.drawable.exchange_select);
                secondLayout.setBackgroundResource(R.drawable.exchange_un_select);
                firstLayout.setBackgroundResource(R.drawable.exchange_un_select);
                break;
        }
    }

    @Override
    protected void submitDataSuccess(Object data) {
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
}
