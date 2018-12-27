package hzxmkuar.com.applibrary.domain.mall;

import lombok.Data;

/**
 * Created by Administrator on 2018/12/27.
 */
@Data
public class SpecificationTo {
    private String name;
    private String price;

    public SpecificationTo(String name, String price) {
        this.name = name;
        this.price = price;
    }
}
