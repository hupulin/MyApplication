package hzxmkuar.com.applibrary.domain.order;

import lombok.Data;

/**
 * Created by Administrator on 2018/12/26.
 */
@Data
public class MallOrderTo {
    private String name="蓝月亮洗衣液薰衣草香家庭装";
    private int type;
    private String imageUrl="http://118.190.201.28:8080/img/order_goods.png";
    private String remark="我是处女座的！";
    private String num="2";
    private String money="120";
    private String orderNo="123456789";
    private String specification="瓶装";

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
            return "付款";
        if (type==2)
            return "催发货";
        if (type==3)
            return "确认收货";
        if (type==4)
            return "已完成";
        return "状态";
    }
}
