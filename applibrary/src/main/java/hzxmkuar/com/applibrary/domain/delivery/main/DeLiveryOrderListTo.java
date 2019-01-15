package hzxmkuar.com.applibrary.domain.delivery.main;

import java.util.List;

import lombok.Data;

/**
 * Created by Administrator on 2019/1/14.
 */
@Data
public class DeLiveryOrderListTo {


    /**
     * total : 2
     * lists : [{"address":"杭州市江干区下沙路699号","expect_delivery_time":"2019年01月15日 13:41","order_amount":98,"wardrobe_title":"1号智能柜","order_id":1,"order_sn":"WS2019JanTue014142353","status_txt":"已清洗"},{"address":"杭州市江干区下沙路699号","expect_delivery_time":"2019年01月15日 13:41","order_amount":98,"wardrobe_title":"1号智能柜","order_id":2,"order_sn":"WS2019JanTue014142546","status_txt":"已清洗"},{"address":"杭州市江干区下沙路699号","expect_delivery_time":"2019年01月15日 13:41","order_amount":98,"wardrobe_title":"1号智能柜","order_id":3,"order_sn":"WS2019JanTue014142932","status_txt":"已清洗"}]
     * limit : 10
     * page : 1
     * remainder : 0
     */
    private int total;
    private List<ListsEntity> lists;
    private int limit;
    private int page;
    private int remainder;


    @Data
    public class ListsEntity {
        /**
         * address : 杭州市江干区下沙路699号
         * expect_delivery_time : 2019年01月15日 13:41
         * order_amount : 98
         * wardrobe_title : 1号智能柜
         * order_id : 1
         * order_sn : WS2019JanTue014142353
         * status_txt : 已清洗
         */
        private String address;
        private String expect_delivery_time;
        private int order_amount;
        private String wardrobe_title;
        private int order_id;
        private String order_sn;
        private String status_txt;

    }
}
