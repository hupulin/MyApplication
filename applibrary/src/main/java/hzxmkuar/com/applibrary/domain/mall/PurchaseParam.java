package hzxmkuar.com.applibrary.domain.mall;

import hzxmkuar.com.applibrary.domain.BaseParam;
import lombok.Data;

/**
 * Created by Administrator on 2019/1/7.
 */
@Data
public class PurchaseParam extends BaseParam {
    private String goods_id;
    private String spec_id;
    private String goods_num;
}
