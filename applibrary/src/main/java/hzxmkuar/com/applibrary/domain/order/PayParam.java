package hzxmkuar.com.applibrary.domain.order;

import hzxmkuar.com.applibrary.domain.BaseParam;
import lombok.Data;

/**
 * Created by Administrator on 2019/1/7.
 */
@Data
public class PayParam extends BaseParam {
    private int order_id;
    private int pay_type;
    private int coupon_id;
}
