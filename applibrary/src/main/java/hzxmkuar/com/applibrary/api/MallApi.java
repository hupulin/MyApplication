package hzxmkuar.com.applibrary.api;

import hzxmkuar.com.applibrary.domain.BaseParam;
import hzxmkuar.com.applibrary.domain.MessageListTo;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.mall.GoodsIdParam;
import hzxmkuar.com.applibrary.domain.mall.MallGoodsListParam;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2019/1/6.
 */

public interface MallApi {
    /**
     * 获取类型
     */
    @POST("Api/Goods/get_goods_menu")
    Observable<MessageListTo>getTypeList(@Body BaseParam param);


    /**
     * 获取首页商品
     */
    @POST("Api/Goods/getInfoList")
    Observable<MessageListTo>getGoodsList(@Body MallGoodsListParam param);

    /**
     * 获取商品详情
     */
    @POST("Api/Goods/getGoodsInfo")
    Observable<MessageTo>getGoodsDetail(@Body GoodsIdParam param);
}
