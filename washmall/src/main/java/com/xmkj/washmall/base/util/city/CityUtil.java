package com.xmkj.washmall.base.util.city;

import android.app.Activity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import hzxmkuar.com.applibrary.domain.login.*;
import hzxmkuar.com.applibrary.domain.login.CityTo;

/**
 * Created by Administrator on 2018/12/25.
 */

public class CityUtil {
    public static List<CityTo> initJson(Activity activity) {
        List<CityTo>cityList=new ArrayList<>();
        JSONObject mJsonObj = null;
        try {
            StringBuffer sb = new StringBuffer();
            InputStream is = activity.getClass().getClassLoader().getResourceAsStream("assets/" + "province.json");
            int len = -1;
            byte[] buf = new byte[1024];
            while ((len = is.read(buf)) != -1) {
                sb.append(new String(buf, 0, len, "utf-8"));
            }
            is.close();
            mJsonObj = new JSONObject(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            cityList = new Gson().fromJson(mJsonObj.getJSONArray("RECORD").toString(), new TypeToken<List<CityTo>>() {}.getType());
            System.out.println(cityList+"cityList");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cityList;
    }
}
