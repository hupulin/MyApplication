package hzxmkuar.com.applibrary.domain.mall;

import java.util.List;

import lombok.Data;

/**
 * Created by Administrator on 2018/12/27.
 */
@Data
public class GoodsDetailTo {


    /**
     * goods_id : 17
     * goods_name : 蜗牛
     * goods_image : http://xmap18100040.php.hzxmnet.com/uploads/picture/2018-12-17/7a0a90cc71b9e7c8e8c3cae872ebd5c2.png
     * goods_price : 998.00
     * goods_desc : 蜗牛蜗牛蜗牛蜗牛蜗牛
     * goods_content : [{"img_url":"http://xmap18100040.php.hzxmnet.com/uploads/picture/2018-12-17/425f59a25f94e347793e000bb3f1e55d.png"}]
     */

    private int goods_id;
    private String goods_name;
    private String goods_image;
    private String goods_price;
    private String goods_desc;
    private List<GoodsContentBean> goods_content;



    @Data
    public static class GoodsContentBean {
        /**
         * img_url : http://xmap18100040.php.hzxmnet.com/uploads/picture/2018-12-17/425f59a25f94e347793e000bb3f1e55d.png
         */

        private String img_url;


    }
}
