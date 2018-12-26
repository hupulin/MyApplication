package hzxmkuar.com.applibrary.domain.wash;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * Created by Administrator on 2018/12/26.
 */
@Data
public class WashTypeTo implements Serializable{
    private String title;
    private List<String>tag;
}
