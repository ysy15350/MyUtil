package com.ysy15350.myutil.log;

import android.content.Context;

import com.ysy15350.ysyutils.base.mvp.BasePresenter;
import com.ysy15350.ysyutils.common.CommFunAndroid;
import com.ysy15350.ysyutils.model.FileInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;



public class LogListPresenter extends BasePresenter<LogListViewInterface> {

    public LogListPresenter(Context context) {
        super(context);

    }

    public List<FileInfo> getFileList(Context context) {
        String cachePath = CommFunAndroid.getCahePath(context);

        File dir = new File(cachePath);
        if (dir.exists()) {
            if (dir.isDirectory()) {
                String[] filesName = dir.list();
                File[] files = dir.listFiles();
                if (filesName != null) {

                    List<FileInfo> fileInfos = new ArrayList<>();

                    for (File file :
                            files) {
                        if (file != null && file.exists()) {

                            String name = file.getName();
                            String path = file.getPath();
                            long length = file.length();

                            FileInfo fileInfo = new FileInfo();
                            fileInfo.setName(name);
                            fileInfo.setPath(path);
                            fileInfo.setLength(length);

                            fileInfos.add(fileInfo);
                        }
                    }

                    return fileInfos;
                }
            }

        }

        return null;
    }


}
