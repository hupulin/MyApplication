package hzxmkuar.com.applibrary.domain.wash;

import hzxmkuar.com.applibrary.domain.BaseParam;
import lombok.Data;

/**
 * Created by Administrator on 2019/1/9.
 */
@Data
public class AddWashOrderParam extends BaseParam {
    private int deposit_id;
    private String remarks;
    private String pickup_time;
    private int pickup_id;

}
