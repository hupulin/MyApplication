package com.xmkj.washmall.myself;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.makeramen.roundedimageview.RoundedImageView;
import com.soundcloud.android.crop.Crop;
import com.xmkj.washmall.MainApp;
import com.xmkj.washmall.R;
import com.xmkj.washmall.base.BaseActivity;
import com.xmkj.washmall.base.CommonDialog;
import com.xmkj.washmall.base.Constant;
import com.xmkj.washmall.base.util.DateUtil;
import com.xmkj.washmall.base.util.FileUtil;
import com.xmkj.washmall.myself.presenter.EditUserPresenter;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hzxmkuar.com.applibrary.domain.login.UserInfoTo;
import hzxmkuar.com.applibrary.impl.PermissionListener;

/**
 * Created by Administrator on 2018/12/28.
 **/

public class EditUserActivity extends BaseActivity implements PermissionListener {
    @BindView(R.id.head_img)
    RoundedImageView headImg;
    @BindView(R.id.nick_name)
    EditText nickName;
    @BindView(R.id.verification)
    EditText verification;
    private EditUserPresenter presenter;
    private String photoPath;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        ButterKnife.bind(this);
        setTitleName("编辑个人资料");
        initView();
        presenter = new EditUserPresenter(this);

    }

    private void initView() {

        disPlayRoundImage(headImg,userInfoTo.getFace_url());
        nickName.setText(userInfoTo.getUsername());
    }

    @OnClick({R.id.save, R.id.modify_password,R.id.head_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.save:
                if (TextUtils.isEmpty(nickName.getText().toString())){
                    showMessage("昵称还没有填写");
                    return;
                }
                presenter.modifyNick(nickName.getText().toString());
                break;
            case R.id.modify_password:
                Intent intent=new Intent(appContext,ModifyPasswordActivity.class);
                startActivity(intent);
                goToAnimation(1);
                break;
            case R.id.head_img:
                uploadHeadImageDialog();
                break;

        }
    }


    private void uploadHeadImageDialog() {
        CommonDialog alertDialog = new CommonDialog(this, getScreenWidth(), (int) (getScreenWidth() * 0.2), R.layout.dialog_pic_type, R.style.DialogDown);
        alertDialog.findViewById(R.id.btn_cancel).setOnClickListener(v -> alertDialog.dismiss());
        alertDialog.findViewById(R.id.btn_camera).setOnClickListener(v -> {
            alertDialog.dismiss();
            getPermission(Manifest.permission.CAMERA, this);
        });
        alertDialog.findViewById(R.id.btn_album).setOnClickListener(v -> {
            alertDialog.dismiss();
            getPermission(Manifest.permission.READ_EXTERNAL_STORAGE, this);
        });
        alertDialog.show();
        alertDialog.setCanceledOnTouchOutside(true);
    }

    @Override
    public void accept(String permission) {
        switch (permission) {
            case Manifest.permission.CAMERA:
                getPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,this);
                break;
            case Manifest.permission.WRITE_EXTERNAL_STORAGE:
                openCamera();
                break;
            case Manifest.permission.READ_EXTERNAL_STORAGE:
                enterAlbum();
                break;

        }

    }

    @Override
    public void refuse(String permission) {

    }

    private void openCamera() {
        Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri uri = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            try {
                ContentValues values = new ContentValues(1);
                File mOutPhotoFile = new File(MainApp.getCacheImagePath(), DateUtil.getDateString(DateUtil.mFormatTimeCamara) + ".png");
                photoPath = mOutPhotoFile.getAbsolutePath();
                values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpg");
                values.put(MediaStore.Images.Media.DATA, photoPath);
                uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {

            File mOutPhotoFile = new File(MainApp.getCacheImagePath(), DateUtil.getDateString(DateUtil.mFormatTimeCamara) + ".png");
            photoPath = mOutPhotoFile.getAbsolutePath();
            uri = Uri.fromFile(mOutPhotoFile);

        }
        intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        intentCamera.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        startActivityForResult(intentCamera, Constant.RESULT_CAMERA);


    }


    private void enterAlbum() {

        // 打开相册
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra("return_data", true);
        startActivityForResult(intent, Constant.RESULT_SDCARD);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case Constant.RESULT_SDCARD:
                    Uri uri = data.getData();
                    String mPhotoPath = FileUtil.getPath(this, uri);
                    if (!TextUtils.isEmpty(mPhotoPath)) {

                        beginCrop(uri);
                    }

                    break;
                case Constant.RESULT_CAMERA:

                    Uri uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

                    if (uri2 != null && photoPath != null) {
                        Uri uri1 = Uri.fromFile(new File(photoPath));
                        beginCrop(uri1);

                    }
                    break;
                case Crop.REQUEST_CROP:
                    handleCrop(data);
                    break;

            }
        }
    }

    private void beginCrop(Uri source) {
        Uri destination = Uri.fromFile(new File(getCacheDir(), "croppedd.jpg"));
        Crop.of(source, destination).asSquare().start(this);

    }

    private void handleCrop(Intent result) {

        presenter.modifyImage(FileUtil.getPath(appContext, Crop.getOutput(result)));


    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void uploadImageSuccess(String path) {
        disPlayRoundImage(headImg, path);
    }

    @Override
    protected void submitDataSuccess(Object data) {
        showMessage("修改昵称成功");
    }
}
