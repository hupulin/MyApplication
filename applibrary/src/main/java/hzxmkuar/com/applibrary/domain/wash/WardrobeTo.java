package hzxmkuar.com.applibrary.domain.wash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import hzxmkuar.com.applibrary.domain.main.MainWardrobeTo;
import lombok.Data;

/**
 * Created by Administrator on 2018/12/26.
 */
@Data
public class WardrobeTo {
    private String address="浙江省杭州市江干区下沙路699号";
    private String name="设备：智能柜";
    private String num="数量：  1";
    private String imageUrl="http://118.190.201.28:8080/img/wardrobe_img.png";
    private List<String>tagList=new ArrayList<>(Arrays.asList("全部","01层","02层","03层","04层","05层","06层","07层"));
    private List<MainWardrobeTo>wardrobeList;
}
