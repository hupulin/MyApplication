package com.xmkj.washmall.wxapi;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

import com.tencent.open.utils.HttpUtils;
import com.tencent.tauth.IRequestListener;
import com.tencent.open.utils.HttpUtils.HttpStatusException;
import com.tencent.open.utils.HttpUtils.NetworkUnavailableException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;


public class BaseApiListener implements IRequestListener {

	public void onUnknowException(Exception e, Object state) {
// 出现未知错误时会触发此异常
	}

	@Override
	public void onComplete(JSONObject jsonObject) {

	}

	@Override
	public void onIOException(IOException e) {

	}

	@Override
	public void onMalformedURLException(MalformedURLException e) {

	}

	@Override
	public void onJSONException(JSONException e) {

	}

	@Override
	public void onConnectTimeoutException(ConnectTimeoutException e) {

	}

	@Override
	public void onSocketTimeoutException(SocketTimeoutException e) {

	}

	@Override
	public void onNetworkUnavailableException(NetworkUnavailableException e) {

	}

	@Override
	public void onHttpStatusException(HttpStatusException e) {

	}

	@Override
	public void onUnknowException(Exception e) {

	}
}