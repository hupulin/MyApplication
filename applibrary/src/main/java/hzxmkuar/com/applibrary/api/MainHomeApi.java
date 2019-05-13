package hzxmkuar.com.applibrary.api;

import hzxmkuar.com.applibrary.IdParam;
import hzxmkuar.com.applibrary.domain.BaseParam;
import hzxmkuar.com.applibrary.domain.MessageListTo;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.integral.PageParam;
import hzxmkuar.com.applibrary.domain.main.MainAdParent;
import hzxmkuar.com.applibrary.domain.main.MainWardrobeParam;
import hzxmkuar.com.applibrary.domain.wardrobe.WardrobeDetailParam;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2019/1/6.
 */

public interface MainHomeApi  {
    /**
     *获取首页轮播图
     */
    @POST("Api/Content/index_top")
    Observable<MessageTo<MainAdParent>>getMainHomeAd(@Body BaseParam param);


    /**
     *获取柜子列表
     */
    @POST("Api/Wardrobe/get_list")
    Observable<MessageListTo>getWardrobeList(@Body MainWardrobeParam param);

    /**
     *获取柜子详情
     */
    @POST("Api/Wardrobe/get_detail")
    Observable<MessageTo>getWardrobeDetail(@Body WardrobeDetailParam param);

    /**
     *获取消息列表
     */
    @POST("Api/Message/msgList")
    Observable<MessageListTo>getMessageList(@Body PageParam param);

    /**
     *获取消息详情
     */
    @POST("Api/Message/msgDetail")
    Observable<MessageTo>getMessageDetail(@Body IdParam param);
}
