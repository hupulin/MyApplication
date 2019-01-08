package hzxmkuar.com.applibrary.api;

import hzxmkuar.com.applibrary.domain.MessageListTo;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.integral.AddIntegralOrderParam;
import hzxmkuar.com.applibrary.domain.integral.IntegralGoodsParam;
import hzxmkuar.com.applibrary.domain.mall.GoodsIdParam;
import hzxmkuar.com.applibrary.domain.mall.MallGoodsListParam;
import hzxmkuar.com.applibrary.domain.order.AddOrderParam;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2019/1/8.
 */

public interface IntegralApi {
    /**
     * 获取积分商品
     */
    @POST("Api/Integralgoods/goods_list")
    Observable<MessageListTo> getGoodsList(@Body IntegralGoodsParam param);


    /**
     * 积分商品详情
     */
    @POST("Api/Integralgoods/goods_detail")
    Observable<MessageTo> getGoodsDetail(@Body GoodsIdParam param);

    /**
     * 积分订单展示
     */
    @POST("Api/Integralgoods/comfirmOrder_show")
    Observable<MessageTo> confirmOrder(@Body GoodsIdParam param);

    /**
     * 立即下单
     */
    @POST("Api/Integralgoods/place_order")
    Observable<MessageTo>addOrder(@Body AddIntegralOrderParam param);
}
