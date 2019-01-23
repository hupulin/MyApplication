package hzxmkuar.com.applibrary.domain.login;

import hzxmkuar.com.applibrary.domain.BaseParam;
import lombok.Data;

/**
 * Created by Administrator on 2019/1/14.
 */
@Data
public class UserLoginParam extends BaseParam {
    private String username;
    private String passwd;
    private int role_id;
    private String jpush_id;
}
