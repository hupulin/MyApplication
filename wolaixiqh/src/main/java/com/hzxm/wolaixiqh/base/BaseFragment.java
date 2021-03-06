package com.hzxm.wolaixiqh.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;

import com.hzxm.wolaixiqh.MainApp;
import com.hzxm.wolaixiqh.R;
import com.hzxm.wolaixiqh.base.adapter.BaseAdapter;
import com.hzxm.wolaixiqh.base.util.GlideCircleTransform;
import com.hzxm.wolaixiqh.base.view.RecycleViewHeadView;
import com.hzxm.wolaixiqh.main.ScanDecodeActivity;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.List;

import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.delivery.main.UserInfoTo;
import hzxmkuar.com.applibrary.impl.PermissionListener;

/**
 * Created by xzz on 2018/8/15.
 */

public class BaseFragment<T> extends Fragment {
    protected Context appContext= MainApp.appContext;
    public LRecyclerView mRecycleView;
    public BaseAdapter baseAdapter;
    protected UserInfoHelp userInfoHelp=new UserInfoHelp();
    protected UserInfoTo userInfoTo;
    protected View headView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        userInfoTo=userInfoHelp.getUserInfo();
        return super.onCreateView(inflater, container, savedInstanceState);
    }




    public void goToAnimation(int type) {

        switch (type) {
            case 1:
                // 跳转到下一个界面
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case 2:
                // 按返回
                getActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;

            case 3:
               getActivity(). overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                break;
        }
    }

    protected void showMessage(String message){
        Toast.makeText(appContext,message,Toast.LENGTH_LONG).show();
    }


    protected void disPlayRoundImage(ImageView imageView) {

        Glide.with(appContext).load(R.drawable.post_image_default).transform(new GlideCircleTransform(appContext)).into(imageView);



    }
    protected void disPlayRoundImage(ImageView imageView,String url) {

        Glide.with(appContext).load(url).placeholder(R.mipmap.user_default_icon).transform(new GlideCircleTransform(appContext)).into(imageView);



    }
    public void loadDataSuccess(T data) {

    }

    public void loadDataSuccess(MessageTo data) {

    }

    public void loadDataSuccess(List data, int total) {

    }

    public void loadDataSuccess(List<T> data) {

    }

    protected void submitDataSuccess(T data) {

    }

    protected void displayImage(ImageView imageView,String url) {

        Glide.with(appContext).load(url).transform(new GlideCircleTransform(appContext)).into(imageView);



    }
    protected void displayImage(ImageView imageView,String url,int icon) {

        Glide.with(appContext).load(url).placeholder(icon).into(imageView);



    }

    protected void setRecycleView(BaseAdapter adapter, LRecyclerView recycleView, BasePresenter presenter) {
        this.baseAdapter = adapter;
        this.mRecycleView = recycleView;
        recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleView.setRefreshHeader(new RecycleViewHeadView(appContext));
        LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
       if (headView!=null)
           lRecyclerViewAdapter.addHeaderView(headView);
        recycleView.setAdapter(lRecyclerViewAdapter);
        recycleView.setPullRefreshEnabled(true);
        recycleView.setLoadMoreEnabled(true);
        recycleView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recycleView.setFooterViewColor(R.color.app_color, R.color.app_color, R.color.transparent);
        recycleView.setOnLoadMoreListener(() -> {

            presenter.recycleViewLoadMore();
        });
        recycleView.setOnRefreshListener(presenter::recycleViewRefresh);
        lRecyclerViewAdapter.setOnItemClickListener(presenter::recycleItemClick);
//        recycleView.forceToRefresh();


    }
    public class CustomLinearLayoutManager extends LinearLayoutManager {
        private boolean isScrollEnabled = true;

        public CustomLinearLayoutManager(Context context) {
            super(context);
        }
        public void setScrollEnabled(boolean flag) {
            this.isScrollEnabled = flag;
        }

        @Override
        public boolean canScrollVertically() {
            //Similarly you can customize "canScrollHorizontally()" for managing horizontal scroll
//            return isScrollEnabled && super.canScrollVertically();
            return  false;
        }

    }

    protected void setRecycleViewNoScroll(BaseAdapter adapter, LRecyclerView recycleView,BasePresenter presenter) {
        this.baseAdapter = adapter;
        this.mRecycleView = recycleView;
        recycleView.setLayoutManager(new CustomLinearLayoutManager(getActivity()));
        recycleView.setRefreshHeader(new RecycleViewHeadView(appContext));
        LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
       if (headView!=null)
           lRecyclerViewAdapter.addHeaderView(headView);
        recycleView.setAdapter(lRecyclerViewAdapter);
        recycleView.setPullRefreshEnabled(false);
        recycleView.setLoadMoreEnabled(false);
        recycleView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recycleView.setFooterViewColor(R.color.appColor, R.color.appColor, R.color.transparent);
        recycleView.setLoadMoreEnabled(false);
        recycleView.setOnLoadMoreListener(presenter::recycleViewLoadMore);
        recycleView.setOnRefreshListener(presenter::recycleViewRefresh);
        lRecyclerViewAdapter.setOnItemClickListener((View view, int position) -> {
//            startActivity(new Intent(appContext, ScanDecodeActivity.class));
        });
//        recycleView.forceToRefresh();


    }
    protected void setRecycleView(BaseAdapter adapter, LRecyclerView recycleView) {
        this.baseAdapter = adapter;
        this.mRecycleView = recycleView;
        recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleView.setRefreshHeader(new RecycleViewHeadView(appContext));
        LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
       if (headView!=null)
           lRecyclerViewAdapter.addHeaderView(headView);
        recycleView.setAdapter(lRecyclerViewAdapter);
        recycleView.setPullRefreshEnabled(false);
        recycleView.setLoadMoreEnabled(false);
        recycleView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recycleView.setFooterViewColor(R.color.appColor, R.color.appColor, R.color.transparent);
        lRecyclerViewAdapter.setOnItemClickListener((View view, int position) -> {
            startActivity(new Intent(appContext, ScanDecodeActivity.class));
        });
//        recycleView.forceToRefresh();


    }
    protected void setRecycleView(BaseAdapter adapter, LRecyclerView recycleView, BasePresenter presenter,Boolean refresh) {
        this.baseAdapter = adapter;
        this.mRecycleView = recycleView;
        recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleView.setRefreshHeader(new RecycleViewHeadView(appContext));
        LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);

        if (headView!=null)
            lRecyclerViewAdapter.addHeaderView(headView);
        recycleView.setAdapter(lRecyclerViewAdapter);
        recycleView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recycleView.setFooterViewColor(R.color.appColor, R.color.appColor, R.color.transparent);
        recycleView.setLoadMoreEnabled(refresh);
        recycleView.setOnLoadMoreListener(presenter::recycleViewLoadMore);
        recycleView.setOnRefreshListener(presenter::recycleViewRefresh);
        lRecyclerViewAdapter.setOnItemClickListener((view, position) -> presenter.recycleItemClick(view, position));
//        recycleView.forceToRefresh();


    }

    public void recycleItemClick(View view, int position, T data) {

    }

    public int getScreenWidth() {
        WindowManager wm = (WindowManager) MainApp.appContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        return display.getWidth();
    }
    public int getScreenHeight() {
        WindowManager wm = (WindowManager) MainApp.appContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        return display.getHeight();
    }
    public void getPermission(String permission,PermissionListener permissionListener) {
        RxPermissions rxPermissions = new RxPermissions(getActivity());
        rxPermissions.request(permission).subscribe(grant -> {
            if (grant)
                permissionListener.accept(permission);
            else
                permissionListener.refuse(permission);

        });
    }
}
