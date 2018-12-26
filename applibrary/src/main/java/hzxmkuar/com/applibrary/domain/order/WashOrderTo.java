package hzxmkuar.com.applibrary.domain.order;

import lombok.Data;

/**
 * Created by Administrator on 2018/12/26.
 */
@Data
public class WashOrderTo {
    private int type;
    private String address="杭州市江干区下沙路699号";
    private String wardrobe="1号智能柜";
    private String orderNo="123456789";
    private String money="99.00";

    public String getStatueStr(){
        if (type==1)
            return "待处理";
        if (type==2)
            return "待发货";
        if (type==3)
            return "已发货";
        if (type==4)
            return "已完成";
        return "状态";
    }

    public String getPayStr(){
        if (type==1)
            return "去评价";
        if (type==2)
            return "催发货";
        if (type==3)
            return "确认收货";
        if (type==4)
            return "扫码取货";
        return "取消订单";
    }
}
