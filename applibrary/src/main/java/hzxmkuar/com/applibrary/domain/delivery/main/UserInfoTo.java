package hzxmkuar.com.applibrary.domain.delivery.main;

import java.util.List;

import lombok.Data;

/**
 * Created by Administrator on 2019/1/16.
 */
@Data
public class UserInfoTo {

    /**
     * user_info : {"uid":123,"nickname":"18069765024","face_url":"http://xmap18100040.php.hzxmnet.com/uploads/picture/2019-01-15/f03fd69622980af7a6de76a14414e3d5.jpg"}
     * order_statistics : {"yesterday":{"order_number":0,"order_amount":0},"last_month":{"order_number":0,"order_amount":0},"month":{"order_number":0,"order_amount":0},"today":{"order_number":0,"order_amount":0}}
     * latest_order : [{"address":"杭州市江干区下沙路699号","expect_delivery_time":"2019年01月15日 16:38","order_amount":299,"wardrobe_title":"1号智能柜","order_id":1,"order_sn":"W238498243791356","status_txt":"已完成"}]
     */
    private User_infoEntity user_info;
    private Order_statisticsEntity order_statistics;
    private List<Latest_orderEntity> latest_order;
    private String hashid;
    private int uid;

    @Data
    public class User_infoEntity {
        /**
         * uid : 123
         * nickname : 18069765024
         * face_url : http://xmap18100040.php.hzxmnet.com/uploads/picture/2019-01-15/f03fd69622980af7a6de76a14414e3d5.jpg
         */
        private int uid;
        private String nickname;
        private String face_url;

    }

    @Data
    public class Order_statisticsEntity {
        /**
         * yesterday : {"order_number":0,"order_amount":0}
         * last_month : {"order_number":0,"order_amount":0}
         * month : {"order_number":0,"order_amount":0}
         * today : {"order_number":0,"order_amount":0}
         */
        private YesterdayEntity yesterday;
        private Last_monthEntity last_month;
        private MonthEntity month;
        private TodayEntity today;

        @Data
        public class YesterdayEntity {
            /**
             * order_number : 0
             * order_amount : 0
             */
            private int order_number;
            private int order_amount;
        }
        @Data
        public class Last_monthEntity {
            /**
             * order_number : 0
             * order_amount : 0
             */
            private int order_number;
            private int order_amount;


        }
        @Data
        public class MonthEntity {
            /**
             * order_number : 0
             * order_amount : 0
             */
            private int order_number;
            private int order_amount;


        }
        @Data
        public class TodayEntity {
            /**
             * order_number : 0
             * order_amount : 0
             */
            private int order_number;
            private int order_amount;


        }
    }
    @Data
    public class Latest_orderEntity {
        /**
         * address : 杭州市江干区下沙路699号
         * expect_delivery_time : 2019年01月15日 16:38
         * order_amount : 299
         * wardrobe_title : 1号智能柜
         * order_id : 1
         * order_sn : W238498243791356
         * status_txt : 已完成
         *
         *   {
         *                 "order_id": 1155,
         *                 "order_sn": "W20190421112847191",
         *                 "deposit_wardrobe_no": "100015",
         *                 "deposit_wardrobe_name": "未知",
         *                 "deposit_address": "未知",
         *                 "delivery_wardrobe_no": "100015",
         *                 "delivery_wardrobe_name": "未知",
         *                 "delivery_address": "未知",
         *                 "order_time": "2019-04-21",
         *                 "pickup_time": "2019-05-01",
         *                 "order_amount": "38.00",
         *                 "remarks": "haha",
         *                 "status_txt": "已清洗"
         *             }
         */

        private int order_amount;
        private int order_id;
        private String order_sn;
        private String status_txt;
        private String remarks;
        private String deposit_wardrobe_no;
        private String deposit_wardrobe_name;
        private String deposit_address;
        private String delivery_wardrobe_no;
        private String  delivery_wardrobe_name;
        private String  delivery_address;
        private String  order_time;
        private String  pickup_time;
//        private String address;
//        private String expect_delivery_time;
//        private String wardrobe_title;

    }
}
