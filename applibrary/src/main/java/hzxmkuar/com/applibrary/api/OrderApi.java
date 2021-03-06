package hzxmkuar.com.applibrary.api;

import hzxmkuar.com.applibrary.IdParam;
import hzxmkuar.com.applibrary.domain.BaseParam;
import hzxmkuar.com.applibrary.domain.MessageListTo;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.mall.CanUseCouponParam;
import hzxmkuar.com.applibrary.domain.mall.OrderIdParam;
import hzxmkuar.com.applibrary.domain.mall.SettlementIdParam;
import hzxmkuar.com.applibrary.domain.message.IdsParam;
import hzxmkuar.com.applibrary.domain.order.AddAddressParam;
import hzxmkuar.com.applibrary.domain.order.AddOrderParam;
import hzxmkuar.com.applibrary.domain.order.EditAddressParam;
import hzxmkuar.com.applibrary.domain.order.MyOrderParam;
import hzxmkuar.com.applibrary.domain.order.PayParam;
import hzxmkuar.com.applibrary.domain.order.WeChatPayTo;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2019/1/7.
 */

public interface OrderApi {
    /**
     * 确定订单页
     */
    @POST("Api/Order/comfirmOrder_show")
    Observable<MessageTo>getOrderInfo(@Body SettlementIdParam param);


    /**
     * 添加地址
     */
    @POST("Api/Address/add")
    Observable<MessageTo>addAddress(@Body AddAddressParam param);


    /**
     * 地址列表
     */
    @POST("Api/Address/index")
    Observable<MessageListTo>addressList(@Body BaseParam param);

    /**
     * 删除地址
     */
    @POST("Api/Address/delOne")
    Observable<MessageTo>deleteAddress(@Body IdParam param);

    /**
     * 设置默认地址
     */
    @POST("Api/Address/setDefault")
    Observable<MessageTo>setDefaultAddress(@Body IdParam param);

    /**
     * 编辑地址
     */
    @POST("Api/Address/edit")
    Observable<MessageTo>editAddress(@Body EditAddressParam param);


    /**
     * 立即下单
     */
    @POST("Api/Order/place_order")
    Observable<MessageTo>addOrder(@Body AddOrderParam param);

    /**
     * 支付
     */
    @POST("Api/Order/goodsPay")
    Observable<MessageTo>getPayInfo(@Body PayParam param);

    /**
     * 支付
     */
    @POST("Api/Order/goodsPay")
    Observable<MessageTo<WeChatPayTo>>getWxPayInfo(@Body PayParam param);
  /**
     * 洗衣支付
     */
    @POST("Api/Order/pay_wash")
    Observable<MessageTo>getWashPayInfo(@Body PayParam param);

    /**
     * 洗衣支付
     */
    @POST("Api/Order/pay_wash")
    Observable<MessageTo<WeChatPayTo>>getWXWashPayInfo(@Body PayParam param);

    /**
     * 我的订单
     */
    @POST("Api/Order/orderList")
    Observable<MessageListTo>myMallOrderList(@Body MyOrderParam param);


    /**
     * 确认收货
     */
    @POST("Api/Order/confirmCompletedForUser")
    Observable<MessageTo>confirmReceiver(@Body OrderIdParam param);

    /**
     * 确认收货
     */
    @POST("Api/Integralgoods/confirmCompleted")
    Observable<MessageTo>confirmIntegralgoodsReceiver(@Body OrderIdParam param);

    /**
     * 取消订单
     */
    @POST("Api/Order/cancelOrderForUser")
    Observable<MessageTo>cancelOrder(@Body OrderIdParam param);

    /**
     * 催发货
     */
    @POST("Api/Order/rush_send")
    Observable<MessageTo>quickSend(@Body OrderIdParam param);

    /**
     * 我的订单详情
     */
    @POST("Api/Order/orderDetailForUser")
    Observable<MessageTo>orderDetail(@Body OrderIdParam param);


    /**
     * 可以使用优惠券列表
     */
    @POST("Api/Coupon/can_use_list")
    Observable<MessageListTo>selectCouponList(@Body CanUseCouponParam param);
}
