package hzxmkuar.com.applibrary.domain.wash;

import hzxmkuar.com.applibrary.domain.BaseParam;
import lombok.Data;

/**
 * Created by xzz on 2019/5/12.
 */
@Data
public class EvaluateOrderParam extends BaseParam{
    private String order_id;
    private String comment;
}
