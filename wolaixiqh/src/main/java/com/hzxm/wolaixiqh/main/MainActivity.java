package com.hzxm.wolaixiqh.main;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;


import com.hzxm.wolaixiqh.R;
import com.hzxm.wolaixiqh.base.BaseActivity;
import com.hzxm.wolaixiqh.base.NoSlideViewPager;
import com.hzxm.wolaixiqh.main.fragment.MainFragment;
import com.hzxm.wolaixiqh.main.fragment.MyselfFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    private List<Fragment> fragmentList = new ArrayList<>();
    private MyselfFragment myselfFragment;
    private MainFragment mainFragment;
    @BindView(R.id.fragment_layout)
    NoSlideViewPager fragmentLayout;
    @BindView(R.id.main_view)
     View mainView;
    @BindView(R.id.my_info_view)
     View myInfoView;
    @BindView(R.id.main_text)
    TextView mainText;
    @BindView(R.id.my_info_text)
    TextView myInfoText;
    private FragmentPagerAdapter pagerAdapter;
    private boolean canOut;
    private Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFragment();

    }
    private void initFragment() {
        myselfFragment=new MyselfFragment(this);
        mainFragment=new MainFragment(this);
        fragmentList.add(mainFragment);
        fragmentList.add(myselfFragment);
        pagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }
        };

        fragmentLayout.setAdapter(pagerAdapter);
        fragmentLayout.setOffscreenPageLimit(2);
        fragmentLayout.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position,float positionOffset,int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mainView.setBackgroundResource(fragmentLayout.getCurrentItem()==0?R.mipmap.main_press_icon:R.mipmap.main_un_press_icon);
                mainText.setTextColor(fragmentLayout.getCurrentItem()==0?getResources().getColor(R.color.app_text_color):getResources().getColor(R.color.black));
                myInfoView.setBackgroundResource(fragmentLayout.getCurrentItem()==1?R.mipmap.my_info_press_bg:R.mipmap.my_info_un_press_bg);
                myInfoText.setTextColor(fragmentLayout.getCurrentItem()==1?getResources().getColor(R.color.app_text_color):getResources().getColor(R.color.black));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        fragmentLayout.setCurrentItem(getIntent().getIntExtra("Index",0));
        mainView.setBackgroundResource(R.mipmap.main_press_icon);
        mainText.setTextColor(getResources().getColor(R.color.app_text_color));

    }

    @OnClick({R.id.main_layout, R.id.my_info_layout,})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.main_layout:
                fragmentLayout.setCurrentItem(0);

                break;
            case R.id.my_info_layout:
                fragmentLayout.setCurrentItem(1);
                break;

        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            showMessage("再返回一次退出我来收货端");
            if (canOut) {
                finish();
            }
            canOut = true;

            handler.postDelayed(() -> canOut = false, 5000);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        initFragment();
        fragmentLayout.setCurrentItem(getIntent().getIntExtra("Index",1));

    }
}
