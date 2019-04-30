package com.xmkj.washmall.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.tencent.connect.common.Constants;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.open.utils.HttpUtils;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.Event;
import com.xmkj.washmall.base.WebActivity;
import com.xmkj.washmall.base.util.StatueBarUtil;
import com.xmkj.washmall.login.presenter.LoginPresenter;
import com.xmkj.washmall.main.MainActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hzxmkuar.com.applibrary.domain.login.QQUserTo;
import hzxmkuar.com.applibrary.domain.login.UserInfoTo;
import hzxmkuar.com.applibrary.domain.login.WechatLoginTo;
import hzxmkuar.com.applibrary.domain.login.WechatUserInfoTo;
import hzxmkuar.com.applibrary.domain.tecent.QQResultTo;

/**
 * Created by Administrator on 2018/12/24.
 **/

public class LoginActivity extends BaseActivity {
    @BindView(R.id.account)
    EditText account;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.select_icon)
    View selectIcon;
    private LoginPresenter presenter;
    private Tencent mTencent;
    private IUiListener listener = new IUiListener() {
        @Override
        public void onComplete(Object o) {
            QQResultTo resultTo = new Gson().fromJson(o + "", QQResultTo.class);
            getUserInfoInThread(resultTo);
        }

        @Override
        public void onError(UiError uiError) {

        }

        @Override
        public void onCancel() {

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        StatueBarUtil.setStatueBarTextWhite(getWindow());
        ButterKnife.bind(this);
        presenter = new LoginPresenter(this);
        EventBus.getDefault().register(this);
        selectIcon.setSelected(true);

    }

    @OnClick({R.id.register, R.id.verification_login, R.id.forget_password, R.id.login, R.id.wechat_login, R.id.qq_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register:
                Intent intent = new Intent(appContext, RegisterActivity.class);
                startActivity(intent);
                goToAnimation(1);

                break;
            case R.id.verification_login:
                intent = new Intent(appContext, VerificationLoginActivity.class);
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.forget_password:
                intent = new Intent(appContext, ForgetPasswordActivity.class);
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.login:

                if (!checkPhone(account.getText().toString()))
                    return;
                if (TextUtils.isEmpty(password.getText().toString())) {
                    showMessage("请填写密码");
                    return;
                }
                if (!selectIcon.isSelected()){
                    showMessage("请同意服务协议");
                    return;
                }
                presenter.login(account.getText().toString(), password.getText().toString());

                break;
            case R.id.wechat_login:
                if (!selectIcon.isSelected()){
                    showMessage("请同意服务协议");
                    return;
                }
                wechatLogin();
                break;
            case R.id.qq_login:
                if (!selectIcon.isSelected()){
                    showMessage("请同意服务协议");
                    return;
                }
                qqLogin();
                break;
            case R.id.agree:
                intent=new Intent(appContext, WebActivity.class);
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.select_icon:
                selectIcon.setSelected(!selectIcon.isSelected());
                selectIcon.setBackgroundResource(selectIcon.isSelected()?R.drawable.login_select_agreement:R.drawable.address_un_select);
                break;
        }
    }

    public void qqLogin() {
        mTencent = Tencent.createInstance("1108049746", this.getApplicationContext());
        if (!mTencent.isSessionValid()) {
            mTencent.login(LoginActivity.this, "all", listener);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Tencent.onActivityResultData(requestCode, resultCode, data, listener);
    }

    public void getUserInfoInThread(QQResultTo resultTo) {
        new Thread() {
            @Override
            public void run() {
                JSONObject json = null;
                try {
                    json = mTencent.request("https://graph.qq.com/user/get_user_info?access_token=" + resultTo.getAccess_token() + "&oauth_consumer_key=1108049746&openid=" + resultTo.getOpenid(), null, Constants.HTTP_GET);
                } catch (IOException | HttpUtils.NetworkUnavailableException | HttpUtils.HttpStatusException | JSONException e) {
                    e.printStackTrace();
                }
                QQUserTo qqUserTo = new Gson().fromJson(json + "", QQUserTo.class);
                runOnUiThread(() -> presenter.thirdPartLogin(resultTo.getOpenid(), qqUserTo.getNickname(), qqUserTo.getFigureurl_qq_2(), "男".equals(qqUserTo.getGender()) ? 1 : 2, 1));
            }
        }.start();
    }

    @Override
    protected void submitDataSuccess(Object data) {
        WechatLoginTo loginTo = new Gson().fromJson(JSON.toJSONString(data), WechatLoginTo.class);
        Intent intent = new Intent(appContext, MainActivity.class);
        intent.putExtra("IsSplash", true);
        userInfoHelp.saveUserLogin(true);
        UserInfoTo userInfoTo = new UserInfoTo();
        userInfoTo.setUid(loginTo.getUid());
        userInfoTo.setHashid(loginTo.getHashid());
        userInfoHelp.saveUserInfo(userInfoTo);
        startActivity(intent);
        finish();
        goToAnimation(1);
    }

    @Override
    public void loadDataSuccess(Object data) {
        WechatLoginTo wechatTo = new Gson().fromJson(JSON.toJSONString(data), WechatLoginTo.class);
        if (wechatTo.getUid() == 0) {
            Intent intent = new Intent(appContext, BindPhoneActivity.class);
            intent.putExtra("OauthId", wechatTo.getOauth_id());
            startActivity(intent);
            goToAnimation(1);
        } else {
            Intent intent = new Intent(appContext, MainActivity.class);
            intent.putExtra("IsSplash", true);
            userInfoHelp.saveUserLogin(true);
            UserInfoTo userInfoTo = new UserInfoTo();
            userInfoTo.setUid(wechatTo.getUid());
            userInfoTo.setHashid(wechatTo.getHashid());
            userInfoHelp.saveUserInfo(userInfoTo);
            startActivity(intent);
            finish();
            goToAnimation(1);
        }
    }

    public void wechatLogin() {
        IWXAPI api = WXAPIFactory.createWXAPI(appContext, "wx34575f0ea7a2a608", false);
        api.registerApp("wx34575f0ea7a2a608");
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        api.sendReq(req);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void wechatLoginData(Event<WechatUserInfoTo> event) {
        if ("WechatLoginSuccess".equals(event.getType())) {

            WechatUserInfoTo wechatUserInfoTo = event.getData();
            presenter.thirdPartLogin(wechatUserInfoTo.getOpenid(), wechatUserInfoTo.getNickname(), wechatUserInfoTo.getHeadimgurl(), wechatUserInfoTo.getSex(), 2);
        }
    }

}
