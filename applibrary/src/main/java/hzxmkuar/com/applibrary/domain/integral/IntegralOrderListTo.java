package hzxmkuar.com.applibrary.domain.integral;

import lombok.Data;

/**
 * Created by Administrator on 2019/1/12.
 */
@Data
public class IntegralOrderListTo {

    /**
     * order_id : 1
     * status_txt : 待发货
     * order_sn : E181220004320284
     * goods_name : 300元话费直充
     * goods_image : http://xmap18100040.php.hzxmnet.com/uploads/picture/2018-11-21/1631db012bda16c0f989dbfc505cbb8e.jpg
     * total_amount : 30000
     */

    private int order_id;
    private String status_txt;
    private String order_sn;
    private String goods_name;
    private String goods_image;
    private int total_amount;
    private String remarks;


}
