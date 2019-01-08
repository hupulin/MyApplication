package hzxmkuar.com.applibrary.domain.integral;

import lombok.Data;

/**
 * Created by Administrator on 2019/1/8.
 */
@Data
public class IntegralOrderInfoTo {

    /**
     * address_info : {"address_id":15,"consignee":"Finder","telephone":"13738084631","final_address":"933934936天街123号"}
     * goods_list : {"goods_id":17,"goods_name":"300元话费直充","spec_name":"默认","goods_num":1,"goods_price":30000,"goods_image":"http://xmap18100040.php.hzxmnet.com/uploads/picture/2018-11-21/1631db012bda16c0f989dbfc505cbb8e.jpg"}
     * settlement_info : {"distribution":"快递","order_amount":30000}
     */

    private AddressInfoBean address_info;
    private GoodsListBean goods_list;
    private SettlementInfoBean settlement_info;


@Data
    public static class AddressInfoBean {
        /**
         * address_id : 15
         * consignee : Finder
         * telephone : 13738084631
         * final_address : 933934936天街123号
         */

        private int address_id;
        private String consignee;
        private String telephone;
        private String final_address;


    }
@Data
    public static class GoodsListBean {
        /**
         * goods_id : 17
         * goods_name : 300元话费直充
         * spec_name : 默认
         * goods_num : 1
         * goods_price : 30000
         * goods_image : http://xmap18100040.php.hzxmnet.com/uploads/picture/2018-11-21/1631db012bda16c0f989dbfc505cbb8e.jpg
         */

        private int goods_id;
        private String goods_name;
        private String spec_name;
        private int goods_num;
        private int goods_price;
        private String goods_image;


    }
@Data
    public static class SettlementInfoBean {
        /**
         * distribution : 快递
         * order_amount : 30000
         */

        private String distribution;
        private int order_amount;


    }
}
