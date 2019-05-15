package hzxmkuar.com.applibrary.domain.myself;

import hzxmkuar.com.applibrary.domain.BaseParam;
import lombok.Data;

/**
 * Created by xzz on 2019/5/15.
 */
@Data
public class ModifyPasswordParam extends BaseParam {

    private String oldpwd;
    private String newpwd;
    private String repeatpwd;
}
