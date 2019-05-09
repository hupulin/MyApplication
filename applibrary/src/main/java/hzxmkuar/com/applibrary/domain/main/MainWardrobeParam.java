package hzxmkuar.com.applibrary.domain.main;

import hzxmkuar.com.applibrary.domain.BaseParam;
import lombok.Data;

/**
 * Created by Administrator on 2019/1/6.
 */
@Data
public class MainWardrobeParam extends BaseParam {
    private String lat="120.13";
    private String lng="30.27";
    private String keywords="";
    private int page=1;
    private int pos_city=934;
}
