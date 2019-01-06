package hzxmkuar.com.applibrary.domain;

import lombok.Data;

/**
 * Created by Administrator on 2018/9/1.
 */
@Data
public class BaseParam {
    private long time=System.nanoTime();
    private String hash;
    private String apiId="289572c62e642b641e15ff2744aa3308";
    private String terminal="3";
    private int uid;
    private String hashid;





}
