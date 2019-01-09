package hzxmkuar.com.applibrary.domain.wash;

import java.util.List;

import lombok.Data;

/**
 * Created by Administrator on 2019/1/9.
 */
@Data
public class WashInfoTo {

    /**
     * id : 2
     * service_img : http://xmap18100040.php.hzxmnet.com/uploads/picture/2018-09-14/a7a19e26e41cdeab700cd157971b2b7b.png
     * service_name : 洗鞋
     * level : 1
     * list2 : []
     */

    private int id;
    private String service_img;
    private String service_name;
    private String sweet_price;
    private Double price;
    private int level;
    private List<WashInfoTo> list2;
    private List<WashInfoTo> list3;
    private boolean select;
    private int num;


}
