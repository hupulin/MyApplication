package hzxmkuar.com.applibrary.domain.delivery.news;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * Created by Administrator on 2019/1/16.
 */
@Data
public class NewsTo {
    /**
     * total : 7
     * lists : [{"is_read":0,"dateline":"2018年12月10日","id":114,"title":"商家提现成功通知","content":"【我俫洗】亲，提现申请提交成功！预计2-3个工作日到账"},{"is_read":1,"dateline":"2018年12月10日","id":113,"title":"商家提现成功通知","content":"【我俫洗】亲，提现申请提交成功！预计2-3个工作日到账"},{"is_read":1,"dateline":"2018年12月10日","id":111,"title":"确认完成通知","content":"【我俫洗】订单已确认完成！订单号：INQ181210141701154"},{"is_read":0,"dateline":"2018年12月10日","id":110,"title":"结束确认通知","content":"【我俫洗】服务已完成，请确认！订单号：INQ181210141701154"},{"is_read":0,"dateline":"2018年12月10日","id":109,"title":"下单通知","content":"【我俫洗】您的订单已下单成功（订单号：INQ181210141701154），商家会在30分钟内予以确认，请耐心等待！"},{"is_read":0,"dateline":"2018年12月10日","id":108,"title":"下单确认通知","content":"【我俫洗】亲，有客户下单啦！快去确认接单吧。您还有30分钟时间进行确认。"},{"is_read":0,"dateline":"2018年12月10日","id":107,"title":"商户报价通知","content":"【我俫洗】有商家报价啦，快去看看吧"}]
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
    public static class ListsEntity implements Serializable {
        /**
         * is_read : 0
         * dateline : 2018年12月10日
         * id : 114
         * title : 商家提现成功通知
         * content : 【我俫洗】亲，提现申请提交成功！预计2-3个工作日到账
         */
        private int is_read;
        private String dateline;
        private int id;
        private String title;
        private String content;


    }
}
