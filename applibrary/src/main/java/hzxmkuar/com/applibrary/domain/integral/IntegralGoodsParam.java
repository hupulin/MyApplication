package hzxmkuar.com.applibrary.domain.integral;

import hzxmkuar.com.applibrary.domain.BaseParam;
import lombok.Data;

/**
 * Created by Administrator on 2019/1/8.
 */
@Data
public class IntegralGoodsParam extends BaseParam {
    private String search="";
    private int page=1;
}
