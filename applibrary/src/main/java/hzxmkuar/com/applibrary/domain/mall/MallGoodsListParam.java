package hzxmkuar.com.applibrary.domain.mall;

import hzxmkuar.com.applibrary.domain.BaseParam;
import lombok.Data;

/**
 * Created by Administrator on 2019/1/6.
 */
@Data
public class MallGoodsListParam extends BaseParam{
    private int cate_id=0;
    private int sort_type=0;
    private int sortord=0;
    private int page=1;

}
