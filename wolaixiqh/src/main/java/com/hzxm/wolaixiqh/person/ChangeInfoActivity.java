package com.hzxm.wolaixiqh.person;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import com.hzxm.wolaixiqh.MainApp;
import com.hzxm.wolaixiqh.R;
import com.hzxm.wolaixiqh.base.ActivityManager;
import com.hzxm.wolaixiqh.base.BaseActivity;
import com.hzxm.wolaixiqh.base.CommonDialog;
import com.hzxm.wolaixiqh.base.Constant;
import com.hzxm.wolaixiqh.base.util.DateUtil;
import com.hzxm.wolaixiqh.base.util.FileUtil;
import com.hzxm.wolaixiqh.login.LoginActivity;
import com.hzxm.wolaixiqh.person.present.ChangeInfoPresent;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.soundcloud.android.crop.Crop;

import java.io.File;
import java.text.ParseException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hzxmkuar.com.applibrary.domain.delivery.main.MainUserInfo;
import rx.Observable;

/**
 * Created by Administrator on 2018/12/16.
 */

public class ChangeInfoActivity extends BaseActivity implements OnDateSetListener {
    @BindView(R.id.man)
    TextView man;
    @BindView(R.id.women)
    TextView women;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.head_image)
    ImageView headImage;
    @BindView(R.id.nickname)
    EditText nickname;
    private String headImageUrl;
    private String photoPath;
    private String path;//上传图像url
    private String gender;//上传性别
    private String birthday;//上传性别
    private ChangeInfoPresent presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_info);
        ButterKnife.bind(this);
        presenter = new ChangeInfoPresent(this);
        presenter.  getUserInfo();
        setView();
    }

    private void setView() {
//        headImageUrl =userInfoTo.getUser_info().getNickname();
        setRightAndTitleText("保存", "编辑个人资料");

    }

    @SuppressLint("SetTextI18n")
    @OnClick({R.id.right_text, R.id.birthday, R.id.change_password, R.id.man, R.id.women, R.id.logout, R.id.head_image})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.right_text:
                presenter.updateUserInfo(nickname.getText().toString(),gender,birthday);
                break;
            case R.id.birthday:
                showBirthday();
                break;
            case R.id.head_image:
                photoPath = "";
                uploadHeadImageDialog();
                break;
            case R.id.change_password:
                startActivity(new Intent(this, ChangePasswordActivity.class));
                goToAnimation(1);
                break;
            case R.id.man:
                man.setTextColor(Color.parseColor("#000000"));
                man.setBackground(getResources().getDrawable(R.drawable.select_man_bg));
                women.setBackground(getResources().getDrawable(R.drawable.transparent));
                women.setTextColor(Color.parseColor("#2E23FF"));
                gender="男";
                break;
            case R.id.women:
                women.setTextColor(Color.parseColor("#000000"));
                women.setBackground(getResources().getDrawable(R.drawable.select_women_bg));
                man.setTextColor(Color.parseColor("#2E23FF"));
                man.setBackground(getResources().getDrawable(R.drawable.transparent));
                gender="女";
                break;
            case R.id.logout:

                NiftyDialogBuilder dialog = NiftyDialogBuilder.getInstance(this);
                dialog.setContentView(R.layout.my_dialog_comment_layout);
                TextView title = dialog.findViewById(R.id.title);
                title.setText("确定退出登录");
                dialog.show();
                dialog.findViewById(R.id.confirm).setOnClickListener(view1 -> {
                    Intent out = new Intent(appContext, LoginActivity.class);
                    startActivity(out);
                    userInfoHelp.saveUserInfo(null);
                    userInfoHelp.saveUserLogin(false);
                    goToAnimation(1);
                    Observable.from(ActivityManager.activityList).subscribe((BaseActivity baseActivity) -> baseActivity.finish());
                    dialog.dismiss();

                });
                dialog.findViewById(R.id.parent).setOnClickListener(view1 -> dialog.dismiss());
                dialog.findViewById(R.id.cancel).setOnClickListener(view1 -> dialog.dismiss());
                break;
        }
    }

    public void showBirthday() {
        long tenYears = 60L * 365 * 1000 * 60 * 60 * 24L;
        long oneYears = 365 * 1000 * 60 * 60 * 24L;
        TimePickerDialog mDialogAll = new TimePickerDialog.Builder().setCallBack((OnDateSetListener) this).
                setCancelStringId(Constant.CANCEL).
                setTitleStringId(Constant.CHOOSING_BIRTHDAY).
                setSureStringId(Constant.CONFIRM).
                setMonthText(Constant.MONTH).setDayText(Constant.DAY).setMinMillseconds(System.currentTimeMillis() - tenYears).setMaxMillseconds(System.currentTimeMillis()).setCurrentMillseconds(System.currentTimeMillis()).
                setCyclic(false).setThemeColor(Color.parseColor("#6d75a4")).setType(Type.YEAR_MONTH_DAY).setWheelItemTextNormalColor(R.color.timetimepicker_default_text_color).setWheelItemTextSelectorColor(R.color.timepicker_toolbar_bg).setWheelItemTextSize(12).build();
        mDialogAll.show(getSupportFragmentManager(), "");

    }


    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
//       millseconds birthday.setText(DateUtil.longToDate(millSeconds, DateUtil.mDateFormatStringLine));
//        birthday=DateUtil.longToDate(millseconds, DateUtil.mFormatDateString);
        try {
            birthday=   DateUtil. getDateTimeFormat(DateUtil.mFormatDateString,DateUtil.longToDate(millseconds, DateUtil.mFormatDateString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.i("5555", "onDateSet: "+birthday);
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
                getPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, this);
                break;
            case Manifest.permission.WRITE_EXTERNAL_STORAGE:
                openCamera();
                break;
            case Manifest.permission.READ_EXTERNAL_STORAGE:
                enterAlbum();
                break;

        }

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
//                    headImage.setImageURI(Crop.getOutput(data));
//
//                    Log.i("222", "onActivityResult: "+Crop.getOutput(data));
                    handleCrop(data);
                    break;

            }
        }
    }

    private void beginCrop(Uri source) {
        Uri destination = Uri.fromFile(new File(getCacheDir(), "croppedd.jpg"));
        Log.i("222", "beginCrop: " + destination);
        Crop.of(source, destination).asSquare().start(this);

    }

    public void uploadImageSuccess(String path) {
        disPlayRoundImage(headImage, path);
    }

    private void handleCrop(Intent result) {
        presenter.modifyImage(FileUtil.getPath(appContext, Crop.getOutput(result)));


    }

    @Override
    protected void submitDataSuccess(Object data) {
        super.submitDataSuccess(data);
        finish();
    }

    @Override
    public void loadDataSuccess(Object data) {
        MainUserInfo mode=(MainUserInfo)data;
        if(mode.getGender()==1){
            man.setTextColor(Color.parseColor("#000000"));
            man.setBackground(getResources().getDrawable(R.drawable.select_man_bg));
            women.setBackground(getResources().getDrawable(R.drawable.transparent));
            women.setTextColor(Color.parseColor("#2E23FF"));
            gender="男";
        }else{
            women.setTextColor(Color.parseColor("#000000"));
            women.setBackground(getResources().getDrawable(R.drawable.select_women_bg));
            man.setTextColor(Color.parseColor("#2E23FF"));
            man.setBackground(getResources().getDrawable(R.drawable.transparent));
            gender="女";
        }
        disPlayRoundImage(headImage, mode.getFace_url());
        nickname.setText(mode.getNickname());
        tvBirthday.setText(mode.getBirthday());
    }
}
