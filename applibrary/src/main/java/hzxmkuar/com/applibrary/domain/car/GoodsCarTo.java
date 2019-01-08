package hzxmkuar.com.applibrary.domain.car;

import lombok.Data;

/**
 * Created by Administrator on 2018/12/27.
 */
@Data
public class GoodsCarTo {


    /**
     * cart_id : 5
     * goods_id : 35
     * goods_name : 黑人牙膏超白竹炭190g美白牙齿防蛀去口臭去牙渍口气清新官方正品
     * spec_name : 190g
     * goods_num : 2
     * goods_price : 86.00
     * spec_image : http://xmap18100040.php.hzxmnet.com/uploads/picture/2019-01-07/78281d5c4de81134da0f7050148da25f.jpg
     */

    private int cart_id;
    private int goods_id;
    private String goods_name;
    private String spec_name;
    private int goods_num;
    private String goods_price;
    private String spec_image;
    private boolean select;


}
