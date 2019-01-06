package hzxmkuar.com.applibrary.domain.login;

import hzxmkuar.com.applibrary.domain.BaseParam;
import lombok.Data;

/**
 * Created by Administrator on 2018/9/10.
 */
@Data
public class BindPhoneParam extends BaseParam{
    private String mobile;
    private String sms_code;
    private String passwd;
    private String repasswd;
    private String addr_area;
    private String addr_detail;
    private String jpush_id;
    private String invite_code;
    private int oauth_id;
}
