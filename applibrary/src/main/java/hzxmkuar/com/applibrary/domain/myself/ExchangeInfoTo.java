package hzxmkuar.com.applibrary.domain.myself;

import java.io.Serializable;

import lombok.Data;

/**
 * Created by xzz on 2019/4/29.
 */
@Data
public class ExchangeInfoTo implements Serializable {

    /**
     * package_id : 3
     * recharge_amount : 1000
     * give_amount : 350
     * tips : 送7折特权
     */

    private int package_id;
    private String recharge_amount;
    private String give_amount;
    private String tips;


}
