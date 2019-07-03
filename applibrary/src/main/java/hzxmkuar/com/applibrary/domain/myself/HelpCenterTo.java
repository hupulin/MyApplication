package hzxmkuar.com.applibrary.domain.myself;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * Created by 1ONE on 2019/5/26.
 */
@Data
public class HelpCenterTo implements Serializable{

    /**
     * list2 : [{"doc_title":"已确认收货的订单失效了？","id":1}]
     * cate_name : 关于订单
     * cate_id : 1
     * cate_img : http://xmap18100040.php.hzxmnet.com/uploads/picture/2019-05-09/402390c87d0e7d0a95a9a3c74dabe3e5.png
     */
    private List<List2Entity> list2;
    private String cate_name;
    private int cate_id;
    private String cate_img;


    @Data
    public class List2Entity implements Serializable{
        /**
         * doc_title : 已确认收货的订单失效了？
         * id : 1
         */
        private String doc_title;
        private int id;


    }
}
