package hzxmkuar.com.applibrary.api;

import hzxmkuar.com.applibrary.domain.BaseParam;
import hzxmkuar.com.applibrary.domain.MessageListTo;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.order.MyOrderParam;
import hzxmkuar.com.applibrary.domain.wash.AddWashOrderParam;
import hzxmkuar.com.applibrary.domain.wash.MyWashOrderParam;
import hzxmkuar.com.applibrary.domain.wash.WashJsonParam;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2019/1/9.
 */

public interface WashApi {
    /**
     * 洗衣种类
     */
    @POST("Api/Serviceproject/getServiceprojectTreeList")
    Observable<MessageListTo>getWashInfo(@Body BaseParam param);

    /**
     * 订单展示
     */
    @POST("Api/Wash/reserve_order")
    Observable<MessageTo>getOrderInfo(@Body WashJsonParam param);

    /**
     * 添加订单
     */
    @POST("Api/Wash/place_order")
    Observable<MessageTo>addOrder(@Body AddWashOrderParam param);

    /**
     * 订单列表
     */
    @POST("Api/Wash/order_list")
    Observable<MessageListTo>getOrderList(@Body MyWashOrderParam param);

    /**
     * 优惠券列表
     */
    @POST("Api/Coupon/all_list")
    Observable<MessageTo>getCouponList(@Body BaseParam param);
}
