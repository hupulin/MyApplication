package com.xmkj.washmall.mall.presenter;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.BasePresenter;
import com.xmkj.washmall.base.MyObserver;
import com.xmkj.washmall.mall.GoodsDetailActivity;

import java.util.List;

import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.MallApi;
import hzxmkuar.com.applibrary.domain.MessageListTo;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.main.MallGoodsTo;
import hzxmkuar.com.applibrary.domain.mall.GoodsDetailTo;
import hzxmkuar.com.applibrary.domain.mall.GoodsIdParam;
import hzxmkuar.com.applibrary.domain.mall.PurchaseParam;
import hzxmkuar.com.applibrary.domain.mall.SpecificationTo;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2019/1/6.
 **/

public class GoodsDetailPresenter extends BasePresenter {

    public GoodsDetailPresenter(BaseActivity activity){
        initContext(activity);
        getGoodsDetail();

    }

    private void getGoodsDetail(){
        GoodsIdParam param=new GoodsIdParam();
        param.setHashid(userInfoTo.getHashid());
        param.setUid(userInfoTo.getUid());
        param.setGoods_id(((MallGoodsTo)activity.getIntent().getSerializableExtra("GoodsTo")).getGoods_id());
        param.setHash(getHashString(GoodsIdParam.class,param));
        ApiClient.create(MallApi.class).getGoodsDetail(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageTo>(this) {
                    @Override
                    public void onNext(MessageTo msg) {
                        if (msg.getCode()==0)
                        getDataSuccess(msg.getData());
                        else
                            showMessage(msg.getMsg());
                    }
                }
        );
    }

    public void getGoodsSpecification(){
        GoodsIdParam param=new GoodsIdParam();
        param.setGoods_id(((MallGoodsTo)activity.getIntent().getSerializableExtra("GoodsTo")).getGoods_id());
        param.setHash(getHashStringNoUser(GoodsIdParam.class,param));
        ApiClient.create(MallApi.class).getGoodsSpecification(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageListTo>(this) {
                    @Override
                    public void onNext(MessageListTo msg) {
                        if (msg.getCode()==0)
                            ((GoodsDetailActivity)activity).specificationDialog(new Gson().fromJson(JSON.toJSONString(msg.getDataList()),new TypeToken<List<SpecificationTo>>(){}.getType()));
                        else
                            showMessage(msg.getMsg());
                    }
                }
        );
    }

    public void purchase(String goodsId,String specId,String goodsNum){
        PurchaseParam param=new PurchaseParam();
        param.setHashid(userInfoTo.getHashid());
        param.setUid(userInfoTo.getUid());
        param.setGoods_id(goodsId);
        param.setSpec_id(specId);
        param.setGoods_num(goodsNum);
        param.setHash(getHashString(PurchaseParam.class,param));
        showLoadingDialog();
        ApiClient.create(MallApi.class).purchaseGoods(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageTo>(this) {
                    @Override
                    public void onNext(MessageTo msg) {
                   if (msg.getCode()==0)
                       submitDataSuccess(msg.getData());
                        else
                            showMessage(msg.getMsg());
                    }
                }
        );
    }

    public void addCar(String goodsId,String specId,String goodsNum){
        PurchaseParam param=new PurchaseParam();
        param.setHashid(userInfoTo.getHashid());
        param.setUid(userInfoTo.getUid());
        param.setGoods_id(goodsId);
        param.setSpec_id(specId);
        param.setGoods_num(goodsNum);
        param.setHash(getHashString(PurchaseParam.class,param));
        showLoadingDialog();
        ApiClient.create(MallApi.class).addCar(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageTo>(this) {
                    @Override
                    public void onNext(MessageTo msg) {
                        if (msg.getCode()==0){
                            ((GoodsDetailActivity)activity).addCarSuccess();
                        }else
                            showMessage(msg.getMsg());

                    }
                }
        );
    }

    public void collect(GoodsDetailTo mode){
      GoodsIdParam param=new GoodsIdParam();
        param.setGoods_id(mode.getGoods_id());
        param.setUid(userInfoTo.getUid());
        param.setHashid(userInfoTo.getHashid());
        param.setHash(getHashString(GoodsIdParam.class,param));
        showLoadingDialog();
        ApiClient.create(MallApi.class).collect(param).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                new MyObserver<MessageTo>(this) {
                    @Override
                    public void onNext(MessageTo msg) {
                        if (msg.getCode()==0){
                            ((GoodsDetailActivity)activity).collectSuccess();
                        }else
                            showMessage(msg.getMsg());
                    }
                }
        );
    }
}
