package hzxmkuar.com.applibrary.domain.order;

import hzxmkuar.com.applibrary.domain.BaseParam;
import lombok.Data;

/**
 * Created by Administrator on 2019/1/7.
 */
@Data
public class AddOrderParam extends BaseParam{
    private String settlement_ids;
    private int address_id;
    private String remarks="";
}
