package hzxmkuar.com.applibrary.api;

import hzxmkuar.com.applibrary.domain.BaseParam;
import hzxmkuar.com.applibrary.domain.MessageListTo;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.integral.PageParam;
import hzxmkuar.com.applibrary.domain.myself.DeleteCollectParam;
import hzxmkuar.com.applibrary.domain.myself.ExchangePayParam;
import hzxmkuar.com.applibrary.domain.myself.FeedbackParam;
import hzxmkuar.com.applibrary.domain.myself.ModifyPasswordParam;
import hzxmkuar.com.applibrary.domain.order.WeChatPayTo;
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
    @POST("Api/Feedback/writeDo")
    Observable<MessageTo>feedback(@Body FeedbackParam param);

    /**
     * 获取充值套餐
     */
    @POST("Api/Recharge/get_package_list")
    Observable<MessageListTo>getExchangeList(@Body BaseParam param);

    /**
     * 充值
     */
    @POST("Api/Order/recharge")
    Observable<MessageTo>exchange(@Body ExchangePayParam param);/**
     * 充值微信
     */
    @POST("Api/Order/recharge")
    Observable<MessageTo<WeChatPayTo>>exchangeWx(@Body ExchangePayParam param);

    /**
     * 我的收藏
     */
    @POST("Api/Goods/get_user_collected_list")
    Observable<MessageListTo>getMyCollectList(@Body PageParam param);

    /**
     * 删除收藏
     */
    @POST("Api/Goods/del_collected_goods")
    Observable<MessageTo>deleteCollect(@Body DeleteCollectParam param);

    /**
     * 修改密码
     */
    @POST("Api/user/updatePasswordByOld")
    Observable<MessageTo>modifyPassword(@Body ModifyPasswordParam param);

}
