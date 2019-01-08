package hzxmkuar.com.applibrary.domain.car;

import hzxmkuar.com.applibrary.domain.BaseParam;
import lombok.Data;

/**
 * Created by Administrator on 2019/1/8.
 */
@Data
public class ModifyCarNumParam extends BaseParam{
    private int cart_id;
    private int goods_num;
}
