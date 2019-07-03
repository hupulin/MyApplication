package com.hzxm.wolaixiqh.main.present;

import com.hzxm.wolaixiqh.base.BaseFragment;
import com.hzxm.wolaixiqh.base.BasePresenter;
import com.hzxm.wolaixiqh.base.MyObserver;

import java.util.ArrayList;
import java.util.List;

import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.DeliveryApi;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.delivery.main.DeLiveryOrderListTo;
import hzxmkuar.com.applibrary.domain.delivery.main.IdParam;
import hzxmkuar.com.applibrary.domain.delivery.main.PageParam;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2019/1/14.
 */

public class MainPresenter extends BasePresenter {
    private List<DeLiveryOrderListTo.ListsEntity> Orderlist = new ArrayList<>();

    public MainPresenter(BaseFragment activity) {
        initContext(activity);
    }

    public void getOrderList() {
        PageParam param = new PageParam();
        param.setUid(userInfoTo.getUid());
        param.setHashid(userInfoTo.getHashid());
        param.setPage(recyclePageIndex);
        param.setHash(getHashString(PageParam.class, param));
        showLoadingDialog();
        ApiClient.create(DeliveryApi.class).getPickupOrderlistMain(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
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

    public void pickupConfirm(int id) {
        IdParam param = new IdParam();
        param.setUid(userInfoTo.getUid());
        param.setHashid(userInfoTo.getHashid());
        param.setOrder_id(id);
        param.setHash(getHashString(IdParam.class, param));
        showLoadingDialog();
        ApiClient.create(DeliveryApi.class).pickupConfirm(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageTo>(this) {
                    @Override
                    public void onNext(MessageTo msg) {
                        if (msg.getCode() == 0) {
                            showMessage("已确认取货");
                            getOrderList();
                        }else{
                            showMessage(msg.getMsg());
                        }
                    }
                }
        );
    }

    @Override
    public void recycleViewLoadMore() {
        super.recycleViewLoadMore();
        getOrderList();
    }

    @Override
    public void recycleViewRefresh() {
        super.recycleViewRefresh();
        getOrderList();
    }


}