package com.hzxm.wolaixish.base;



import com.hzxm.wolaixish.login.LoginActivity;
import com.hzxm.wolaixish.main.ScanDecodeActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/9/27.
 */

public class ActivityManager {
    public static List<BaseActivity>activityList=new ArrayList<>();
    public static List<BaseActivity>merchantEnterList=new ArrayList<>();
//    public static MainActivity mainActivity;
//    public static PersonOrderActivity personOrderActivity;
//    public static MyOrderActivity myOrderActivity;
//    public static SelectDemandActivity demandActivity;
//    public static SearchDemandActivity searchDemandActivity;
//    public static SearchDetailActivity searchDetailActivity;
//    public static IntegrationDetailActivity integrationDetailActivity;
    public static LoginActivity loginActivity;
    public static ScanDecodeActivity scanDecodeActivity;
}
