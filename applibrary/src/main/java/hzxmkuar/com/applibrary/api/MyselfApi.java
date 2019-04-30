package hzxmkuar.com.applibrary.api;

import hzxmkuar.com.applibrary.domain.BaseParam;
import hzxmkuar.com.applibrary.domain.MessageListTo;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.myself.ExchangePayParam;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by xzz on 2019/4/29.
 */

public interface MyselfApi {
    /**
     * 意见反馈
     */
//    @POST("")

    /**
     * 获取充值套餐
     */
    @POST("Api/Recharge/get_package_list")
    Observable<MessageListTo>getExchangeList(@Body BaseParam param);

    /**
     * 充值
     */
    @POST("Api/Order/recharge")
    Observable<MessageTo>exchange(@Body ExchangePayParam param);

}
