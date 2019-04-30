package com.xmkj.washmall.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;


import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.xmkj.washmall.R;
import com.xmkj.washmall.base.Event;


import org.greenrobot.eventbus.EventBus;



public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private IWXAPI api;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        api = WXAPIFactory.createWXAPI(this, "wx34575f0ea7a2a608");
        api.handleIntent(getIntent(), this);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {
    }

    @Override
    public void onResp(BaseResp baseResp) {
        if (baseResp.errCode == -2) {
            Toast.makeText(WXPayEntryActivity.this, "取消支付", Toast.LENGTH_LONG).show();
        } else if (baseResp.errCode == 0) {
            Toast.makeText(WXPayEntryActivity.this, "支付成功", Toast.LENGTH_LONG).show();
            EventBus.getDefault().post(new Event<>("PayResultDataWX",10));
            EventBus.getDefault().post(new Event<>("PayResultData",10));
        } else {
            Toast.makeText(WXPayEntryActivity.this, "支付错误", Toast.LENGTH_LONG).show();
        }
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }


}