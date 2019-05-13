package com.xmkj.washmall.base;



import com.xmkj.washmall.wash.ScanActivity;
import com.xmkj.washmall.wash.fragment.SelectWashFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/9/27.
 */

public class ActivityManager {
    public static List<BaseActivity>activityList=new ArrayList<>();
    public static List<BaseActivity>merchantEnterList=new ArrayList<>();
    public static List<SelectWashFragment>washFragmentList=new ArrayList<>();
    public static ScanActivity scanDecodeActivity;

}
