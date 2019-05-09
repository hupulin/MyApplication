package com.hzxm.wolaixiqh.main.present;

import com.hzxm.wolaixiqh.base.BaseActivity;
import com.hzxm.wolaixiqh.base.BaseFragment;
import com.hzxm.wolaixiqh.base.BasePresenter;
import com.hzxm.wolaixiqh.base.MyObserver;
import com.hzxm.wolaixiqh.base.impl.UploadImageModel;
import com.hzxm.wolaixiqh.base.impl.UploadImagePathListener;

import java.util.ArrayList;
import java.util.List;

import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.DeliveryApi;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.delivery.main.DeLiveryOrderListTo;
import hzxmkuar.com.applibrary.domain.delivery.main.FeedBackParam;
import hzxmkuar.com.applibrary.domain.delivery.main.PageParam;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class EvaluatePresenter extends BasePresenter implements UploadImagePathListener {
    FeedBackParam param = new FeedBackParam();
    private String type;

    public EvaluatePresenter(BaseActivity activity) {
        initContext(activity);
    }

    public void upLoadImage(ArrayList<String> imageList, int orderId, String content, String type) {
        this.type = type;
        param.setUid(userInfoTo.getUid());
        param.setHashid(userInfoTo.getHashid());
        param.setOrder_id(orderId);
        param.setContent(content);
        UploadImageModel model = new UploadImageModel();
        if (imageList.size() != 0 ) {
            model.uploadImage(imageList, this);
        } else {

            if ("1".equals(type)) {
                FeedBackInfo("");
            } else{
                FeedbackBack("");
            }
        }


    }

    public void FeedBackInfo(String key) {
        param.setPic_id(key);

        param.setHash(getHashString(FeedBackParam.class, param));
        showLoadingDialog();
        ApiClient.create(DeliveryApi.class).FeedBackInfo(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageTo>(this) {
                    @Override
                    public void onNext(MessageTo msg) {
                        if (msg.getCode() == 0) {
                            submitDataSuccess(msg);
                            showMessage("反馈告知成功");

                        } else {
                            showMessage(msg.getMsg());
                        }
                    }
                }
        );
    }

    public void FeedbackBack(String key) {
        param.setPic_id(key);

        param.setHash(getHashString(FeedBackParam.class, param));
        showLoadingDialog();
        ApiClient.create(DeliveryApi.class).FeedbackBack(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageTo>(this) {
                    @Override
                    public void onNext(MessageTo msg) {
                        if (msg.getCode() == 0) {
                            getDataSuccess(msg);
                            showMessage("反馈退回成功");
                        }else{
                            showMessage(msg.getMsg());
                        }
                    }
                }
        );
    }

    @Override
    public void uploadImageSuccess(String path, String idKey) {
        if ("1".equals(type))
            FeedBackInfo(idKey);
        else
            FeedbackBack(idKey);
    }
}
