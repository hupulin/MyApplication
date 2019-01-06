package hzxmkuar.com.applibrary.domain.tecent;

import lombok.Data;

/**
 * Created by Administrator on 2019/1/5.
 */
@Data
public class QQResultTo {

    /**
     * ret : 0
     * openid : 5059B9443DDF2135A86756A200BD8952
     * access_token : 7638AE5EBCA8DFC07776134375820A1E
     * pay_token : 3939E1B765D9B21708F6CBDA4AC9F0DA
     * expires_in : 7776000
     * pf : desktop_m_qq-10000144-android-2002-
     * pfkey : 7cdbcbdc26174695ec46797d53aa7617
     * msg :
     * login_cost : 79
     * query_authority_cost : 152
     * authority_cost : 0
     * expires_time : 1554478663910
     */

    private int ret;
    private String openid;
    private String access_token;
    private String pay_token;
    private int expires_in;
    private String pf;
    private String pfkey;
    private String msg;
    private int login_cost;
    private int query_authority_cost;
    private int authority_cost;
    private long expires_time;


}
