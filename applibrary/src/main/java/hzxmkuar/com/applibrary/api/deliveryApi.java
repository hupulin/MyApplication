package hzxmkuar.com.applibrary.api;

import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.delivery.main.DeLiveryOrderListTo;
import hzxmkuar.com.applibrary.domain.delivery.main.DeliveryOrderlistParam;
import hzxmkuar.com.applibrary.domain.delivery.login.LoginShopUserParam;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2019/1/15.
 */

public interface deliveryApi {
    /**
     * user端登录
     */
    @POST("Api/Shopuser/passwdLogin")
    Observable<MessageTo>shopUserLogin(@Body LoginShopUserParam param);
    /**
     *【送货员】订单列表
     */
    @POST("Api/Shopuser/delivery_orderlist")
    Observable<MessageTo<DeLiveryOrderListTo>> getDeliveryOrderlist(@Body DeliveryOrderlistParam param);
}
