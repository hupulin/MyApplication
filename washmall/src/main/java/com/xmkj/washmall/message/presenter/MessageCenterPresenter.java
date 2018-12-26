package com.xmkj.washmall.message.presenter;

import android.os.Handler;

import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import hzxmkuar.com.applibrary.domain.message.SystemMessageTo;

/**
 * Created by Administrator on 2018/12/26.
 */

public class MessageCenterPresenter extends BasePresenter {
    private List<SystemMessageTo>messageList=new ArrayList<>();
    public MessageCenterPresenter(BaseActivity activity){
        initContext(activity);
        getMessageList();
    }

    public void getMessageList(){
        for (int i=0;i<5;i++){
            SystemMessageTo messageTo=new SystemMessageTo();
            messageList.add(messageTo);
        }
        new Handler().postDelayed(() -> {setRecycleList(messageList);},500);

    }
}
