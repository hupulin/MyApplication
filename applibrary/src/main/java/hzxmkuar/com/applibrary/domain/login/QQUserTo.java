package hzxmkuar.com.applibrary.domain.login;

import lombok.Data;

/**
 * Created by Administrator on 2019/1/6.
 */
@Data
public class QQUserTo {

    /**
     * ret : 0
     * msg :
     * is_lost : 0
     * nickname : 东篱残阳
     * gender : 男
     * province : 湖北
     * city : 武汉
     * year : 1992
     * constellation :
     * figureurl : http://qzapp.qlogo.cn/qzapp/1108049746/5059B9443DDF2135A86756A200BD8952/30
     * figureurl_1 : http://qzapp.qlogo.cn/qzapp/1108049746/5059B9443DDF2135A86756A200BD8952/50
     * figureurl_2 : http://qzapp.qlogo.cn/qzapp/1108049746/5059B9443DDF2135A86756A200BD8952/100
     * figureurl_qq_1 : http://thirdqq.qlogo.cn/qqapp/1108049746/5059B9443DDF2135A86756A200BD8952/40
     * figureurl_qq_2 : http://thirdqq.qlogo.cn/qqapp/1108049746/5059B9443DDF2135A86756A200BD8952/100
     * is_yellow_vip : 0
     * vip : 0
     * yellow_vip_level : 0
     * level : 0
     * is_yellow_year_vip : 0
     */

    private int ret;
    private String msg;
    private int is_lost;
    private String nickname;
    private String gender;
    private String province;
    private String city;
    private String year;
    private String constellation;
    private String figureurl;
    private String figureurl_1;
    private String figureurl_2;
    private String figureurl_qq_1;
    private String figureurl_qq_2;
    private String is_yellow_vip;
    private String vip;
    private String yellow_vip_level;
    private String level;
    private String is_yellow_year_vip;

}
