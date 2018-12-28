package com.xmkj.washmall.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.makeramen.roundedimageview.RoundedImageView;
import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseFragment;
import com.xmkj.washmall.base.WashAlertDialog;
import com.xmkj.washmall.base.WebActivity;
import com.xmkj.washmall.base.util.PingFangTextView;
import com.xmkj.washmall.myself.EditUserActivity;
import com.xmkj.washmall.myself.FeedBackActivity;
import com.xmkj.washmall.myself.HelpActivity;
import com.xmkj.washmall.myself.IntegralDetailActivity;
import com.xmkj.washmall.myself.MallOrderActivity;
import com.xmkj.washmall.myself.MyCollectActivity;
import com.xmkj.washmall.myself.MyCouponActivity;
import com.xmkj.washmall.myself.VipCenterActivity;
import com.xmkj.washmall.myself.WashOrderActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import hzxmkuar.com.applibrary.domain.login.UserInfoTo;

/**
 * Created by Administrator on 2018/12/27.
 */

public class MyselfFragment extends BaseFragment {
    @BindView(R.id.user_image)
    RoundedImageView userImage;
    @BindView(R.id.user_name)
    PingFangTextView userName;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = View.inflate(appContext, R.layout.fragment_myself, null);
        unbinder = ButterKnife.bind(this, rootView);
        setView();
        return rootView;
    }

    public void setView(){
        UserInfoTo userInfoTo=new UserInfoTo();
      displayImage(userImage,userInfoTo.getUserImg());
        userName.setText(userInfoTo.getUserName());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.user_image, R.id.user_name, R.id.vip_center,R.id.collect_layout,R.id.balance_layout,R.id.coupon_layout,
    R.id.wash_order_layout,R.id.wash_order_1,R.id.wash_order_2,R.id.wash_order_3,R.id.wash_order_4,R.id.mall_order_layout,R.id.mall_order_1,R.id.mall_order_2,R.id.mall_order_3,R.id.mall_order_4,
            R.id.address,R.id.help,R.id.feed_back,R.id.custom_service,R.id.platform,R.id.about,R.id.login_out
    })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.user_image:
            case R.id.user_name:
                Intent intent=new Intent(appContext, EditUserActivity.class);
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.vip_center:
                intent=new Intent(appContext, VipCenterActivity.class);
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.balance_layout:
                intent=new Intent(appContext, IntegralDetailActivity.class);
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.collect_layout:
                intent=new Intent(appContext, MyCollectActivity.class);
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.coupon_layout:
                intent=new Intent(appContext, MyCouponActivity.class);
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.wash_order_layout:
                intent=new Intent(appContext, WashOrderActivity.class);
                intent.putExtra("Index",0);
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.wash_order_1:
                intent=new Intent(appContext, WashOrderActivity.class);
                intent.putExtra("Index",1);
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.wash_order_2:
                intent=new Intent(appContext, WashOrderActivity.class);
                intent.putExtra("Index",2);
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.wash_order_3:
                intent=new Intent(appContext, WashOrderActivity.class);
                intent.putExtra("Index",3);
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.wash_order_4:
                intent=new Intent(appContext, WashOrderActivity.class);
                intent.putExtra("Index",4);
                startActivity(intent);
                goToAnimation(1);
                break;

            case R.id.mall_order_layout:
                intent=new Intent(appContext, MallOrderActivity.class);
                intent.putExtra("Index",0);
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.mall_order_1:
                intent=new Intent(appContext, MallOrderActivity.class);
                intent.putExtra("Index",1);
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.mall_order_2:
                intent=new Intent(appContext, MallOrderActivity.class);
                intent.putExtra("Index",2);
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.mall_order_3:
                intent=new Intent(appContext, MallOrderActivity.class);
                intent.putExtra("Index",3);
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.mall_order_4:
                intent=new Intent(appContext, MallOrderActivity.class);
                intent.putExtra("Index",4);
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.address:
                break;
            case R.id.help:
                intent=new Intent(appContext, HelpActivity.class);
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.feed_back:
                intent=new Intent(appContext, FeedBackActivity.class);
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.custom_service:
                break;
            case R.id.platform:
                WashAlertDialog.show(getActivity(),"联系热线","请联系我们，客服电话\n" + "1876810525").setOnClickListener(v -> {
                    WashAlertDialog.dismiss();

                });
                break;
            case R.id.about:
                intent=new Intent(appContext, WebActivity.class);
                intent.putExtra("Type",1);
                intent.putExtra("Title","关于我们");
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.login_out:
                WashAlertDialog.show(getActivity(),"退出登录","是否退出当前账号").setOnClickListener(v -> {
                    WashAlertDialog.dismiss();

                });
                break;

        }
    }
}
