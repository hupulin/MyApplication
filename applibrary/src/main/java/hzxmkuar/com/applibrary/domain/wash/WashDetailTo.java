package hzxmkuar.com.applibrary.domain.wash;

import java.util.List;

import lombok.Data;

/**
 * Created by 1ONE on 2019/5/29.
 */
@Data
public class WashDetailTo {

    /**
     * goods_list : [{"total_price":28,"service_name":"卫衣","service_num":2}]
     * customer_info : {"telphone":"19920205994","customer":"漫威"}
     * order_info : {"delivery_address":"和达高科生命科技园14楼1403","order_button":8,"deposit_address":"和达高科生命科技园14楼1403","deposit_wardrobe_name":"和达高科5号智能柜","order_time":"2019-05-25","status_txt":"已存入，待取回","delivery_wardrobe_no":"201904261357","deposit_wardrobe_no":"201904261357","button_list":{"pay_btn":0,"smqh_btn":1,"qxdd_btn":0,"smch_btn":0,"qpj_btn":0},"order_amount":"28.00","pickup_time":"2019-05-25","order_id":1442,"delivery_wardrobe_name":"和达高科5号智能柜","order_sn":"W190525044500793","remarks":"科"}
     */
    private List<Goods_listEntity> goods_list;
    private Customer_infoEntity customer_info;
    private Order_infoEntity order_info;


@Data
    public class Goods_listEntity {
        /**
         * total_price : 28
         * service_name : 卫衣
         * service_num : 2
         */
        private String total_price;
        private String service_name;
        private int service_num;


    }
@Data
    public class Customer_infoEntity {
        /**
         * telphone : 19920205994
         * customer : 漫威
         */
        private String telphone;
        private String customer;

    }
@Data
    public class Order_infoEntity {
        /**
         * delivery_address : 和达高科生命科技园14楼1403
         * order_button : 8
         * deposit_address : 和达高科生命科技园14楼1403
         * deposit_wardrobe_name : 和达高科5号智能柜
         * order_time : 2019-05-25
         * status_txt : 已存入，待取回
         * delivery_wardrobe_no : 201904261357
         * deposit_wardrobe_no : 201904261357
         * button_list : {"pay_btn":0,"smqh_btn":1,"qxdd_btn":0,"smch_btn":0,"qpj_btn":0}
         * order_amount : 28.00
         * pickup_time : 2019-05-25
         * order_id : 1442
         * delivery_wardrobe_name : 和达高科5号智能柜
         * order_sn : W190525044500793
         * remarks : 科
         */
        private String delivery_address;
        private int order_button;
        private String deposit_address;
        private String deposit_wardrobe_name;
        private String order_time;
        private String status_txt;
        private String delivery_wardrobe_no;
        private String deposit_wardrobe_no;
        private Button_listEntity button_list;
        private String order_amount;
        private String pickup_time;
        private int order_id;
        private String delivery_wardrobe_name;
        private String order_sn;
        private String remarks;


        @Data

        public class Button_listEntity {
            /**
             * pay_btn : 0
             * smqh_btn : 1
             * qxdd_btn : 0
             * smch_btn : 0
             * qpj_btn : 0
             */
            private int pay_btn;
            private int smqh_btn;
            private int qxdd_btn;
            private int smch_btn;
            private int qpj_btn;


        }
    }
}
