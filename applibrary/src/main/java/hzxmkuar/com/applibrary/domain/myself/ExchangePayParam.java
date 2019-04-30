package hzxmkuar.com.applibrary.domain.myself;

import hzxmkuar.com.applibrary.domain.BaseParam;
import lombok.Data;

/**
 * Created by xzz on 2019/4/30.
 */
@Data
public class ExchangePayParam extends BaseParam {

    private String package_id;
    private String pay_type;
}
