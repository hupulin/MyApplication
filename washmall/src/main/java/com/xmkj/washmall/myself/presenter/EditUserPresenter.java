package com.xmkj.washmall.myself.presenter;

import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.BasePresenter;
import com.xmkj.washmall.base.MyObserver;
import com.xmkj.washmall.base.impl.UploadImageModel;
import com.xmkj.washmall.base.impl.UploadImagePathListener;
import com.xmkj.washmall.myself.EditUserActivity;

import java.util.ArrayList;

import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.UserApi;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.user.ModifyImageParam;
import hzxmkuar.com.applibrary.domain.user.ModifyNickParam;
import hzxmkuar.com.applibrary.domain.user.NickNameTo;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2019/1/8.
 */

public class EditUserPresenter extends BasePresenter implements UploadImagePathListener {

    public EditUserPresenter(BaseActivity activity){
        initContext(activity);
    }

    public void modifyImage(String imagePaths){
        ArrayList<String> imageList=new ArrayList<>();
        imageList.add(imagePaths);
        UploadImageModel model=new UploadImageModel();
        model.uploadImage(imageList,this);


    }


    @Override
    public void uploadImageSuccess(String path,String key) {

        ModifyImageParam param=new ModifyImageParam();
        param.setFace(key);
        param.setHash(getHashString(ModifyImageParam.class,param));

        showLoadingDialog();
        ApiClient.create(UserApi.class).modifyHead(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageTo>(this) {
                    @Override
                    public void onNext(MessageTo msg) {
                        ((EditUserActivity)activity).uploadImageSuccess(path);
                    }
                }
        );
    }

    public void modifyNick(String userName){
        ModifyNickParam param=new ModifyNickParam();
        param.setUsername(userName);
        param.setUid(userInfoTo.getUid());
        param.setHashid(userInfoTo.getHashid());
        param.setHash(getHashString(ModifyNickParam.class,param));
        showLoadingDialog();
        ApiClient.create(UserApi.class).modifyNick(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageTo>(this) {
                    @Override
                    public void onNext(MessageTo msg) {
                        if(msg.getCode()==0)
                            submitDataSuccess(msg.getData());
                        else
                            showMessage(msg.getMsg());
                    }
                }
        );
    }
}
