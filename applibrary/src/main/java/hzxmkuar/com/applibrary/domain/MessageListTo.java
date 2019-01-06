package hzxmkuar.com.applibrary.domain;


import java.util.List;

import lombok.Data;

/**
 * Created by Administrator on 2018/9/1.
 */
@Data
public class MessageListTo<T> {

    /**
     * Code : 000000
     * Msg : 返回成功
     * Time : 2018-08-30 16:08:20
     * ApiUrl : http://xmap18070031.php.hzxmnet.com/api/Sms/sendcode
     * Data : {"mobile":"13738084633","sms_code":"637442"}
     */

    private int Code;
    private String Msg;
    private String Time;
    private String ApiUrl;
    private ListTo Data ;

    private class ListTo<T>{
        private List<T>lists;
    }

    public List<T>getDataList(){
        if (Data==null)
            return null;
        return Data.lists;
    }

}
