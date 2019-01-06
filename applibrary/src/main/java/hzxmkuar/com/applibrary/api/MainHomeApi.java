package hzxmkuar.com.applibrary.api;

import hzxmkuar.com.applibrary.domain.BaseParam;
import hzxmkuar.com.applibrary.domain.MessageListTo;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.main.MainAdParent;
import hzxmkuar.com.applibrary.domain.main.MainWardrobeParam;
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
}