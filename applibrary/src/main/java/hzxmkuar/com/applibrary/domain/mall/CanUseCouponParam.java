package hzxmkuar.com.applibrary.domain.mall;

import hzxmkuar.com.applibrary.domain.BaseParam;
import lombok.Data;

/**
 * Created by 1ONE on 2019/7/1.
 */
@Data
public class CanUseCouponParam extends BaseParam {
    private int user_type;
    private int order_id;
}
