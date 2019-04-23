package hzxmkuar.com.applibrary.domain.delivery.main;

import hzxmkuar.com.applibrary.domain.BaseParam;
import lombok.Data;

@Data
public class OpenDoorParam extends BaseParam {
    private int order_id;
    private String wardrobe_no;

}
