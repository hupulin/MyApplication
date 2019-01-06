package hzxmkuar.com.applibrary;

import javax.xml.parsers.SAXParser;

import lombok.Data;

/**
 * Created by Administrator on 2019/1/3.
 */
@Data
public class LoginParam {
    private String account;
    private String password;
    private String appKey;
    private String sign;
    private String timestamp;
}
