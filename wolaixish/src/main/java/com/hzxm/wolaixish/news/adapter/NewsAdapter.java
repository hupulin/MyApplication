package com.hzxm.wolaixish.news.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hzxm.wolaixish.R;
import com.hzxm.wolaixish.base.adapter.BaseAdapter;
import com.hzxm.wolaixish.base.adapter.BindingHolder;
import com.hzxm.wolaixish.databinding.MyOrderDeliveryItemBinding;
import com.hzxm.wolaixish.databinding.NewsItemBinding;

import hzxmkuar.com.applibrary.domain.delivery.news.NewsTo;


/**
 *  Created by Administrator on 2018/12/18.
 */

public class NewsAdapter  extends BaseAdapter<NewsTo.ListsEntity,NewsItemBinding> {
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
        holder.setIsRecyclable(false);

        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(BindingHolder<NewsItemBinding> holder, int position) {
        super.onBindViewHolder(holder, position);
        binding = holder.getBinding();
        NewsTo.ListsEntity mode = mList.get(position);
        binding.title.setText(mode.getTitle());
        binding.contend.setText(mode.getContent());
        binding.time.setText(mode.getDateline());
        binding.selectImage.setBackground(mode.getIs_read() == 0 ? mContext.getResources().getDrawable(R.mipmap.new_un_read_icon) : mContext.getResources().getDrawable(R.mipmap.news_read_icon));

    }

//        binding.selectView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onSelectClick.onItemClick(position);
//            }
//        });
//    }
//
//    public  interface OnAddSelectListener {
//        // true add; false cancel
//        public void onItemClick(int position); //传递boolean类型数据给activity
//    }
//
//    // add click callback
//    OnAddSelectListener onSelectClick;
//
//    public void setOnAddSelectListener(OnAddSelectListener onSelectClick) {
//        this.onSelectClick = onSelectClick;
//

}
