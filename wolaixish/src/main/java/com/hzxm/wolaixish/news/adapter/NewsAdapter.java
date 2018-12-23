package com.hzxm.wolaixish.news.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hzxm.wolaixish.R;
import com.hzxm.wolaixish.base.adapter.BaseAdapter;
import com.hzxm.wolaixish.base.adapter.BindingHolder;
import com.hzxm.wolaixish.databinding.MyOrderDeliveryItemBinding;
import com.hzxm.wolaixish.databinding.NewsItemBinding;

import hzxmkuar.com.applibrary.domain.news.NewsTo;

/**
 *  Created by Administrator on 2018/12/18.
 */

public class NewsAdapter  extends BaseAdapter<NewsTo,NewsItemBinding> {
    private boolean isclick;
    public NewsItemBinding binding;
    public NewsAdapter(Activity context) {
        super(context);
    }


    @NonNull
    @Override
    public BindingHolder<NewsItemBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        NewsItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.news_item, parent, false);

        BindingHolder<NewsItemBinding> holder = new BindingHolder<>(binding.getRoot());
        holder.setBinding(binding);

        return holder;
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(BindingHolder<NewsItemBinding> holder, int position) {
        super.onBindViewHolder(holder, position);
         binding = holder.getBinding();

    }

    @Override
    public int getItemCount() {
        return 20;
    }

}


