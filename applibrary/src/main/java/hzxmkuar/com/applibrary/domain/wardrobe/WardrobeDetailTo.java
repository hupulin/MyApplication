package hzxmkuar.com.applibrary.domain.wardrobe;

import java.util.List;

import lombok.Data;

/**
 * Created by Administrator on 2019/1/8.
 */
@Data
public class WardrobeDetailTo {

    /**
     * id : 10
     * wardrobe_name : 西湖1号柜
     * wardrobe_img : http://xmap18100040.php.hzxmnet.com/uploads/picture/2019-01-05/8a9bdf90e38faf43221e70108a4f116d.png
     * address : 城西银泰
     * grid_list : [{"grid_id":22,"grid_title":"2层9号柜","floor_no":2,"is_full":0},{"grid_id":23,"grid_title":"2层10号柜","floor_no":2,"is_full":0},{"grid_id":24,"grid_title":"2层11号柜","floor_no":2,"is_full":0},{"grid_id":25,"grid_title":"2层12号柜","floor_no":2,"is_full":0},{"grid_id":26,"grid_title":"2层13号柜","floor_no":2,"is_full":0},{"grid_id":27,"grid_title":"2层14号柜","floor_no":2,"is_full":0},{"grid_id":40,"grid_title":"2层27号柜","floor_no":2,"is_full":0}]
     */

    private int id;
    private String wardrobe_name;
    private String wardrobe_img;
    private String address;
    private String free_grid_num;
    private List<GridListBean> grid_list;


    @Data
    public static class GridListBean {
        /**
         * grid_id : 22
         * grid_title : 2层9号柜
         * floor_no : 2
         * is_full : 0
         */

        private int grid_id;
        private String grid_title;
        private int floor_no;
        private int is_full;


    }
}
