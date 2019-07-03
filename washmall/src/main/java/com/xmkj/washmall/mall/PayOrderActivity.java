package com.xmkj.washmall.mall;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/12/27.
 */

public class PayOrderActivity extends BaseActivity {
    @BindView(R.id.balance_icon)
    View balanceIcon;
    @BindView(R.id.ali_icon)
    View aliIcon;
    @BindView(R.id.wechat_icon)
    View wechatIcon;
    @BindView(R.id.coupon_name)
    TextView couponName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_order);
        ButterKnife.bind(this);
        setTitleName("支付");
    }

    @OnClick({R.id.balance_layout, R.id.ali_layout, R.id.wechat_layout, R.id.confirm,R.id.select_coupon_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.balance_layout:
                balanceIcon.setBackgroundResource(R.drawable.address_select);
                aliIcon.setBackgroundResource(R.drawable.address_un_select);
                wechatIcon.setBackgroundResource(R.drawable.address_un_select);
                break;
            case R.id.ali_layout:
                balanceIcon.setBackgroundResource(R.drawable.address_un_select);
                aliIcon.setBackgroundResource(R.drawable.address_select);
                wechatIcon.setBackgroundResource(R.drawable.address_un_select);
                break;
            case R.id.wechat_layout:
                balanceIcon.setBackgroundResource(R.drawable.address_un_select);
                aliIcon.setBackgroundResource(R.drawable.address_un_select);
                wechatIcon.setBackgroundResource(R.drawable.address_select);
                break;
            case R.id.confirm:
                payBalanceDialog();
                break;
            case R.id.select_coupon_layout:
                Intent intent=new Intent(appContext,SelectCouponActivity.class);
                intent.putExtra("UseType",1);
//                intent.putExtra("OrderId",)
                startActivityForResult(intent,20);
                goToAnimation(1);
                break;
        }
    }

    private void payBalanceDialog() {
        NiftyDialogBuilder dialog = NiftyDialogBuilder.getInstance(this);
        dialog.setContentView(R.layout.dialog_select_pay_balance);
        dialog.findViewById(R.id.cancel).setOnClickListener(v -> dialog.dismiss());
        dialog.findViewById(R.id.parent).setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==20){
            couponName.setText(data.getStringExtra("Discount"));
        }
    }
}
