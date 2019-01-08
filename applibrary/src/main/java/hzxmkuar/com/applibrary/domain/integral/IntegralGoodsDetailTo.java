package hzxmkuar.com.applibrary.domain.integral;

import java.util.List;

import hzxmkuar.com.applibrary.domain.mall.GoodsDetailTo;
import lombok.Data;

/**
 * Created by Administrator on 2019/1/8.
 */
@Data
public class IntegralGoodsDetailTo {

    /**
     * goods_id : 21
     * goods_name : 10元话费直充
     * goods_image : http://xmap18100040.php.hzxmnet.com/uploads/picture/2018-11-21/9e786d91782106b034f50fa3caa883d6.jpg
     * goods_integral : 1000.00
     * desc : 礼品描述：
     话费直充，是指平台将话费直接充入客户手机账户。
     客户须知：
     话费直充礼品一经兑出或转赠，将不予退换。
     生效时间：
     客户成功兑换后，24小时内生效。
     * goods_content : []
     * exchange_num : 0
     */

    private int goods_id;
    private String goods_name;
    private String goods_image;
    private int goods_integral;
    private String desc;
    private int exchange_num;
    private List<GoodsDetailTo.GoodsContentBean> goods_content;


}
