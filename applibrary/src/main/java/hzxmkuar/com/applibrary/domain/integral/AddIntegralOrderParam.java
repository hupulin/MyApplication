package hzxmkuar.com.applibrary.domain.integral;

import hzxmkuar.com.applibrary.domain.BaseParam;
import lombok.Data;

/**
 * Created by Administrator on 2019/1/7.
 */
@Data
public class AddIntegralOrderParam extends BaseParam{
    private int goods_id;
    private int address_id;
    private String remarks;

}
