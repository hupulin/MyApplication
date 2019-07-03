package hzxmkuar.com.applibrary.api;

import hzxmkuar.com.applibrary.LoginTo;
import hzxmkuar.com.applibrary.domain.BaseParam;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.VerificationParam;
import hzxmkuar.com.applibrary.domain.login.BindPhoneParam;
import hzxmkuar.com.applibrary.domain.login.LoginParam;
import hzxmkuar.com.applibrary.domain.login.ModifyPasswordParam;
import hzxmkuar.com.applibrary.domain.login.RegisterParam;
import hzxmkuar.com.applibrary.domain.login.SendLoginParam;
import hzxmkuar.com.applibrary.domain.login.UploadImageParam;
import hzxmkuar.com.applibrary.domain.login.UserLoginParam;
import hzxmkuar.com.applibrary.domain.login.VerificationLoginParam;
import hzxmkuar.com.applibrary.domain.login.WechatLoginParam;
import hzxmkuar.com.applibrary.domain.login.WechatLoginTo;
import hzxmkuar.com.applibrary.domain.main.UploadImageTo;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2018/9/1.
 **/

public interface LoginApi {
    /**
     * 发送验证码
     */
    @POST("Api/Sms/sendCode")
    Observable<MessageTo>getVerificationCode(@Body VerificationParam param);

    /**
     * 微信登录
     */
    @POST("Api/User/oauthLogin")
    Observable<MessageTo>wechatLogin(@Body WechatLoginParam param);
    /**
     * 手机号绑定
     */
    @POST("Api/User/mobileSmsBind")
    Observable<MessageTo>bindPhone(@Body BindPhoneParam param);
    /**
     * 登录
     */
    @POST("Api/User/userLogin")
    Observable<MessageTo>login(@Body LoginParam param);

    /**
     * 验证码登录
     */
    @POST("Api/User/mobileSmsLogin")
    Observable<MessageTo>verificationLogin(@Body VerificationLoginParam param);

 /**
     * 修改密码
     */
    @POST("Api/User/retrieve_password")
    Observable<MessageTo>modifyPassword(@Body ModifyPasswordParam param);


    /**
     * 注册
     */
    @POST("Api/User/userRegister")
    Observable<MessageTo>register(@Body RegisterParam param);

    /**
     * 绑定手机号
     */
    @POST("Api/User/mobileSmsBind")
    Observable<MessageTo>bindPhone(@Body RegisterParam param);

    /**
     * 登录
     */
    @POST("Api/Shopuser/passwdLogin")
    Observable<MessageTo>userLogin(@Body UserLoginParam param);

    /**
     * 发送验证码
     */
    @POST("sms/verifycode/login")
    Observable<MessageTo>sendCode(@Body SendLoginParam param);
}
