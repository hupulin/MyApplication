package com.hzxm.wolaixiqh.person.present;

import com.hzxm.wolaixiqh.base.BaseActivity;
import com.hzxm.wolaixiqh.base.BasePresenter;
import com.hzxm.wolaixiqh.base.MyObserver;
import com.hzxm.wolaixiqh.base.impl.UploadImageModel;
import com.hzxm.wolaixiqh.base.impl.UploadImagePathListener;
import com.hzxm.wolaixiqh.person.ChangeInfoActivity;

import java.util.ArrayList;

import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.DeliveryApi;
import hzxmkuar.com.applibrary.domain.BaseParam;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.delivery.main.MainUserInfo;
import hzxmkuar.com.applibrary.domain.delivery.main.updateUserInfoParam;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2019/1/17.
 */

public class ChangeInfoPresent extends BasePresenter implements UploadImagePathListener {
    private String key;

    public ChangeInfoPresent(BaseActivity activity) {
        initContext(activity);
    }

    public void modifyImage(String imagePaths) {
        ArrayList<String> imageList = new ArrayList<>();
        imageList.add(imagePaths);
        UploadImageModel model = new UploadImageModel();
        model.uploadImage(imageList, this);


    }

    @Override
    public void uploadImageSuccess(String path, String key) {
        ((ChangeInfoActivity) activity).uploadImageSuccess(path);
        this.key = key;
//        ModifyImageParam param=new ModifyImageParam();
//        param.setFace(key);
//        param.setHash(getHashString(ModifyImageParam.class,param));
//        showLoadingDialog();
//        ApiClient.create(UserApi.class).modifyHead(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
//                new MyObserver<MessageTo>(this) {
//                    @Override
//                    public void onNext(MessageTo msg) {
//                        ((EditUserActivity)activity).uploadImageSuccess(path);
//                    }
//                }
//        );
    }

    public void updateUserInfo(String nickname, String gender, String birthday) {
        updateUserInfoParam param = new updateUserInfoParam();
        param.setUid(userInfoTo.getUid());
        param.setHashid(userInfoTo.getHashid());
        if (key == null)
            param.setFace("");
        else
            param.setFace(key);
        param.setNickname(nickname);
        if (birthday == null)
            param.setBirthday("");
        else
            param.setBirthday(birthday);
        param.setGender(gender);
        param.setHash(getHashString(updateUserInfoParam.class, param));

        showLoadingDialog();
        ApiClient.create(DeliveryApi.class).updateUserInfo(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageTo>(this) {
                    @Override
                    public void onNext(MessageTo msg) {
                        if (msg.getCode() == 0) {
                            showMessage("修改个人资料成功");
                            submitDataSuccess(msg.getData());
                        } else {
                            showMessage(msg.getMsg());
                        }
                    }
                }
        );
    }

    public void getUserInfo() {
        BaseParam param = new BaseParam();
        param.setUid(userInfoTo.getUid());
        param.setHashid(userInfoTo.getHashid());

        param.setHash(getHashString(updateUserInfoParam.class, param));

        showLoadingDialog();
        ApiClient.create(DeliveryApi.class).getUserInfo(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageTo<MainUserInfo>>(this) {
                    @Override
                    public void onNext(MessageTo<MainUserInfo> msg) {
                        if (msg.getCode() == 0) {
                            getDataSuccess(msg.getData());
                        } else {
                            showMessage(msg.getMsg());
                        }
                    }
                }
        );
    }
}
