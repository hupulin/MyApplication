package com.xmkj.washmall.main.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.google.gson.Gson;
import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseFragment;
import com.xmkj.washmall.base.WashAlertDialog;
import com.xmkj.washmall.base.util.DoubleUtil;
import com.xmkj.washmall.base.util.PingFangTextView;
import com.xmkj.washmall.car.adapter.CarAdapter;
import com.xmkj.washmall.car.presenter.CarPresenter;
import com.xmkj.washmall.mall.ConfirmOrderActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import hzxmkuar.com.applibrary.domain.car.GoodsCarTo;
import hzxmkuar.com.applibrary.domain.mall.SettlementIdTo;
import rx.Observable;

/**
 * Created by Administrator on 2018/12/27.
 **/

public class CarFragment extends BaseFragment {
    @BindView(R.id.recycler_view)
    LRecyclerView recyclerView;
    Unbinder unbinder;
    @BindView(R.id.edit)
    TextView edit;
    @BindView(R.id.all_select_icon)
    View allSelectIcon;
    @BindView(R.id.all_select_text)
    TextView allSelectText;
    @BindView(R.id.all_text)
    TextView allText;
    @BindView(R.id.all_money)
    PingFangTextView allMoney;
    @BindView(R.id.settlement)
    PingFangTextView settlement;
    private CarAdapter adapter;
    private CarPresenter presenter;
    private String selectCarId="";
    private double selectMoeny;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = View.inflate(appContext, R.layout.fragment_car, null);
        unbinder = ButterKnife.bind(this, mView);
        adapter = new CarAdapter(getActivity());
        presenter = new CarPresenter(this);
        setRecycleView(adapter, recyclerView, presenter);
        setAdapter();
        return mView;
    }

    private void setAdapter() {
        adapter.setCarModifyListener(new CarAdapter.CarModifyListener() {
            @Override
            public void modifyNum(GoodsCarTo mode, int goodsNum) {
                presenter.modifyNum(mode.getCart_id(), goodsNum);
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void selectClick(GoodsCarTo mode) {
              setMoeny();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.edit, R.id.settlement, R.id.all_select_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edit:
                edit.setSelected(!edit.isSelected());
                edit.setText(edit.isSelected()?"完成":"编辑");
                settlement.setText(edit.isSelected()?"删除":"结算");
                allText.setVisibility(edit.isSelected()?View.GONE:View.VISIBLE);
                allMoney.setVisibility(edit.isSelected()?View.GONE:View.VISIBLE);
                for (int i=0;i<presenter.goodsList.size();i++)
                    presenter.goodsList.get(i).setSelect(false);
                allSelectIcon.setSelected(false);
                allSelectIcon.setBackgroundResource(R.drawable.address_un_select);
                allSelectText.setText("全选");
                adapter.notifyDataSetChanged();
                setMoeny();
                break;
            case R.id.settlement:
                selectCarId="";
               Observable.from(presenter.goodsList).filter(GoodsCarTo::isSelect).subscribe(goodsCarTo -> selectCarId=selectCarId+goodsCarTo.getCart_id()+",");
                if (selectCarId.length()==0){
                    showMessage("请选择商品");
                    return;
                }
                selectCarId=selectCarId.substring(0,selectCarId.length()-1);
                if (edit.isSelected()) {
                    WashAlertDialog.show(getActivity(),"提示","是否删除").setOnClickListener(v -> {
                        WashAlertDialog.dismiss();
                        presenter.deleteGoods(selectCarId);
                    });
                }
                else
                    presenter.settlementCar(selectCarId);
                break;
            case R.id.all_select_layout:
                allSelectIcon.setSelected(!allSelectIcon.isSelected());
                allSelectIcon.setBackgroundResource(allSelectIcon.isSelected()?R.drawable.address_select:R.drawable.address_un_select);
                allSelectText.setText(allSelectIcon.isSelected()?"取消全选":"全选");
                for (int i=0;i<presenter.goodsList.size();i++)
                 presenter.goodsList.get(i).setSelect(allSelectIcon.isSelected());
                adapter.notifyDataSetChanged();
                setMoeny();
                break;
        }
    }

    @Override
    protected void submitDataSuccess(Object data) {

        SettlementIdTo settlementIdTo=new Gson().fromJson(JSON.toJSONString(data),SettlementIdTo.class);
        Intent intent=new Intent(appContext,ConfirmOrderActivity.class);
        intent.putExtra("SettlementId",settlementIdTo.getSettlement_ids());
        startActivity(intent);
        goToAnimation(1);

    }

    @SuppressLint("SetTextI18n")
    private void setMoeny(){
        selectMoeny=0;
        Observable.from(presenter.goodsList).filter(GoodsCarTo::isSelect).subscribe(goodsCarTo -> {
            selectMoeny= DoubleUtil.add(selectMoeny,DoubleUtil.mul(Double.valueOf(goodsCarTo.getGoods_price()),goodsCarTo.getGoods_num()));
        });
        allMoney.setText("￥"+selectMoeny);
    }
}
