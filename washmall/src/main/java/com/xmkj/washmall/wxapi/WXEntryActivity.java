package com.xmkj.washmall.wxapi;

import com.google.gson.Gson;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.ShowMessageFromWX;
import com.tencent.mm.opensdk.modelmsg.WXAppExtendObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.Event;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;


import hzxmkuar.com.applibrary.domain.login.UserInfoTo;
import hzxmkuar.com.applibrary.domain.login.WeChatTokenTo;
import hzxmkuar.com.applibrary.domain.login.WechatLoginTo;
import hzxmkuar.com.applibrary.domain.login.WechatUserInfoTo;


public class WXEntryActivity extends BaseActivity implements IWXAPIEventHandler {

    public static final String APP_ID = "wx34575f0ea7a2a608";

    private IWXAPI mApi;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mApi = WXAPIFactory.createWXAPI(this, APP_ID, true);
        mApi.handleIntent(this.getIntent(), this);

    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {

        String code = ((SendAuth.Resp) baseResp).code;

        getToken(code);

    }


    private void getToken(String code) {
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx34575f0ea7a2a608&secret=ad7f7bcaee98e6a3f4463c63c784ef19&code=" + code + "&grant_type=authorization_code",
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        WeChatTokenTo tokenTo = new Gson().fromJson(responseInfo.result, WeChatTokenTo.class);
                        System.out.println(responseInfo.result+"tokenTo==");
                        getUSerInfo(tokenTo.getAccess_token(), tokenTo.getOpenid());
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {

                    }
                });
    }

    private void getUSerInfo(String access, String openId) {
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, "https://api.weixin.qq.com/sns/userinfo?access_token=" + access + "&openid=" + openId,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        WechatUserInfoTo wechatUserInfoTo = new Gson().fromJson(responseInfo.result, WechatUserInfoTo.class);
                        EventBus.getDefault().post(new Event<>("WechatLoginSuccess",wechatUserInfoTo));
                        finish();
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {

                    }
                });
    }



    @Override
    public void finish() {
        super.finish();
        //注释掉activity本身的过渡动画
        overridePendingTransition(0, 0);

    }




}
