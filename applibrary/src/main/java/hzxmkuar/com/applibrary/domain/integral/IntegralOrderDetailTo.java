package hzxmkuar.com.applibrary.domain.integral;

import lombok.Data;

/**
 * Created by xzz on 2019/5/16.
 */
@Data
public class IntegralOrderDetailTo {

    /**
     * order_id : 1
     * status_txt : 待发货
     * new_status : 1
     * order_sn : E181220004320284
     * goods_name : 300元话费直充
     * goods_image : http://xmap18100040.php.hzxmnet.com/uploads/picture/2018-11-21/1631db012bda16c0f989dbfc505cbb8e.jpg
     * total_amount : 30000
     * consignee : 风云
     * telephone : 13738084631
     * address : 玉环路12号
     * remarks :
     * add_time : 2018-12-20 00:43:20
     * pay_time : 1970-01-01 08:00:00
     * send_time : 1970-01-01 08:00:00
     * finish_time : 1970-01-01 08:00:00
     * distribution : 快递
     */

    private int order_id;
    private String status_txt;
    private int new_status;
    private String order_sn;
    private String goods_name;
    private String goods_image;
    private int total_amount;
    private String consignee;
    private String telephone;
    private String address;
    private String remarks;
    private String add_time;
    private String pay_time;
    private String send_time;
    private String finish_time;
    private String distribution;


}
