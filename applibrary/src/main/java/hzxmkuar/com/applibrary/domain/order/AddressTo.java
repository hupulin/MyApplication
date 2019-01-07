package hzxmkuar.com.applibrary.domain.order;

import java.io.Serializable;

import lombok.Data;

/**
 * Created by Administrator on 2019/1/7.
 */
@Data
public class AddressTo implements Serializable{

    /**
     * id : 21
     * uid : 2
     * consignee : 丁爷爷
     * telephone : 13738084312
     * province : 2
     * city : 3
     * area : 4
     * address : 嘻哈街354号
     * add_time : 1545232725
     * update_time : 1545233172
     * is_default : 2
     * finaladdress : 234嘻哈街354号
     */

    private int id;
    private int uid;
    private String consignee;
    private String telephone;
    private String province;
    private String city;
    private String area;
    private String address;
    private int add_time;
    private int update_time;
    private int is_default;
    private String finaladdress;


}
