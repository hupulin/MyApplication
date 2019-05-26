package com.xmkj.washmall.message.presenter;

import android.os.Handler;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.BasePresenter;
import com.xmkj.washmall.base.MyObserver;

import java.util.ArrayList;
import java.util.List;

import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.MainHomeApi;
import hzxmkuar.com.applibrary.domain.MessageListTo;
import hzxmkuar.com.applibrary.domain.integral.PageParam;
import hzxmkuar.com.applibrary.domain.message.SystemMessageTo;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/12/26.
 */

public class MessageCenterPresenter extends BasePresenter {
    private List<SystemMessageTo> messageList = new ArrayList<>();

    public MessageCenterPresenter(BaseActivity activity) {
        initContext(activity);
        getMessageList();
    }

    public void getMessageList() {
        showLoadingDialog();
        PageParam param = new PageParam();
        param.setPage(recyclePageIndex);
        param.setHash(getHashString(PageParam.class, param));
        ApiClient.create(MainHomeApi.class).getMessageList(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageListTo>(this) {
                    @Override
                    public void onNext(MessageListTo msg) {
                        if (msg.getCode() == 0) {
                            if (recyclePageIndex == 1)
                                messageList.clear();
                            messageList.addAll(new Gson().fromJson(JSON.toJSONString(msg.getDataList()), new TypeToken<List<SystemMessageTo>>() {
                            }.getType()));
                            setRecycleList(messageList);
                        }else
                            showMessage(msg.getMsg());
                    }
                }
        );

    }

    @Override
    public void recycleViewLoadMore() {
        super.recycleViewLoadMore();
        getMessageList();
    }

    @Override
    public void recycleViewRefresh() {
        super.recycleViewRefresh();
        getMessageList();
    }
}
