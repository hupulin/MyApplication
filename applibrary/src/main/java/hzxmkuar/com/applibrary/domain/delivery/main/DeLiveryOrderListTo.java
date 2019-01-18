package hzxmkuar.com.applibrary.domain.delivery.main;

import java.util.List;

import lombok.Data;

/**
 * Created by Administrator on 2019/1/14.
 */
@Data
public class DeLiveryOrderListTo {
    /**
     * total : 13
     * lists : [{"address":"西溪花园","expect_delivery_time":"2019年01月15日 16:13","button_list":{"tzqh_btn":1,"smkx_btn":1},"order_amount":0,"wardrobe_title":"西溪花园1号柜","order_id":1140,"order_sn":"W20190109015926642","status_txt":"已清洗"},{"address":"西溪花园","expect_delivery_time":"2019年01月15日 16:13","button_list":{"tzqh_btn":1,"smkx_btn":1},"order_amount":0,"wardrobe_title":"西溪花园1号柜","order_id":1139,"order_sn":"W20190109015344238","status_txt":"已清洗"},{"address":"西溪花园","expect_delivery_time":"2019年01月15日 16:13","button_list":{"tzqh_btn":1,"smkx_btn":1},"order_amount":0,"wardrobe_title":"西溪花园1号柜","order_id":1138,"order_sn":"W20190109012034799","status_txt":"已清洗"},{"address":"城西银泰","expect_delivery_time":"2019年01月15日 16:13","button_list":{"tzqh_btn":1,"smkx_btn":1},"order_amount":10,"wardrobe_title":"西湖1号柜","order_id":1137,"order_sn":"W20190107014613115","status_txt":"已清洗"},{"address":"城西银泰","expect_delivery_time":"2019年01月15日 16:13","button_list":{"tzqh_btn":1,"smkx_btn":1},"order_amount":10,"wardrobe_title":"西湖1号柜","order_id":1136,"order_sn":"W20190107014508445","status_txt":"已清洗"},{"address":"城西银泰","expect_delivery_time":"2019年01月15日 16:13","button_list":{"tzqh_btn":1,"smkx_btn":1},"order_amount":10,"wardrobe_title":"西湖1号柜","order_id":1135,"order_sn":"W20190107014356119","status_txt":"已清洗"},{"address":"城西银泰","expect_delivery_time":"2019年01月15日 16:13","button_list":{"tzqh_btn":1,"smkx_btn":1},"order_amount":10,"wardrobe_title":"西湖1号柜","order_id":1134,"order_sn":"W20190107014326494","status_txt":"已清洗"},{"address":"城西银泰","expect_delivery_time":"2019年01月15日 16:13","button_list":{"tzqh_btn":1,"smkx_btn":1},"order_amount":10,"wardrobe_title":"西湖1号柜","order_id":1133,"order_sn":"W20190107014218957","status_txt":"已清洗"},{"address":"城西银泰","expect_delivery_time":"2019年01月15日 16:13","button_list":{"tzqh_btn":1,"smkx_btn":1},"order_amount":10,"wardrobe_title":"西湖1号柜","order_id":1132,"order_sn":"W20190107014046954","status_txt":"已清洗"},{"address":"城西银泰","expect_delivery_time":"2019年01月15日 16:13","button_list":{"tzqh_btn":1,"smkx_btn":1},"order_amount":10,"wardrobe_title":"西湖1号柜","order_id":1131,"order_sn":"W20190107013832534","status_txt":"已清洗"}]
     * limit : 10
     * page : 1
     * remainder : 3
     */
    private int total;
    private List<ListsEntity> lists;
    private int limit;
    private int page;
    private int remainder;

    @Data
    public class ListsEntity {
        /**
         * address : 西溪花园
         * expect_delivery_time : 2019年01月15日 16:13
         * button_list : {"tzqh_btn":1,"smkx_btn":1}
         * order_amount : 0
         * wardrobe_title : 西溪花园1号柜
         * order_id : 1140
         * order_sn : W20190109015926642
         * status_txt : 已清洗
         */
        private String address;
        private String expect_delivery_time;
        private Button_listEntity button_list;
        private int order_amount;
        private String wardrobe_title;
        private int order_id;
        private String order_sn;
        private String status_txt;


        @Data
        public class Button_listEntity {
            /**
             * tzqh_btn : 1
             * smkx_btn : 1
             */
            private int tzqh_btn;
            private int smkx_btn;
            private int fkgz_btn;
            private int qrqh_btn;
            private int fkth_btn;

        }
    }
//
//
//    /**
//     * total : 2
//     * lists : [{"address":"杭州市江干区下沙路699号","expect_delivery_time":"2019年01月15日 13:41","order_amount":98,"wardrobe_title":"1号智能柜","order_id":1,"order_sn":"WS2019JanTue014142353","status_txt":"已清洗"},{"address":"杭州市江干区下沙路699号","expect_delivery_time":"2019年01月15日 13:41","order_amount":98,"wardrobe_title":"1号智能柜","order_id":2,"order_sn":"WS2019JanTue014142546","status_txt":"已清洗"},{"address":"杭州市江干区下沙路699号","expect_delivery_time":"2019年01月15日 13:41","order_amount":98,"wardrobe_title":"1号智能柜","order_id":3,"order_sn":"WS2019JanTue014142932","status_txt":"已清洗"}]
//     * limit : 10
//     * page : 1
//     * remainder : 0
//     */
//    private int total;
//    private List<ListsEntity> lists;
//    private int limit;
//    private int page;
//    private int remainder;
//
//
//    @Data
//    public class ListsEntity {
//        /**
//         * address : 杭州市江干区下沙路699号
//         * expect_delivery_time : 2019年01月15日 13:41
//         * order_amount : 98
//         * wardrobe_title : 1号智能柜
//         * order_id : 1
//         * order_sn : WS2019JanTue014142353
//         * status_txt : 已清洗
//         */
//        private String address;
//        private String expect_delivery_time;
//        private int order_amount;
//        private String wardrobe_title;
//        private int order_id;
//        private String order_sn;
//        private String status_txt;
//
//    }

}
