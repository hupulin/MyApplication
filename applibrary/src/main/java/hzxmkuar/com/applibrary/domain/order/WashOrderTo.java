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

    private int order_id;
    private String order_sn;
    private String wardrobe_title;
    private String address;
    private String order_amount;
    private String status_txt;
    private ButtonInfoTo button_list;

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
    }


}
