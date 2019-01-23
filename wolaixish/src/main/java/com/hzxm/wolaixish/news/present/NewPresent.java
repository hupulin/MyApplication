package com.hzxm.wolaixish.news.present;

import android.view.View;

import com.hzxm.wolaixish.base.BaseActivity;
import com.hzxm.wolaixish.base.BasePresenter;
import com.hzxm.wolaixish.base.MyObserver;

import java.util.ArrayList;
import java.util.List;

import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.DeliveryApi;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.delivery.main.DeLiveryOrderListTo;
import hzxmkuar.com.applibrary.domain.delivery.main.PageParam;
import hzxmkuar.com.applibrary.domain.delivery.news.NewsTo;
import hzxmkuar.com.applibrary.domain.delivery.news.ReadIdParam;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2019/1/16.
 */

public class NewPresent extends BasePresenter {
    private List<NewsTo.ListsEntity> newsList=new ArrayList<>();

    public NewPresent(BaseActivity activity) {
        initContext(activity);
    }
    public void getOrderList (int page){
        PageParam param=new PageParam();
        param.setUid(userInfoTo.getUid());
        param.setHashid(userInfoTo.getHashid());
        param.setPage(page);
        param.setHash(getHashString(PageParam.class,param));
        showLoadingDialog();
        ApiClient.create(DeliveryApi.class).getMessageList(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageTo<NewsTo>>(this) {
                    @Override
                    public void onNext(MessageTo<NewsTo> msg) {
                        if (msg.getCode()==0){
                            if (recyclePageIndex==1)
                                newsList.clear();
                            if (msg.getData().getLists()!=null)
                                newsList.addAll(msg.getData().getLists());
                            setRecycleList(newsList);
                            submitDataSuccess(newsList);
                        }
                    }
                }
        );
    }

   public void readMessage (String ids){
        ReadIdParam param=new ReadIdParam();
        param.setUid(userInfoTo.getUid());
        param.setHashid(userInfoTo.getHashid());
        param.setIds(ids);
        param.setHash(getHashString(ReadIdParam.class,param));
        showLoadingDialog();
        ApiClient.create(DeliveryApi.class).readMessage(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageTo>(this) {
                    @Override
                    public void onNext(MessageTo msg) {
                        if (msg.getCode()==0){
                            getOrderList(1);
                            getDataSuccess(msg.getData());

                        }
                    }
                }
        );
    }

    @Override
    public void recycleViewLoadMore() {
        super.recycleViewLoadMore();
        getOrderList(recyclePageIndex);
    }

    @Override
    public void recycleViewRefresh() {
        super.recycleViewRefresh();
        getOrderList(recyclePageIndex);
    }

    @Override
    public void recycleItemClick(View view, int position) {
        super.recycleItemClick(view, position);
    }
}