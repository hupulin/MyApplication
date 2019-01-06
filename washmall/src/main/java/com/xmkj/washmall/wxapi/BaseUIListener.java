package com.xmkj.washmall.wxapi;

import org.json.JSONObject;

import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;


import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class BaseUIListener implements IUiListener {

	@Override
	public void onComplete(Object o) {
		System.out.println(o+"===");
	}

	@Override
	public void onError(UiError uiError) {
		System.out.println(uiError+"error");
	}

	@Override
	public void onCancel() {
		System.out.println("cancel");
	}
}