package com.xmkj.washmall.base.banner;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.xmkj.washmall.base.WebActivity;
import com.xmkj.washmall.base.util.GlideRoundTransform;
import com.xmkj.washmall.integral.IntegralGoodsDetailActivity;
import com.xmkj.washmall.mall.GoodsDetailActivity;
import com.xmkj.washmall.wash.SelectWashActivity;

import hzxmkuar.com.applibrary.domain.integral.IntegralGoodsTo;
import hzxmkuar.com.applibrary.domain.main.MainHomeAdTo;
import hzxmkuar.com.applibrary.domain.main.MallGoodsTo;


/**
 * Created by xzz on 2017/6/25.
 **/

public class HomeBannerHolderView implements Holder<MainHomeAdTo> {
    private ImageView imageView;
    private int loadSrc;

    @Override
    public View createView(Context context) {
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, MainHomeAdTo data) {
        Glide.with(context).load(data.getPic()).placeholder(loadSrc).transform(new GlideRoundTransform(context, 10)).into(imageView);
        imageView.setOnClickListener(view -> {
            if (data.getTarget_module() == 2) {
                Intent intent = new Intent(context, WebActivity.class);
                intent.putExtra("Title", data.getTag());
                intent.putExtra("Url", data.getTarget_id());
                context.startActivity(intent);
            }

            if (data.getTarget_module() == 3) {
                Intent intent = new Intent(context, SelectWashActivity.class);
                intent.putExtra("TypeId", data.getTarget_id());
                context.startActivity(intent);
            }
            if (data.getTarget_module() == 4) {
                Intent intent = new Intent(context, GoodsDetailActivity.class);
                MallGoodsTo mode = new MallGoodsTo();
                mode.setGoods_id(Integer.valueOf(data.getTarget_id()));
                mode.setGoods_name(data.getTag());
                intent.putExtra("GoodsTo", mode);
                context.startActivity(intent);
            }
            if (data.getTarget_module() == 5) {
                Intent intent = new Intent(context, IntegralGoodsDetailActivity.class);
                IntegralGoodsTo mode=new IntegralGoodsTo();
                mode.setGoods_id(Integer.valueOf(data.getTarget_id()));
                mode.setGoods_name(data.getTag());
                intent.putExtra("GoodsTo",mode);
                context.startActivity(intent);
            }
        });
    }
}
