package com.xmkj.washmall.base.banner;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.xmkj.washmall.base.WebActivity;
import com.xmkj.washmall.base.util.GlideRoundTransform;

import hzxmkuar.com.applibrary.domain.main.MainHomeAdTo;


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
        Glide.with(context).load(data.getPic()).placeholder(loadSrc).transform(new GlideRoundTransform(context,10)).into(imageView);
        imageView.setOnClickListener(view -> {
            if (data.getTarget_module()==2){
                Intent intent=new Intent(context, WebActivity.class);
                intent.putExtra("Title",data.getTag());
                intent.putExtra("Url",data.getTarget_id());
                context.startActivity(intent);
            }
        });
    }
}
