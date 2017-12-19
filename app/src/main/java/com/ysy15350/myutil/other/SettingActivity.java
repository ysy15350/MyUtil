package com.ysy15350.myutil.other;


import android.content.pm.PackageManager;
import android.view.View;

import com.ysy15350.ysyutils.base.BaseActivity;
import com.ysy15350.ysyutils.common.CommFunAndroid;
import com.ysy15350.ysyutils.common.RequestPermissionType;
import com.ysy15350.ysyutils.custom_view.dialog.ConfirmDialog;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

import java.io.File;


/**
 * 设置
 *
 * @author yangshiyou
 */
@ContentView(com.ysy15350.ysyutils.R.layout.activity_setting)
public class SettingActivity extends BaseActivity {


    @Override
    public void initView() {

        super.initView();
        setFormHead("设置");
    }

    @Override
    protected void onResume() {
        super.onResume();

        String vesionName = CommFunAndroid.getAppVersionName(getApplicationContext());
        mHolder.setText(com.ysy15350.ysyutils.R.id.tv_version, "当前版本号：" + vesionName);
    }

    /**
     * 清除缓存
     *
     * @param view
     */
    @Event(value = com.ysy15350.ysyutils.R.id.ll_menu_1)
    private void ll_menu_1Click(View view) {

        String cachePath = CommFunAndroid.getCahePath(this);

        File dir = new File(cachePath);

        File[] files = dir.listFiles();
        if (files != null) {
            showMsg("缓存文件数量" + files.length);
        }

        clearErrorLog(dir);
    }

    private void clearErrorLog(File file) {
        try {
            if (!file.exists())
                return;
            if (file.isFile()) {
                file.delete();
                return;
            }
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                clearErrorLog(files[i]);
            }
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 检测更新
     *
     * @param view
     */
    @Event(value = com.ysy15350.ysyutils.R.id.ll_menu_2)
    private void ll_menu_2Click(View view) {

        boolean isGranted = checkPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, RequestPermissionType.REQUEST_CODE_ASK_READ_EXTERNAL_STORAGE, new PermissionsResultListener() {
            @Override
            public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
                boolean isGranted = false;
                if (grantResults != null && grantResults != null) {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        isGranted = true;

                    }
                }

                if (isGranted) {
                    showWaitDialog("版本检测中...");

                    String title = "版本更新(" + "" + ")";
                    String versionName = "1.0.3";
                    String content = "新版本描述\n1、修改BUG;\n2、优化逻辑；";
                    String fileSize = "3 M";
                    //http://www.360vrdh.com:8080/api/file/downloadApk/com.ysy15350.readpacket/32.apk
                    //http://www.360vrdh.com:8080/upload/uploadFiles/apks/red_packet(1.0.1).apk
                    //"http://www.mg0607.cn/Public/qrcode/qsk1.0.19_legu_signed_zipalign.apk";
                    //http://192.168.0.108:8080/api/file/downloadApk/com.ysy15350.readpacket/32.apk
                    //http://192.168.0.108:8080/api/file/download
                    //http://192.168.0.108:8080/api/file/downloadApk/com.ysy15350.readpacket/32.apk

                    String url = "http://192.168.0.108:8080/api/file/downloadApk/com.ysy15350.readpacket/32.apk";
                    updateVersion(title, versionName, content, fileSize, url);
                } else {
                    ConfirmDialog confirmDialog = new ConfirmDialog(SettingActivity.this, "你已拒绝读写手机存储，去权限设置页面打开？");
                    confirmDialog.setDialogListener(new ConfirmDialog.DialogListener() {
                        @Override
                        public void onCancelClick() {

                        }

                        @Override
                        public void onOkClick() {
                            gotoMiuiPermission();
                        }
                    });
                    confirmDialog.show();

                }

            }
        });


    }

    /**
     * 注销
     *
     * @param view
     */
    @Event(value = com.ysy15350.ysyutils.R.id.ll_menu_3)
    private void ll_menu_3Click(View view) {


    }


}
