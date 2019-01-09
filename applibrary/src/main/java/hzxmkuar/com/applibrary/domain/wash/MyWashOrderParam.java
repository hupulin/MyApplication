package hzxmkuar.com.applibrary.domain.wash;

import hzxmkuar.com.applibrary.domain.BaseParam;
import lombok.Data;

/**
 * Created by Administrator on 2019/1/7.
 */
@Data
public class MyWashOrderParam extends BaseParam {
    private int order_type;
    private int page;
}
