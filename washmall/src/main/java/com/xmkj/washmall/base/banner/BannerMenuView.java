package com.xmkj.washmall.base.banner;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.xmkj.washmall.R;


import java.util.List;

import hzxmkuar.com.applibrary.domain.main.MainHomeAdTo;


/**
 * Created by xzz on 2017/6/25.
 **/

public class BannerMenuView implements Holder<List<MainHomeAdTo>> {

    private GridLayout gridLayout;

    @Override
    public View createView(Context context) {
        gridLayout = new GridLayout(context);

       gridLayout.setColumnCount(4);
        return gridLayout;
    }

    @Override
    public void UpdateUI(Context context, int position, List<MainHomeAdTo> menuList ) {

    }
}
