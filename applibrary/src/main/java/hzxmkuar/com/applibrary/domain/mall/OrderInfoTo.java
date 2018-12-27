package hzxmkuar.com.applibrary.domain.mall;

import hzxmkuar.com.applibrary.domain.main.ContactTo;
import lombok.Data;

/**
 * Created by Administrator on 2018/12/27.
 */
@Data
public class OrderInfoTo {
    private String imageUrl="http://118.190.201.28:8080/img/goods_image.png";
    private ContactTo contactTo=new ContactTo();
    private String goodsName="蓝月亮洗衣液薰衣草香家庭装";
    private String specification="瓶装";
    private String num="1";
    private String money="188.00";
    private String express="快递";
    private String expressMoney="12.00";
    private String allMoney="200.00";
    private String integral="1000分";

}
