package hzxmkuar.com.applibrary.api;

import hzxmkuar.com.applibrary.domain.BaseParam;
import hzxmkuar.com.applibrary.domain.MessageListTo;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.car.CarIdsParam;
import hzxmkuar.com.applibrary.domain.car.ModifyCarNumParam;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2019/1/8.
 */

public interface CarApi {
    /**
     * 购物车列表
     */
    @POST("Api/Cart/get_list")
    Observable<MessageListTo>getCarList(@Body BaseParam param);

    /**
     * 购物车列表
     */
    @POST("Api/Cart/update_goods_num")
    Observable<MessageTo>modifyGoodsNum(@Body ModifyCarNumParam param);

    /**
     * 删除购物车
     */
    @POST("Api/Cart/delete_goods")
    Observable<MessageTo>deleteGoods(@Body CarIdsParam param);

    /**
     * 购物车结算
     */
    @POST("Api/Cart/cart_settlement")
    Observable<MessageTo>carSettment(@Body CarIdsParam param);
}
