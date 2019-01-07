package hzxmkuar.com.applibrary.domain.order;

import java.util.List;

import lombok.Data;

/**
 * Created by Administrator on 2019/1/7.
 */
@Data
public class ConfirmOrderInfoTo  {

    /**
     * address_info : {"address_id":0,"consignee":"","telephone":"","final_address":""}
     * goods_list : [{"goods_id":35,"spec_id":47,"goods_name":"黑人牙膏超白竹炭190g美白牙齿防蛀去口臭去牙渍口气清新官方正品","spec_name":"190g","goods_num":1,"goods_price":86,"spec_image":"http://xmap18100040.php.hzxmnet.com/uploads/picture/2019-01-07/78281d5c4de81134da0f7050148da25f.jpg"}]
     * settlement_info : {"distribution":"快递","goods_amount":86,"express_amount":0,"order_amount":86}
     */

    private AddressInfoBean address_info;
    private SettlementInfoBean settlement_info;
    private List<GoodsListBean> goods_list;

    @Data
    public static class AddressInfoBean {
        /**
         * address_id : 0
         * consignee :
         * telephone :
         * final_address :
         */

        private int address_id;
        private String consignee;
        private String telephone;
        private String final_address;


    }
@Data
    public static class SettlementInfoBean {
        /**
         * distribution : 快递
         * goods_amount : 86
         * express_amount : 0
         * order_amount : 86
         */

        private String distribution;
        private String goods_amount;
        private String express_amount;
        private String order_amount;


    }
@Data
    public static class GoodsListBean {
        /**
         * goods_id : 35
         * spec_id : 47
         * goods_name : 黑人牙膏超白竹炭190g美白牙齿防蛀去口臭去牙渍口气清新官方正品
         * spec_name : 190g
         * goods_num : 1
         * goods_price : 86
         * spec_image : http://xmap18100040.php.hzxmnet.com/uploads/picture/2019-01-07/78281d5c4de81134da0f7050148da25f.jpg
         */

        private int goods_id;
        private int spec_id;
        private String goods_name;
        private String spec_name;
        private int goods_num;
        private int goods_price;
        private String spec_image;


    }
}
