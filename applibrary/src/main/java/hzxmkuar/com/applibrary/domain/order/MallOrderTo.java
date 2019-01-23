package hzxmkuar.com.applibrary.domain.order;

import java.util.List;

import lombok.Data;

/**
 * Created by Administrator on 2018/12/26.
 */
@Data
public class MallOrderTo {

    /**
     * id : 28
     * status_txt : 等待付款
     * new_status : 1
     * order_sn : BG190107215002575
     * total_amount : 86.00
     * remarks :
     * goods_list : [{"goods_id":35,"spec_id":47,"goods_name":"黑人牙膏超白竹炭190g美白牙齿防蛀去口臭去牙渍口气清新官方正品","spec_name":"190g","spec_image":5279,"goods_num":1}]
     * button_list : {"fk_btn":1,"cfh_btn":0,"qrsh_btn":0}
     */

    private int id;
    private String status_txt;
    private int new_status;
    private String order_sn;
    private String total_amount;
    private String remarks;
    private ButtonListBean button_list;
    private List<GoodsListBean> goods_list;


    @Data
    public static class ButtonListBean {

        private int fk_btn;
        private int cfh_btn;
        private int qrsh_btn;


    }

    @Data
    public static class GoodsListBean {
        /**
         * goods_id : 35
         * spec_id : 47
         * goods_name : 黑人牙膏超白竹炭190g美白牙齿防蛀去口臭去牙渍口气清新官方正品
         * spec_name : 190g
         * spec_image : 5279
         * goods_num : 1
         */

        private int goods_id;
        private int spec_id;
        private String goods_name;
        private String spec_name;
        private String spec_image;
        private int goods_num;

    }



    public String getPayStr(){
        if (new_status==1)
            return "付款";
        if (new_status==2)
            return "催发货";
        if (new_status==3)
            return "确认收货";
        if (new_status==4)
            return "已完成";
        return "状态";
    }
}
