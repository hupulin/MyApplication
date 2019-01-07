package hzxmkuar.com.applibrary.domain.order;

import java.io.Serializable;

import lombok.Data;

/**
 * Created by Administrator on 2019/1/7.
 */
@Data
public class OrderResultTo implements Serializable {
    private int order_id;
    private String total_amount;
}
