package hzxmkuar.com.applibrary.domain.order;

import hzxmkuar.com.applibrary.domain.BaseParam;
import lombok.Data;

/**
 * Created by Administrator on 2019/1/7.
 */
@Data
public class AddAddressParam extends BaseParam {
    private String consignee;
    private String telephone;
    private String province;
    private String city;
    private String area;
    private String address;
}
