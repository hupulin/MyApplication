package hzxmkuar.com.applibrary.domain.login;

import hzxmkuar.com.applibrary.domain.BaseParam;
import lombok.Data;

/**
 * Created by Administrator on 2019/1/6.
 */
@Data
public class ModifyPasswordParam extends BaseParam {
    private String mobile;
    private String sms_code;
    private String passwd;
    private String repasswd;
}
