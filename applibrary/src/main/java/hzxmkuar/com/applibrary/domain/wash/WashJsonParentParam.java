package hzxmkuar.com.applibrary.domain.wash;

import java.util.List;

import hzxmkuar.com.applibrary.domain.BaseParam;
import lombok.Data;

/**
 * Created by Administrator on 2019/1/9.
 */
@Data
public class WashJsonParentParam extends BaseParam {
    private String classid;
    private List<WashJsonDetailTo>service_data;
}
