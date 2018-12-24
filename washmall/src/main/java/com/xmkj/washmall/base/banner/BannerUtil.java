package com.xmkj.washmall.base.banner;

import com.bigkoo.convenientbanner.ConvenientBanner;

import java.util.List;



/**
 * Created by xzz on 2017/6/25.
 **/

public class BannerUtil {
    public static void setBanner(ConvenientBanner banner, List<String> adList, int loadSrc) {

        banner.setPages(() -> new BannerHolderView(loadSrc), adList);
        //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
        banner.setOnItemClickListener(position -> {

        });
    }







    private void setBannerClick(ConvenientBanner banner){

    }
}
