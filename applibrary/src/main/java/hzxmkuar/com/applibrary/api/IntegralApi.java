package hzxmkuar.com.applibrary.api;

import hzxmkuar.com.applibrary.domain.BaseParam;
import hzxmkuar.com.applibrary.domain.MessageListTo;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.integral.AddIntegralOrderParam;
import hzxmkuar.com.applibrary.domain.integral.IntegralGoodsParam;
import hzxmkuar.com.applibrary.domain.integral.PageParam;
import hzxmkuar.com.applibrary.domain.mall.GoodsIdParam;
import hzxmkuar.com.applibrary.domain.mall.MallGoodsListParam;
import hzxmkuar.com.applibrary.domain.order.AddOrderParam;
import hzxmkuar.com.applibrary.domain.order.MyOrderParam;
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

    /**
     * 积分明细
     */
    @POST("Api/Integralgoods/my_integral")
    Observable<MessageTo>getIntegralDetail(@Body BaseParam param);

    /**
     * 积分使用纪录
     */
    @POST("Api/Integralgoods/integral_usage_record")
    Observable<MessageListTo>getIntegralRecord(@Body PageParam param);

    /**
     * 订单记录
     */
    @POST("Api/Integralgoods/order_list")
    Observable<MessageListTo>getOrderList(@Body MyOrderParam param);


}
