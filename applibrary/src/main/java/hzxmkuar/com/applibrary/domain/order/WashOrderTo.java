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




//    public String getStatueStr(){
//        if (type==1)
//            return "待处理";
//        if (type==2)
//            return "待发货";
//        if (type==3)
//            return "已发货";
//        if (type==4)
//            return "已完成";
//        return "状态";
//    }
//
//    public String getPayStr(){
//        if (type==1)
//            return "去评价";
//        if (type==2)
//            return "催发货";
//        if (type==3)
//            return "确认收货";
//        if (type==4)
//            return "扫码取货";
//        return "取消订单";
//    }
}
