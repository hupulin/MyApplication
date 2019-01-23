package hzxmkuar.com.applibrary.domain.delivery.login;

import hzxmkuar.com.applibrary.domain.BaseParam;
import lombok.Data;

/**
 * Created by Administrator on 2019/1/16.
 */
@Data
public class updateUserPasswdParam extends BaseParam {
    private String passwd;
    private String newpasswd;
    private String re_newpasswd;
}
