package hzxmkuar.com.applibrary.domain.order;

import lombok.Data;

/**
 * Created by Administrator on 2018/12/26.
 */
@Data
public class WashOrderTo {
    /**
     * order_id : 1128
     * order_sn : W20181227025445893
     * wardrobe_title : 西湖1号柜
     * address : 城西银泰
     * order_amount : 10.00
     * status_txt : 预订成功
     */


    private ButtonInfoTo button_list;
    /**
     * order_id : 1253
     * order_sn : W190508021153468
     * deposit_wardrobe_no : 100011
     * deposit_wardrobe_name : 8号智能柜
     * deposit_address : 龙湖滟澜山
     * delivery_wardrobe_no : 100012
     * delivery_wardrobe_name : 6号智能柜
     * delivery_address : 龙湖金沙湖天街
     * order_time : 2019-05-08
     * pickup_time : 2019-05-08
     * order_amount : 12.00
     * remarks : 啊啊啊啊啊
     * status_txt : 已取消
     * order_button : 9
     */

    private int order_id;
    private String order_sn;
    private String deposit_wardrobe_no;
    private String deposit_wardrobe_name;
    private String deposit_address;
    private String delivery_wardrobe_no;
    private String delivery_wardrobe_name;
    private String delivery_address;
    private String order_time;
    private String pickup_time;
    private String order_amount;
    private String remarks;
    private String status_txt;
    private int order_button;



    /**
     * qxdd_btn : 0
     * smch_btn : 0
     * smqh_btn : 0
     * qpj_btn : 0
     */


    @Data
    public static class ButtonInfoTo {
        private int qxdd_btn;
        private int smch_btn;
        private int smqh_btn;
        private int qpj_btn;
        private int pay_btn;
    }


}
