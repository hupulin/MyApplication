package com.xmkj.washmall.main.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.makeramen.roundedimageview.RoundedImageView;
import com.ruffian.library.RTextView;
import com.tencent.connect.share.QQShare;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.tauth.Tencent;
import com.xmkj.washmall.MainApp;
import com.xmkj.washmall.R;
import com.xmkj.washmall.base.ActivityManager;
import com.xmkj.washmall.base.BaseFragment;
import com.xmkj.washmall.base.CommonDialog;
import com.xmkj.washmall.base.WashAlertDialog;
import com.xmkj.washmall.base.WebActivity;
import com.xmkj.washmall.base.util.AppUtil;
import com.xmkj.washmall.base.util.PingFangTextView;
import com.xmkj.washmall.integral.IntegralDetailActivity;
import com.xmkj.washmall.login.LoginActivity;
import com.xmkj.washmall.main.presenter.MyselfPresenter;
import com.xmkj.washmall.mall.AddressActivity;
import com.xmkj.washmall.message.ChatActivity;
import com.xmkj.washmall.myself.EditUserActivity;
import com.xmkj.washmall.myself.ExchangeActivity;
import com.xmkj.washmall.myself.FeedBackActivity;
import com.xmkj.washmall.myself.HelpActivity;
import com.xmkj.washmall.myself.MallOrderActivity;
import com.xmkj.washmall.myself.MyCollectActivity;
import com.xmkj.washmall.myself.MyCouponActivity;
import com.xmkj.washmall.myself.VipCenterActivity;
import com.xmkj.washmall.myself.WashOrderActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import hzxmkuar.com.applibrary.domain.user.MyselfUserTo;
import hzxmkuar.com.applibrary.impl.PermissionListener;
import rx.Observable;

/**
 * Created by Administrator on 2018/12/27.
 */

public class MyselfFragment extends BaseFragment {
    @BindView(R.id.user_image)
    RoundedImageView userImage;
    @BindView(R.id.user_name)
    PingFangTextView userName;

    @BindView(R.id.user_tag)
    TextView userTag;
    @BindView(R.id.my_collect_num)
    PingFangTextView myCollectNum;
    @BindView(R.id.my_score_num)
    PingFangTextView myScoreNum;
    @BindView(R.id.my_coupon_num)
    PingFangTextView myCouponNum;
    @BindView(R.id.my_balance_num)
    PingFangTextView myBalanceNum;
    Unbinder unbinder;
    @BindView(R.id.vip_center)
    RTextView vipCenter;
    private MyselfUserTo mode;
    private CommonDialog dialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = View.inflate(appContext, R.layout.fragment_myself, null);
        unbinder = ButterKnife.bind(this, rootView);
        setView();

        MyselfPresenter presenter = new MyselfPresenter(this);
        return rootView;
    }

    public void setView() {


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.user_image, R.id.user_name, R.id.vip_center, R.id.collect_layout, R.id.balance_layout, R.id.coupon_layout,
            R.id.wash_order_layout, R.id.wash_order_1, R.id.wash_order_2, R.id.wash_order_3, R.id.wash_order_4, R.id.mall_order_layout, R.id.mall_order_1, R.id.mall_order_2, R.id.mall_order_3, R.id.mall_order_4,
            R.id.address, R.id.help, R.id.feed_back, R.id.custom_service, R.id.platform, R.id.about, R.id.login_out, R.id.exchange_layout,R.id.share_layout
    })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.user_image:
            case R.id.user_name:
                Intent intent = new Intent(appContext, EditUserActivity.class);
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.vip_center:
                intent = new Intent(appContext, VipCenterActivity.class);
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.balance_layout:
                intent = new Intent(appContext, IntegralDetailActivity.class);
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.collect_layout:
                intent = new Intent(appContext, MyCollectActivity.class);
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.coupon_layout:
                intent = new Intent(appContext, MyCouponActivity.class);
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.wash_order_layout:
                intent = new Intent(appContext, WashOrderActivity.class);
                intent.putExtra("Index", 0);
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.wash_order_1:
                intent = new Intent(appContext, WashOrderActivity.class);
                intent.putExtra("Index", 1);
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.wash_order_2:
                intent = new Intent(appContext, WashOrderActivity.class);
                intent.putExtra("Index", 2);
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.wash_order_3:
                intent = new Intent(appContext, WashOrderActivity.class);
                intent.putExtra("Index", 3);
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.wash_order_4:
                intent = new Intent(appContext, WashOrderActivity.class);
                intent.putExtra("Index", 4);
                startActivity(intent);
                goToAnimation(1);
                break;

            case R.id.mall_order_layout:
                intent = new Intent(appContext, MallOrderActivity.class);
                intent.putExtra("Index", 0);
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.mall_order_1:
                intent = new Intent(appContext, MallOrderActivity.class);
                intent.putExtra("Index", 1);
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.mall_order_2:
                intent = new Intent(appContext, MallOrderActivity.class);
                intent.putExtra("Index", 2);
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.mall_order_3:
                intent = new Intent(appContext, MallOrderActivity.class);
                intent.putExtra("Index", 3);
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.mall_order_4:
                intent = new Intent(appContext, MallOrderActivity.class);
                intent.putExtra("Index", 4);
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.address:
                intent = new Intent(appContext, AddressActivity.class);
                intent.putExtra("Index", 4);
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.help:
                intent = new Intent(appContext, HelpActivity.class);
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.feed_back:
                intent = new Intent(appContext, FeedBackActivity.class);
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.custom_service:
                intent = new Intent(appContext, ChatActivity.class);
                intent.putExtra("Phone",mode.getMore_service().getKf_tel());
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.platform:
                setCall();
                break;
            case R.id.about:
                intent = new Intent(appContext, WebActivity.class);
                intent.putExtra("Type", 1);
                intent.putExtra("Title", "关于我们");
                intent.putExtra("Url", MainApp.webBaseUrl+"2");
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.login_out:
                WashAlertDialog.show(getActivity(), "退出登录", "是否退出当前账号").setOnClickListener(v -> {
                    WashAlertDialog.dismiss();
                    userInfoHelp.saveUserInfo(null);
                    userInfoHelp.saveUserLogin(false);
                    Observable.from(ActivityManager.activityList).subscribe(Activity::finish);
                    Intent intent1 = new Intent(appContext, LoginActivity.class);
                    startActivity(intent1);
                    goToAnimation(2);


                });
                break;
            case R.id.exchange_layout:
                intent = new Intent(appContext, ExchangeActivity.class);
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.share_layout:
                showShare();
                break;

        }
    }

    private void chatRegister(String phone) {

        new Thread(() -> {
            try {
                EMClient.getInstance().createAccount(phone, "123456");
            } catch (HyphenateException e) {
                e.printStackTrace();
            }
        }).start();

    }

    @Override
    public void loadDataSuccess(Object data) {

        mode = (MyselfUserTo) data;
        userInfoTo=userInfoHelp.getUserInfo();

        chatRegister(mode.getMore_service().getKf_tel());
        userName.setText(mode.getUser_info().getUsername());
        userInfoTo = userInfoHelp.getUserInfo();
        userInfoTo.setMyselfTo(mode);
        userInfoHelp.saveUserInfo(userInfoTo);
        disPlayRoundImage(userImage, mode.getUser_info().getFace_url());
        userTag.setText(mode.getUser_info().getUser_tag());
        myBalanceNum.setText(mode.getUser_info().getAccount());
        myCollectNum.setText(mode.getUser_info().getCollection_num());
        vipCenter.setVisibility(mode.getUser_info().getMember_level()!=0?View.VISIBLE:View.GONE);
//        vipCenter.setText(mode.getUser_info().getUser_tag());

        myScoreNum.setText(mode.getUser_info().getScore().contains(".") ? mode.getUser_info().getScore().split(".")[0] : mode.getUser_info().getScore());
        myCouponNum.setText(mode.getUser_info().getCoupon_num());
    }

    public void setCall() {
        WashAlertDialog.show(getActivity(), "联系热线", "请联系我们，客服电话\n" + userInfoTo.getMyselfTo().getMore_service().getKf_tel()).setOnClickListener(v -> {
            WashAlertDialog.dismiss();
            if (!AppUtil.readSIMCard(appContext, getActivity())) return;
            getPermission(Manifest.permission.CALL_PHONE, new PermissionListener() {
                @Override
                public void accept(String permission) {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    Uri data = Uri.parse("tel:" + userInfoTo.getMyselfTo().getMore_service().getKf_tel());
                    intent.setData(data);
                    startActivity(intent);

                }

                @Override
                public void refuse(String permission) {

                }
            });
        });
    }

    public void showShare() {

        dialog = new CommonDialog(getActivity(), getScreenWidth(), (int) (getScreenWidth() * 180.0 / 750), R.layout.dialog_share_moment, R.style.DialogDown);
        dialog.show();
        dialog.findViewById(R.id.wechat_layout).setOnClickListener(view1 -> {
            wChatShare();
            dialog.dismiss();
        });
        dialog.findViewById(R.id.qq_layout).setOnClickListener(view1 -> {
            qqShare();
            dialog.dismiss();
        });
        dialog.findViewById(R.id.moment_layout).setOnClickListener(view1 -> {
            momentShare();

            dialog.dismiss();
        });
        dialog.findViewById(R.id.parent).setOnClickListener(view -> dialog.dismiss());

    }

    public void qqShare() {

        Tencent  mTencent = Tencent.createInstance("1108049746", appContext);
        final Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);//分享的类型
        params.putString(QQShare.SHARE_TO_QQ_TITLE, "我俫洗");//分享标题
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY,"邀请好友有积分奖励哦");//要分享的内容摘要
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL,"http://xmap18100040.h5.hzxmnet.com/h5/downloadPage/index.html?invite_code="+userInfoTo.getMyselfTo().getUser_info().getInvite_code());//内容地址
        params.putInt(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL,R.drawable.ic_launcher);//分享的图片URL
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "我俫洗");//应用名称
        mTencent.shareToQQ(getActivity(), params,null);


    }

    public void wChatShare() {
        IWXAPI wxapi = WXAPIFactory.createWXAPI(appContext, "wx34575f0ea7a2a608");
        if (!wxapi.isWXAppInstalled()) {
          showMessage("您还没有安装微信");
            return;
        }
        WXWebpageObject webpageObject = new WXWebpageObject();
        webpageObject.webpageUrl = "http://xmap18100040.h5.hzxmnet.com/h5/downloadPage/index.html?invite_code="+userInfoTo.getMyselfTo().getUser_info().getInvite_code();;
        WXMediaMessage msg = new WXMediaMessage(webpageObject);
        msg.title = "我俫洗";
        msg.description = "邀请好友有积分奖励哦";
        Bitmap bitmap = ((BitmapDrawable)getResources().getDrawable(R.drawable.ic_launcher)).getBitmap();
        msg.setThumbImage(bitmap);
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = "webpage";
        req.message = msg;
        req.scene = SendMessageToWX.Req.WXSceneSession;
        wxapi.sendReq(req);


    }


    public void momentShare() {
        IWXAPI wxapi = WXAPIFactory.createWXAPI(appContext, "wx34575f0ea7a2a608");
        if (!wxapi.isWXAppInstalled()) {
            showMessage("您还没有安装微信");
            return;
        }
        WXWebpageObject webpageObject = new WXWebpageObject();
        webpageObject.webpageUrl = "http://xmap18100040.h5.hzxmnet.com/h5/downloadPage/index.html?invite_code="+userInfoTo.getMyselfTo().getUser_info().getInvite_code();
        WXMediaMessage msg = new WXMediaMessage(webpageObject);
        msg.title = "我俫洗";
        msg.description = "邀请好友有积分奖励哦";
        Bitmap bitmap = ((BitmapDrawable)getResources().getDrawable(R.drawable.ic_launcher)).getBitmap();
        msg.setThumbImage(bitmap);
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = "webpage";
        req.message = msg;
        req.scene = SendMessageToWX.Req.WXSceneTimeline;
        wxapi.sendReq(req);
    }
}
