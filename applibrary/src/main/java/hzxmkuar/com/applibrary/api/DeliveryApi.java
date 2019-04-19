package hzxmkuar.com.applibrary.api;

import hzxmkuar.com.applibrary.domain.BaseParam;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.delivery.login.LoginShopUserParam;
import hzxmkuar.com.applibrary.domain.delivery.login.updateUserPasswdParam;
import hzxmkuar.com.applibrary.domain.delivery.main.DeLiveryOrderListTo;
import hzxmkuar.com.applibrary.domain.delivery.main.IdParam;
import hzxmkuar.com.applibrary.domain.delivery.main.PageParam;
import hzxmkuar.com.applibrary.domain.delivery.main.UserInfoTo;
import hzxmkuar.com.applibrary.domain.delivery.main.updateUserInfoParam;
import hzxmkuar.com.applibrary.domain.delivery.news.NewsTo;
import hzxmkuar.com.applibrary.domain.delivery.news.ReadIdParam;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2019/1/15.
 */

public interface DeliveryApi {
    /**
     * user端登录
     */
    @POST("Api/Shopuser/passwdLogin")
    Observable<MessageTo>shopUserLogin(@Body LoginShopUserParam param);
    /**
     *【送货员】订单列表
     */
    @POST("Api/Shopuser/delivery_orderlist")
    Observable<MessageTo<DeLiveryOrderListTo>> getDeliveryOrderlist(@Body PageParam param);
    /**
     *【送货员】首页
     */
    @POST("Api/Shopuser/delivery_index")
    Observable<MessageTo<DeLiveryOrderListTo>> getDeliveryOrderlistMain(@Body PageParam param);
    /**
     *【取货员】首页
     */
    @POST("Api/Shopuser/pickup_index")
    Observable<MessageTo<DeLiveryOrderListTo>> getPickupOrderlistMain(@Body PageParam param);
    /**
     *消息中心
     */
    @POST("Api/Shopuser/get_message_list")
    Observable<MessageTo<NewsTo>> getMessageList(@Body PageParam param);
    /**
     *消息中心
     */
    @POST("Api/Shopuser/read_message")
    Observable<MessageTo> readMessage(@Body ReadIdParam param);
    /**
     *送货员个人中心
     */
    @POST("Api/Shopuser/delivery_usercenter")
    Observable<MessageTo<UserInfoTo>> getUserInfoDelivery (@Body BaseParam param);
    /**
     *取货员个人中心
     */
    @POST("Api/Shopuser/pickup_usercenter ")
    Observable<MessageTo<UserInfoTo>> getUserInfoPickup (@Body BaseParam param);
    /**
     *修改登录密码
     */
    @POST("Api/Shopuser/updateUserPasswd")
    Observable<MessageTo> updateUserPasswd(@Body updateUserPasswdParam param);
    /**
     *【送货员】通知用户取货
     */
    @POST("Api/Shopuser/notify_user_pickup")
    Observable<MessageTo> notifyUserPickup(@Body IdParam param);
    /**
     *修改个人资料
     */
    @POST("Api/Shopuser/updateUserInfo")
    Observable<MessageTo> updateUserInfo(@Body updateUserInfoParam param);
}
