package hzxmkuar.com.applibrary.domain.delivery.main;

import hzxmkuar.com.applibrary.domain.BaseParam;
import lombok.Data;

@Data
public class FeedBackParam extends BaseParam {
    private int order_id;
    private String content;
    private String pic_id;
}

