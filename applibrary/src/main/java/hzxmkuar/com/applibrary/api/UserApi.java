package hzxmkuar.com.applibrary.api;

import hzxmkuar.com.applibrary.domain.BaseParam;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.login.UserInfoTo;
import hzxmkuar.com.applibrary.domain.main.UploadImageTo;
import hzxmkuar.com.applibrary.domain.user.ModifyImageParam;
import hzxmkuar.com.applibrary.domain.user.ModifyNickParam;
import hzxmkuar.com.applibrary.domain.user.MyselfUserTo;
import hzxmkuar.com.applibrary.domain.user.NickNameTo;
import hzxmkuar.com.applibrary.domain.user.VipTo;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 * Created by Administrator on 2019/1/8.
 */

public interface UserApi  {
    /**
     * 获取用户信息
     */
    @POST("Api/User/getUserInfo")
    Observable<MessageTo<UserInfoTo>>getUserInfo(@Body BaseParam param);


    /**
     * 获取用户VIP信息
     */
    @POST("Api/User/memberCenter")
    Observable<MessageTo<MyselfUserTo>>getMyselfUserInfo(@Body BaseParam param);

    /**
     * 修改用户头像
     */
    @POST("Api/User/updateUserFace")
    Observable<MessageTo>modifyHead(@Body ModifyImageParam param);

    /**
     * 修改昵称
     */
    @POST("Api/User/updateUsername")
    Observable<MessageTo>modifyNick(@Body ModifyNickParam param);

    /**
     * 上传照片
     */
    @Multipart
    @POST("api/upload/uploadImg")
    Observable<MessageTo<UploadImageTo>>uploadImage(
            @Part("time") RequestBody timeBody,
            @Part("hash") RequestBody hash,
            @Part("apiId") RequestBody apiId,
            @Part("terminal") RequestBody terminal,
            @Part("uid") RequestBody uid,
            @Part("hashid") RequestBody hashid,
            @Part("fileName") RequestBody fileName,
            @Part("tags") RequestBody tags,
            @Part("description") RequestBody description,
            @Part MultipartBody.Part file);
}
