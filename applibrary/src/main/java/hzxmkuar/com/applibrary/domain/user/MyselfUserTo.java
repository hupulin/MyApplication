package hzxmkuar.com.applibrary.domain.user;

import android.text.TextUtils;

import java.io.Serializable;

import lombok.Data;

/**
 * Created by Administrator on 2018/10/15.
 */
@Data
public class MyselfUserTo implements Serializable{


    private UserInfoBean user_info;
    private MyWashOrderBean my_wash_order;
    private MyMallOrderBean my_mall_order;
    private MoreServiceBean more_service;
    private MemberLevels member_levels;


    @Data
    public static class UserInfoBean implements Serializable{
        /**
         * uid : 2
         * face_url :
         * is_member : 0
         * user_tag : 普通会员
         * account : 0.00
         * score : 0.00
         * collection_num : 0
         * coupon_num : 0
         * username : Coo.Ddk
         */

        private int uid;
        private String face_url;
        private int is_member;
        private int member_level;
        private String user_tag;
        private String account;
        private String score;
        private String collection_num;
        private String coupon_num;
        private String username;
        private String privilege;
        private String invite_code;

        public double getDiscount(){
            double discount=1;
            if (TextUtils.isEmpty(privilege)||"0".equals(privilege))
              return 1;

            discount=Double.valueOf(privilege)/10;

            return discount;

        }

    }

    @Data
    public static class MyWashOrderBean implements Serializable{
        /**
         * order1 : 0
         * order2 : 0
         * order3 : 0
         * order4 : 0
         */

        private String order1;
        private String order2;
        private String order3;
        private String order4;


    }

    @Data
    public static class MyMallOrderBean implements Serializable{
        /**
         * order1 : 0
         * order2 : 0
         * order3 : 0
         * order4 : 0
         */

        private String order1;
        private String order2;
        private String order3;
        private String order4;


    }

    @Data
    public static class MoreServiceBean implements Serializable{
        /**
         * kf_tel : 13516717556
         */

        private String kf_tel;

    }
    @Data
    public static class MemberLevels implements Serializable{
        /**"member_levels": {
         "baiyin": 9,
         "huangjin": 8,
         "zuanshi": 7
         */

        private String baiyin;
        private String huangjin;
        private String zuanshi;

    }



}
