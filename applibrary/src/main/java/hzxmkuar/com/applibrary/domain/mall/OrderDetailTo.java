package hzxmkuar.com.applibrary.domain.mall;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * Created by xzz on 2019/5/1.
 */
@Data
public class OrderDetailTo implements Serializable {
    private int order_id;
    private String order_sn;
    private String address;
    private String add_time;
    private String consignee;
    private String telephone;
    private String pay_time;
    private String send_time;
    private String finish_time;
    private String remarks;
    private String status_txt;
    private int new_status;
    private ButtonListBean button_list;
    private SettlementInfoBean settlement_info;
    private List<GoodsListBean> goods_list;


    @Data
    public static class ButtonListBean {
        /**
         * qxdd_btn : 1
         * fk_btn : 1
         * cfh_btn : 0
         * qrsh_btn : 0
         * qpj_btn : 0
         */

        private int qxdd_btn;
        private int fk_btn;
        private int cfh_btn;
        private int qrsh_btn;
        private int qpj_btn;


    }
@Data
    public static class SettlementInfoBean {
        /**
         * distribution : 快递
         * goods_amount : 0.00
         * express_amount : 0
         * order_amount : 267.00
         * total_amount : 267.00
         */

        private String distribution;
        private String goods_amount;
        private int express_amount;
        private String order_amount;
        private String total_amount;


    }

    @Data
    public static class GoodsListBean {
        /**
         * goods_id : 1
         * spec_id : 21
         * goods_name : Midea/美的 MK-H415E2J食品级304不锈钢防烫电热水壶开水煲烧水壶
         * spec_name : 玫瑰香
         * goods_num : 2
         * goods_price : 0.00
         * spec_image : 4984
         */

        private int goods_id;
        private int spec_id;
        private String goods_name;
        private String spec_name;
        private int goods_num;
        private String goods_price;
        private String spec_image;


    }
}
