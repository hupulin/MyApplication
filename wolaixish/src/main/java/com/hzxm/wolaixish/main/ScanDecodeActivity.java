package com.hzxm.wolaixish.main;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.hzxm.wolaixish.R;
import com.hzxm.wolaixish.base.ActivityManager;
import com.hzxm.wolaixish.base.BaseActivity;
import com.hzxm.wolaixish.base.MyObserver;
import com.hzxm.wolaixish.main.present.OpenDoorPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hzxmkuar.com.applibrary.api.ApiClient;
import hzxmkuar.com.applibrary.api.DeliveryApi;
import hzxmkuar.com.applibrary.domain.MessageTo;
import hzxmkuar.com.applibrary.domain.delivery.main.IdParam;
import hzxmkuar.com.applibrary.domain.delivery.main.OpenDoorParam;
import hzxmkuar.com.applibrary.impl.PermissionListener;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import util.zxing.activity.CaptureFragment;
import util.zxing.activity.CodeUtils;
import util.zxing.activity.ZXingLibrary;
import util.zxing.camera.CameraManager;

/**
 * Created by xzz on 2018/7/22.
 **/

public class ScanDecodeActivity extends BaseActivity {


    @BindView(R.id.code_container)
    FrameLayout codeContainer;
    @BindView(R.id.open_light)
    TextView openLight;

    private int type;
    private int id;
    private boolean open;
    OpenDoorPresenter openDoorPresent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ZXingLibrary.initDisplayOpinion(this);
        setContentView(R.layout.activity_scan_code);
        ButterKnife.bind(this);
        setTitleName("扫一扫");
        getPermission(Manifest.permission.CAMERA,this);
        id=  getIntent().getIntExtra("id",0);
         openDoorPresent=new OpenDoorPresenter(this);
    }

    private void setView() {
        type = getIntent().getIntExtra("Type", 0);
        ActivityManager.scanDecodeActivity=this;
        CaptureFragment captureFragment = new CaptureFragment();
        captureFragment.setAnalyzeCallback(analyzeCallback);
        getSupportFragmentManager().beginTransaction().replace(R.id.code_container, captureFragment).commit();
    }

    @Override
    public void accept(String permission) {
        setView();
        super.accept(permission);
    }

    /**
     * 二维码解析回调函数
     */
    CodeUtils.AnalyzeCallback analyzeCallback = new CodeUtils.AnalyzeCallback() {
        @Override
        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
//                Intent intent = new Intent(appContext, MainActivity.class);
//                intent.putExtra("Result", result);
//                startActivity(intent);
//                finish();
//                goToAnimation(1);
                openDoorPresent.OpenDoorPresent(id,result);
        }

        @Override
        public void onAnalyzeFailed() {

            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_FAILED);
            bundle.putString(CodeUtils.RESULT_STRING, "");
            resultIntent.putExtras(bundle);
            setResult(RESULT_OK, resultIntent);
            finish();
        }
    };

    @Override
    protected void submitDataSuccess(Object data) {
       new Handler().postDelayed(() -> {
           finish();
           goToAnimation(2);
       },2500);

    }

    @OnClick({R.id.open_light})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.open_light:
                openLight();
                break;
        }
    }

    private void openLight() {
        if (CameraManager.get().camera == null) {

            return;
        }
        if (!open) {

            Camera.Parameters mParameters = CameraManager.get().camera.getParameters();
            mParameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            CameraManager.get().camera.setParameters(mParameters);
            openLight.setText("点击关闭照明");
            open = true;
        } else {
            Camera.Parameters mParameters = CameraManager.get().camera.getParameters();
            mParameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            CameraManager.get().camera.setParameters(mParameters);
            openLight.setText("点击开启照明");
            open = false;
        }
    }

}