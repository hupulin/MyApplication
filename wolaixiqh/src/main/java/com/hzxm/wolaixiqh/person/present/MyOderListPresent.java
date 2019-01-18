package com.hzxm.wolaixiqh.person.present;


import com.hzxm.wolaixiqh.base.BaseActivity;
import com.hzxm.wolaixiqh.base.BasePresenter;
import com.hzxm.wolaixiqh.base.MyObserver;

import java.util.ArrayList;
import java.util.List;

import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.DeliveryApi;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.delivery.main.DeLiveryOrderListTo;
import hzxmkuar.com.applibrary.domain.delivery.main.PageParam;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2019/1/17.
 */

public class MyOderListPresent extends BasePresenter {
    private List<DeLiveryOrderListTo.ListsEntity> Orderlist = new ArrayList<>();

    public MyOderListPresent(BaseActivity activity) {
        initContext(activity);
    }

    public void getMyOrderList(int page) {
        PageParam param = new PageParam();
        param.setUid(userInfoTo.getUid());
        param.setHashid(userInfoTo.getHashid());
        param.setPage(page);
        param.setHash(getHashString(PageParam.class, param));
        showLoadingDialog();
        ApiClient.create(DeliveryApi.class).getDeliveryOrderlist(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageTo<DeLiveryOrderListTo>>(this) {
                    @Override
                    public void onNext(MessageTo<DeLiveryOrderListTo> msg) {
                        if (msg.getCode() == 0) {
                            if (recyclePageIndex == 1)
                                Orderlist.clear();
                            if (msg.getData().getLists() != null)
                                Orderlist.addAll(msg.getData().getLists());
                            setRecycleList(Orderlist);
                        }
                    }
                }
        );
    }
    @Override
    public void recycleViewLoadMore() {
        super.recycleViewLoadMore();
        getMyOrderList(recyclePageIndex);
    }

    @Override
    public void recycleViewRefresh() {
        super.recycleViewRefresh();
        getMyOrderList(recyclePageIndex);
    }

}
