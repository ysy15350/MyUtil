package com.ysy15350.ysyutils.common.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by yangshiyou on 2017/12/11.
 */

public class ImageUtils {

    private static final String TAG = "ImageUtils";

    private static final String SD_PATH = "/sdcard/dskqxt/pic/";
    private static final String IN_PATH = "/dskqxt/pic/";

    /**
     * 随机生产文件名
     *
     * @return
     */
    private static String generateFileName() {
        return UUID.randomUUID().toString();
    }

    /**
     * 保存bitmap到本地
     *
     * @param context
     * @param mBitmap
     * @return
     */
    public static String saveBitmap(Context context, Bitmap mBitmap) {
        String savePath;
        File filePic;
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            String externalStorageDirectory = Environment.getExternalStorageDirectory() + "/";///storage/emulated/0/
            String path_1 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/";///storage/emulated/0/Pictures/

            savePath = externalStorageDirectory + "pic/";//   /sdcard/dskqxt/pic/

            savePath = path_1;
            File file = new File(savePath);
            if (!file.exists()) {
                boolean isCreateDir = file.mkdirs();
                Log.d(TAG, "saveBitmap: " + isCreateDir);
            }

        } else {
            savePath = context.getApplicationContext().getFilesDir()
                    .getAbsolutePath()
                    + IN_PATH;
        }
        try {
            filePic = new File(savePath + generateFileName() + ".jpg");
            if (!filePic.exists()) {
                boolean isCreateDir = filePic.getParentFile().mkdirs();
                boolean isCreateFile = filePic.createNewFile();//此方法返回true，如果指定的文件不存在，并已成功创建。如果该文件存在，该方法返回false。
            }
            FileOutputStream fos = new FileOutputStream(filePic);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

        return filePic.getAbsolutePath();
    }

}
