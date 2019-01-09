package com.xmkj.washmall.wash.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseFragment;
import com.xmkj.washmall.base.util.DoubleUtil;
import com.xmkj.washmall.databinding.SelectWashAddItemBinding;
import com.xmkj.washmall.databinding.SelectWashTypeItemBinding;
import com.xmkj.washmall.wash.PlaceOrderActivity;
import com.xmkj.washmall.wash.SelectWashActivity;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import hzxmkuar.com.applibrary.domain.wash.WashInfoTo;
import hzxmkuar.com.applibrary.domain.wash.WashJsonDetailTo;
import rx.Observable;

/**
 * Created by Administrator on 2018/12/25.
 **/

@SuppressLint("ValidFragment")
public class SelectWashFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.tag_layout)
    TagFlowLayout tagLayout;
    @BindView(R.id.type_layout)
    GridLayout typeLayout;
    @BindView(R.id.select_layout)
    GridLayout selectLayout;
    @BindView(R.id.money)
    TextView money;

    private boolean isViewCreate;
    private boolean isUiVisible;
    private double allMoney = 0;
    private List<WashJsonDetailTo>jsonDetailList=new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = View.inflate(appContext, R.layout.fragment_select_wash, null);

        unbinder = ButterKnife.bind(this, rootView);
        isViewCreate = true;

        loadData();

        return rootView;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            isUiVisible = true;
            loadData();
        } else {
            isUiVisible = false;
        }
        super.setUserVisibleHint(isVisibleToUser);

    }

    public void loadData() {
        if (isViewCreate && isUiVisible) {

            isUiVisible = false;
            isViewCreate = false;
            assert getActivity() != null;
            List<WashInfoTo> washTypeTo = ((SelectWashActivity) getActivity()).washItemMap.get(((SelectWashActivity) getActivity()).typeList.get(FragmentPagerItem.getPosition(getArguments())));
            setFlowLayout(washTypeTo);
            setWash(washTypeTo.get(0).getList3());
        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void setFlowLayout(List<WashInfoTo> tagList) {
        List<TextView> textViewList = new ArrayList<>();
        tagLayout.setMaxSelectCount(1);
        tagLayout.setAdapter(new TagAdapter<WashInfoTo>(tagList) {
            @Override
            public View getView(FlowLayout parent, int position, WashInfoTo info) {
                View mView = View.inflate(appContext, R.layout.select_wash_tag_item, null);
                TextView tagName = mView.findViewById(R.id.name);
                textViewList.add(tagName);
                tagName.setText(info.getService_name());
                return mView;
            }
        });

        tagLayout.setOnTagClickListener((view, position, parent) -> {
            Observable.from(textViewList).subscribe(textView -> {
                textView.setBackgroundResource(R.drawable.wash_type_tag_un_select);
                textView.setTextColor(Color.parseColor("#8273EF"));
            });
            ((TextView) view.findViewById(R.id.name)).setTextColor(Color.parseColor("#ffffff"));
            view.findViewById(R.id.name).setBackgroundResource(R.drawable.wash_type_tag_select);
            setWash(tagList.get(position).getList3());
            return true;
        });

        textViewList.get(0).setTextColor(Color.parseColor("#ffffff"));
        textViewList.get(0).setBackgroundResource(R.drawable.wash_type_tag_select);
    }

    @SuppressLint("SetTextI18n")
    public void setWash(List<WashInfoTo> washList) {
        typeLayout.removeAllViews();
        for (int i = 0; i < washList.size(); i++) {
            WashInfoTo washTo = washList.get(i);
            View mView = View.inflate(appContext, R.layout.select_wash_type_item, null);
            SelectWashTypeItemBinding bind = DataBindingUtil.bind(mView);
            bind.typeName.setText(washTo.getService_name());
            displayImage(bind.washImage, washTo.getService_img());
            typeLayout.addView(mView);
            bind.add.setOnClickListener(v -> {
                bind.num.setText(Integer.valueOf(bind.num.getText().toString()) + 1 + "");
                washTo.setNum(Integer.valueOf(bind.num.getText().toString()));
                setSelectWash(washList);
            });
            bind.reduce.setOnClickListener(v -> {
                if (Integer.valueOf(bind.num.getText().toString()) > 0) {
                    bind.num.setText(Integer.valueOf(bind.num.getText().toString()) - 1 + "");
                    washTo.setNum(Integer.valueOf(bind.num.getText().toString()));
                    setSelectWash(washList);
                }
            });

        }
    }

    @SuppressLint("SetTextI18n")
    private void setSelectWash(List<WashInfoTo> washList) {
        selectLayout.removeAllViews();
        jsonDetailList.clear();
        for (int i = 0; i < washList.size(); i++) {
            WashInfoTo washTo = washList.get(i);
            if (washTo.getNum() > 0) {
                WashJsonDetailTo detailTo=new WashJsonDetailTo();
                detailTo.setSid(washTo.getId());
                detailTo.setNum(washTo.getNum());
                View mView = View.inflate(appContext, R.layout.select_wash_add_item, null);
                SelectWashAddItemBinding bind = DataBindingUtil.bind(mView);
                bind.name.setText(washTo.getService_name());
                bind.num.setText(washTo.getNum() + "件");
                bind.aromaMoney.setText(washTo.getSweet_price());
                allMoney = DoubleUtil.add(allMoney, DoubleUtil.mul(washTo.getPrice(), washTo.getNum()));
                money.setText("￥" + allMoney);
                selectLayout.addView(mView);
                bind.needAroma.setOnClickListener(v -> {

                    bind.needAroma.setBackgroundResource(R.drawable.wash_need_select);
                    bind.unNeedAroma.setBackgroundResource(R.drawable.wash_un_need_un_select);
                    if (!bind.needAroma.isSelected())
                        allMoney = DoubleUtil.add(allMoney, Double.valueOf(washTo.getSweet_price()));
                    money.setText("￥" + allMoney);
                    bind.needAroma.setSelected(true);
                    detailTo.setIs_sweet(1);
                });

                bind.unNeedAroma.setOnClickListener(v -> {

                    bind.needAroma.setBackgroundResource(R.drawable.wash_need_un_select);
                    bind.unNeedAroma.setBackgroundResource(R.drawable.wash_un_need_select);
                    if (bind.needAroma.isSelected())
                        allMoney = DoubleUtil.reduce(allMoney, Double.valueOf(washTo.getSweet_price()));
                    money.setText("￥" + allMoney);
                    bind.needAroma.setSelected(false);
                    detailTo.setIs_sweet(0);
                });
                jsonDetailList.add(detailTo);
            }
        }
    }

    @OnClick(R.id.place_order)
    public void onViewClicked() {
        if (selectLayout.getChildCount()==0){
            showMessage("请选择下单种类");
            return;
        }

        Intent intent=new Intent(appContext, PlaceOrderActivity.class);
        intent.putExtra("WashJson", JSON.toJSONString(jsonDetailList));
        startActivity(intent);
        goToAnimation(1);
    }
}
