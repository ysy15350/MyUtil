package com.ysy15350.myutil.log;

import android.content.Intent;

import com.ysy15350.myutil.R;
import com.ysy15350.ysyutils.base.BaseActivity;
import com.ysy15350.ysyutils.common.file.FileUtils;
import com.ysy15350.ysyutils.model.FileInfo;

import org.xutils.view.annotation.ContentView;


/**
 * Created by yangshiyou on 2017/11/30.
 */


@ContentView(R.layout.activity_log_detail)
public class LogDetailActivity extends BaseActivity {

    @Override
    public void initView() {
        super.initView();

        Intent intent = getIntent();
        FileInfo fileInfo = (FileInfo) intent.getSerializableExtra("fileInfo");

        bindFileInfo(fileInfo);
    }

    /**
     * 绑定日志详情
     *
     * @param fileInfo
     */
    private void bindFileInfo(FileInfo fileInfo) {

        try {
            if (fileInfo != null) {
                String path = fileInfo.getPath();

                String content = FileUtils.readFile(path);


                mHolder.setText(R.id.tv_content, content);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
