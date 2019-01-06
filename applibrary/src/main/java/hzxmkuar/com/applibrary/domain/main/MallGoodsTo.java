package hzxmkuar.com.applibrary.domain.main;

import java.io.Serializable;

import lombok.Data;

/**
 * Created by Administrator on 2018/12/27.
 */
@Data
public class MallGoodsTo implements Serializable{

    /**
     * goods_id : 17
     * goods_name : 蜗牛
     * goods_image : http://xmap18100040.php.hzxmnet.com/uploads/picture/2018-12-17/7a0a90cc71b9e7c8e8c3cae872ebd5c2.png
     * goods_price : 998.00
     */

    private int goods_id;
    private String goods_name;
    private String goods_image;
    private String goods_price;


}
