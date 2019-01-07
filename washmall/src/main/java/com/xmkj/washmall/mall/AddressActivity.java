package com.xmkj.washmall.mall;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.WashAlertDialog;
import com.xmkj.washmall.mall.adapter.AddressAdapter;
import com.xmkj.washmall.mall.presenter.AddressPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hzxmkuar.com.applibrary.domain.order.AddressTo;

/**
 * Created by Administrator on 2018/12/27.
 */

public class AddressActivity extends BaseActivity {
    @BindView(R.id.recycler_view)
    LRecyclerView recyclerView;
    private AddressPresenter presenter;
    private AddressAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        ButterKnife.bind(this);
        setTitleName("收货地址管理");
        presenter = new AddressPresenter(this);
        adapter = new AddressAdapter(this);
        setRecycleView(adapter, recyclerView, presenter);
        setAdapter();
    }

    private void setAdapter() {
        adapter.setAddressListener(new AddressAdapter.AddressClickListener() {
            @Override
            public void deleteAddress(AddressTo mode) {
                WashAlertDialog.show(AddressActivity.this,"提示","确定删除地址").setOnClickListener(v -> {
                    WashAlertDialog.dismiss();
                    presenter.deleteAddress(mode.getId());
                });
            }

            @Override
            public void editAddress(AddressTo mode) {
             Intent intent=new Intent(appContext,AddAddressActivity.class);
                intent.putExtra("AddressTo",mode);
                startActivity(intent);
                goToAnimation(1);

            }

            @Override
            public void setDefaultAddress(AddressTo mode) {
                if (mode.getIs_default()==1)
                    return;
                WashAlertDialog.show(AddressActivity.this,"提示","设置为默认地址").setOnClickListener(v -> {
                    WashAlertDialog.dismiss();
                    presenter.setDefaultAddress(mode.getId());
                });
            }
        });
    }



    @OnClick(R.id.confirm)
    public void onViewClicked() {
        Intent intent=new Intent(appContext,AddAddressActivity.class);
        startActivity(intent);
        goToAnimation(1);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        presenter.getAddressList();

    }

    @Override
    public void recycleItemClick(View view, int position, Object data) {
       AddressTo addressTo= (AddressTo) data;
        Intent intent=new Intent();
        intent.putExtra("AddressId",addressTo.getId());
        setResult(10,intent);
        finish();
        goToAnimation(2);

    }
}
