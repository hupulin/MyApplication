package hzxmkuar.com.applibrary.domain.delivery.main;

import hzxmkuar.com.applibrary.domain.BaseParam;
import lombok.Data;

/**
 * Created by Administrator on 2019/1/18.
 */
@Data
public class updateUserInfoParam extends BaseParam {
    private String face;
    private String nickname;
    private String gender;
    private String birthday;
}
