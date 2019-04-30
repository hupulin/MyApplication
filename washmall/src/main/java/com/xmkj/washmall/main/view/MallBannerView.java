package com.xmkj.washmall.main.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.view.View;
import android.widget.GridLayout;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.xmkj.washmall.R;
import com.xmkj.washmall.databinding.MallBannerItemBinding;
import com.xmkj.washmall.integral.IntegralActivity;
import com.xmkj.washmall.mall.GoodsSortActivity;

import java.util.List;

import hzxmkuar.com.applibrary.domain.main.MainHomeAdTo;
import hzxmkuar.com.applibrary.domain.main.MallBannerTo;
import hzxmkuar.com.applibrary.domain.mall.MallTypeTo;
import rx.Observable;


/**
 * Created by xzz on 2017/6/25.
 **/

public class MallBannerView implements Holder<List<MallTypeTo>> {

    private GridLayout gridLayout;

    @Override
    public View createView(Context context) {
        gridLayout = new GridLayout(context);

        gridLayout.setColumnCount(4);
        return gridLayout;
    }

    @Override
    public void UpdateUI(Context context, int position, List<MallTypeTo> menuList) {
        Observable.from(menuList).subscribe(mallBannerTo -> {
            View mView = View.inflate(context, R.layout.mall_banner_item, null);
            MallBannerItemBinding binding = DataBindingUtil.bind(mView);
            binding.name.setText(mallBannerTo.getCate_name());
            Glide.with(context).load(mallBannerTo.getCate_img()).into(binding.image);
            mView.setOnClickListener(v -> {
                if (mallBannerTo.getCate_id()!=1000000) {
                    Intent intent = new Intent(context, GoodsSortActivity.class);
                    intent.putExtra("CateId", mallBannerTo.getCate_id());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }else {
                    Intent intent = new Intent(context, IntegralActivity.class);
                    intent.putExtra("CateId", mallBannerTo.getCate_id());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }

            });
            gridLayout.addView(mView);
        });

    }
}
