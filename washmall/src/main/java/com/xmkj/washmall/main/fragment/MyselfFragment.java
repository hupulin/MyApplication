package com.xmkj.washmall.main.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.makeramen.roundedimageview.RoundedImageView;
import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseFragment;
import com.xmkj.washmall.base.util.PingFangTextView;

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

    @OnClick({R.id.user_image, R.id.user_name, R.id.vip_center})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.user_image:
            case R.id.user_name:
                break;
            case R.id.vip_center:
                break;
        }
    }
}
