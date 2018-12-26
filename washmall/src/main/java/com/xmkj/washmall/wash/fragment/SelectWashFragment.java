package com.xmkj.washmall.wash.fragment;

import android.annotation.SuppressLint;
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

import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseFragment;
import com.xmkj.washmall.databinding.SelectWashAddItemBinding;
import com.xmkj.washmall.databinding.SelectWashTypeItemBinding;
import com.xmkj.washmall.wash.SelectWashActivity;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import hzxmkuar.com.applibrary.domain.wash.WashItemTo;
import hzxmkuar.com.applibrary.domain.wash.WashTypeTo;
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

    private boolean isViewCreate;
    private boolean isUiVisible;
    private List<WashItemTo> washList;


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
            WashTypeTo washTypeTo = ((SelectWashActivity) getActivity()).typeList.get(FragmentPagerItem.getPosition(getArguments()));
            setFlowLayout(washTypeTo.getTag());
            setWash();
        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void setFlowLayout(List<String> tagList) {
        List<TextView> textViewList = new ArrayList<>();
        tagLayout.setMaxSelectCount(1);
        tagLayout.setAdapter(new TagAdapter<String>(tagList) {
            @Override
            public View getView(FlowLayout parent, int position, String name) {
                View mView = View.inflate(appContext, R.layout.select_wash_tag_item, null);
                TextView tagName = mView.findViewById(R.id.name);
                textViewList.add(tagName);
                tagName.setText(name);
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
            return true;
        });
    }

    public void setWash() {
        washList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            WashItemTo washTo = new WashItemTo();
            washList.add(washTo);
        }
        for (int i = 0; i < washList.size(); i++) {
            WashItemTo washTo = washList.get(i);
            View mView = View.inflate(appContext, R.layout.select_wash_type_item, null);
            SelectWashTypeItemBinding bind = DataBindingUtil.bind(mView);
            bind.typeName.setText(washTo.getName());
            displayImage(bind.washImage, washTo.getImageUrl());
            typeLayout.addView(mView);

            mView.setOnClickListener(v -> {
                washTo.setSelect(!washTo.isSelect());
                setSelectWash();
            });

        }
    }

    private void setSelectWash(){
        selectLayout.removeAllViews();
        for (int i = 0; i < washList.size(); i++) {
            WashItemTo washTo = washList.get(i);
            if (washTo.isSelect()) {
                View mView = View.inflate(appContext, R.layout.select_wash_add_item, null);
                SelectWashAddItemBinding bind = DataBindingUtil.bind(mView);
                bind.name.setText(washTo.getName());
                bind.num.setText(washTo.getNum() + "");
                bind.aromaMoney.setText(washTo.getAroma_money());
                selectLayout.addView(mView);
            }
        }
    }
}
