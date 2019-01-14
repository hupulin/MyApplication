package hzxmkuar.com.applibrary.domain.myself;

import java.util.List;

import lombok.Data;

/**
 * Created by Administrator on 2019/1/9.
 */
@Data
public class CouponParentTo {
    private List<CouponTo>unuse_list;
    private List<CouponTo>used_list;
    private List<CouponTo>expired_list;
}
