package hzxmkuar.com.applibrary.domain.integral;

import java.io.Serializable;

import lombok.Data;

/**
 * Created by Administrator on 2019/1/8.
 */
@Data
public class IntegralGoodsTo implements Serializable{

    /**
     * goods_id : 21
     * goods_name : 10元话费直充
     * goods_image : http://xmap18100040.php.hzxmnet.com/uploads/picture/2018-11-21/9e786d91782106b034f50fa3caa883d6.jpg
     * goods_integral : 1000
     */

    private int goods_id;
    private String goods_name;
    private String goods_image;
    private int goods_integral;


}
