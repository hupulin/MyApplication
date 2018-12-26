package com.xmkj.washmall.main.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.View;
import android.widget.GridLayout;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.xmkj.washmall.R;
import com.xmkj.washmall.databinding.MallBannerItemBinding;

import java.util.List;

import hzxmkuar.com.applibrary.domain.main.MainHomeAdTo;
import hzxmkuar.com.applibrary.domain.main.MallBannerTo;
import rx.Observable;


/**
 * Created by xzz on 2017/6/25.
 **/

public class MallBannerView implements Holder<List<MallBannerTo>> {

    private GridLayout gridLayout;

    @Override
    public View createView(Context context) {
        gridLayout = new GridLayout(context);

       gridLayout.setColumnCount(4);
        return gridLayout;
    }

    @Override
    public void UpdateUI(Context context, int position, List<MallBannerTo> menuList ) {
        Observable.from(menuList).subscribe(mallBannerTo -> {
            View mView=View.inflate(context, R.layout.mall_banner_item,null);
            MallBannerItemBinding binding= DataBindingUtil.bind(mView);
           binding.name.setText(mallBannerTo.getName());
            Glide.with(context).load(mallBannerTo.getImageUrl()).into(binding.image);
            gridLayout.addView(mView);
        });

    }
}
