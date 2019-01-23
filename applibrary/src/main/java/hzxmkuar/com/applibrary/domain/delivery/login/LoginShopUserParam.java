package hzxmkuar.com.applibrary.domain.delivery.login;

import hzxmkuar.com.applibrary.domain.BaseParam;
import lombok.Data;

/**
 * Created by Administrator on 2019/1/12.
 */
@Data
public class LoginShopUserParam extends BaseParam {
    private String username;
    private String passwd;
    private String jpush_id;
    private int role_id;//用户角色，1=取货员，2=送货员
}
